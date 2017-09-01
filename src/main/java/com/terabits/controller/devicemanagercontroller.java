package com.terabits.controller;

import com.terabits.meta.po.Admin.AdminPO;
import com.terabits.meta.po.Device.TerminalPO;
import com.terabits.service.DeviceService;
import com.terabits.service.HuaweiService;
import com.terabits.utils.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/device")
public class devicemanagercontroller {
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private HuaweiService huaweiService;
    //*****************************************************************************************************************
    // 添加设备*
    /*
    *   200:请求成功
    *   400:认证失败
    *   500:添加设备失败*/
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public @ResponseBody JSONObject add(@RequestHeader("Authorization") String clientToken,
                                        @RequestParam("imei") String imei,
                                        @RequestParam("address") String address,
                                        @RequestParam("sim") String sim)throws Exception{
     //************************************************************************
        // 授权认证***
        AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
        System.out.println(adminPO);
        if (adminPO == null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", 400);
            return jsonObject;
        }
     //**************************************************************************
        // 获取deviceId和token*
        JSONObject lot=PlatformGlobal.add(imei);
        String token=lot.getString("token");
        String deviceId=lot.getString("deviceId");
    //**************************************************************************
        //更新huaweiToken
        int resultUpdateToken = huaweiService.updateToken(token);
        // 数据库插入一条记录*
        TerminalPO terminalPO=new TerminalPO();
        terminalPO.setImei(imei);
        terminalPO.setDeviceId(deviceId);
        terminalPO.setDisplayId("1007");
        terminalPO.setWebId("haha");
        terminalPO.setState(10);
        terminalPO.setStrength(0);
        terminalPO.setLocation(address);
        terminalPO.setSimId(sim);
        terminalPO.setSimRemain(10);
        int resultInsertTerminal=deviceService.insertTerminal(terminalPO);
    //**************************************************************************
        // 插入成功返回200，否则返回500*
        JSONObject jsonObject=new JSONObject();
        if(resultInsertTerminal==1&&resultUpdateToken==1)
            jsonObject.put("status",200);
        else
            jsonObject.put("status",500);
        return jsonObject;

    }


    //*****************************************************************************************************************
    // 删除设备*
    /*
        200：请求成功
        400:认证失败
        401：设备不存在
        500：删除失败*/
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public @ResponseBody JSONObject delete(@RequestHeader("Authorization") String clientToken,
                                           @RequestParam("displayId") String displayId)throws Exception{
    //*************************************************************************
        // 授权认证*
        System.out.println(clientToken);
        AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
        System.out.println(adminPO);
        if (adminPO == null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", 400);
            return jsonObject;
        }
    //**************************************************************************
        //获取终端terminalPO
        TerminalPO terminalPO=deviceService.selectOneTerminal(displayId);
        if(terminalPO==null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", 401);
            return jsonObject;
        }
    //**************************************************************************
        //在lot平台上删除设备
        String token = PlatformGlobal.delete(terminalPO.getDeviceId());
        System.out.println(token);
    //**************************************************************************
        //更新token
        int result = huaweiService.updateToken(token);
        //在数据库中删除设备
        int result1 = deviceService.deleteTerminal(displayId);
    //**************************************************************************
        JSONObject jsonObject=new JSONObject();
        if(result==1&&result1==1)
            jsonObject.put("status",200);
        else
            jsonObject.put("status",500);
        return jsonObject;
    }


    //******************************************************************************************************************
    @RequestMapping(value = "/replace",method = RequestMethod.POST)
    public void replace(@RequestParam("oldImei") String oldImei,
                        @RequestParam("newImei") String newImei,
                        HttpServletResponse response)throws Exception{

    }

}
