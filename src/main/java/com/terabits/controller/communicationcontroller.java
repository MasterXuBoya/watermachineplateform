package com.terabits.controller;

import com.terabits.meta.po.Device.TerminalPO;
import com.terabits.service.DeviceService;
import com.terabits.utils.PlatformGlobal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
public class communicationcontroller
{
    @Autowired
    private DeviceService deviceService;
    @RequestMapping(value = "/parameter",method = RequestMethod.POST)
    public void parameter(@RequestParam(value = "displayId") String displayId,
                          @RequestParam(value = "openTime") String openTime,
                          @RequestParam(value = "pulse") String pulse,
                          @RequestParam(value = "hotPulse")String hotPulse,
                          @RequestParam(value = "heartRate") String heartRate,
                          HttpServletResponse response)throws Exception{
        //*****************************根据dispalyId查询deviceId*********************************************
        System.out.println(displayId+"  "+openTime+"  "+pulse+"  "+hotPulse+"  "+heartRate);
        TerminalPO terminalPO=deviceService.selectOneTerminal(displayId);
        String deviceId=terminalPO.getDeviceId();
        System.out.println(deviceId);

        //***********************************************************************************************
        if(openTime!=""){
            byte[] txd1=new byte[5];
            txd1[0]=(byte)0xBB;
            txd1[1]=(byte)Integer.valueOf(openTime).intValue();
            txd1[2]=(byte)0x1f;
            txd1[3]=(byte)0x2f;
            txd1[4]=(byte)0xFB;
            try {
                PlatformGlobal.command(txd1,deviceId);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        //***********************************************************************************************
        byte tmp1,tmp2;
        if(pulse=="")
            tmp1=10;
        else
            tmp1=(byte)(Double.valueOf(pulse).doubleValue()*10);
        if(hotPulse=="")
            tmp2=10;
        else
            tmp2=(byte)(Double.valueOf(hotPulse).doubleValue()*10);
        byte[] txd2=new byte[6];
        txd2[0]=(byte)0xBC;
        txd2[1]=tmp1;
        txd2[2]=tmp2;
        txd2[3]=(byte)0x1f;
        txd2[4]=(byte)0x2f;
        txd2[5]=(byte)0xFC;
        try {
            PlatformGlobal.command(txd2,deviceId);
        }catch (Exception e){
            e.printStackTrace();
        }
        //***********************************************************************************************
        if(heartRate!=""){
            byte[] txd3=new byte[5];
            txd3[0]=(byte)0xBD;
            txd3[1]=(byte)Integer.valueOf(heartRate).intValue();
            txd3[2]=(byte)0x1f;
            txd3[3]=(byte)0x2f;
            txd3[4]=(byte)0xFD;
            try {
                PlatformGlobal.command(txd3,deviceId);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        response.getWriter().print("1");
    }
}
