package com.terabits.controller;

import com.terabits.meta.po.Admin.AdminRecordPO;
import com.terabits.service.AdminService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

@Controller
public class maincontroller {

    @Autowired
    private AdminService adminService;
    //***********************************************登录界面************************************************************
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public void login(@RequestParam(value = "name") String name,
                        @RequestParam(value = "password") String password,
                        HttpServletResponse response)throws Exception{
        //System.out.println("name:" + name + " password:" + password);
        //int result;
        //result=adminService.insertAdmin(name,password);
        String tmp=adminService.selectAdminByAccount(name);
        //System.out.println(tmp);
        if(tmp==null)
            response.getWriter().print("false");
        else {
            if(tmp.equals(password))
                response.getWriter().print("true");
            else
                response.getWriter().print("false");
        }
    }


    //*********************************************充值管理**************************************************************
    /*
    JsonObject
    {
        adminname
        money:
    }*/
    @RequestMapping(value = "/recharge",method = RequestMethod.POST)
    public void recharge(@RequestParam(value = "adminname") String adminname,
                         @RequestParam(value = "money") String money,
                         HttpServletResponse response)throws Exception{
        AdminRecordPO adminRecordPO=new AdminRecordPO();

        adminRecordPO.setAdminName(adminname);
        double tmp=Double.valueOf(money).doubleValue();
        adminRecordPO.setMoney(tmp);

        int result=adminService.insertAdminRecord(adminRecordPO);
        if(result==1)
            response.getWriter().print("1");
        else
            response.getWriter().print("0");
        //***********************查找测试成功*********************************
        /*List<AdminRecordPO> adminRecordPOList=new ArrayList<AdminRecordPO>();
        adminRecordPOList=adminService.selectAllAdminRecord();
        int i=adminRecordPOList.size();
        response.getWriter().print(i);*/
    }
}
