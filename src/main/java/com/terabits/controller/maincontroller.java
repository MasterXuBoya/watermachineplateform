package com.terabits.controller;

import com.auth0.jwt.internal.org.bouncycastle.asn1.ocsp.ResponseData;
import com.terabits.meta.po.Admin.AdminPO;
import com.terabits.service.AdminService;
import com.terabits.utils.JWT;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
public class maincontroller {

    @Autowired
    private AdminService adminService;

    //***********************************************登录界面************************************************************
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(@RequestParam(value = "name") String name,
                      @RequestParam(value = "password") String password,
                      HttpServletResponse response) throws Exception {
        System.out.println("name:" + name + " password:" + password);
        AdminPO adminPO = adminService.selectAdmin(name);
        System.out.println(adminPO);
        if (adminPO != null && adminPO.getPassword().equals(password)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("admin", adminPO);
            String token = JWT.sign(adminPO, 30L * 24L * 3600L * 1000L);
            jsonObject.put("token", token);
            jsonObject.put("status", 1);
            response.getWriter().print(jsonObject);
        } else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", 0);
            response.getWriter().print(jsonObject);
        }
    }
}
