package com.oversea.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oversea.common.util.Version;

@Controller
public class TestController {


    @RequestMapping("/version")
    public String version(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.getOutputStream().print(Version.getVersion(Version.class, "2.0.0.0"));
        response.getOutputStream().close();
        return null;
    }
}
