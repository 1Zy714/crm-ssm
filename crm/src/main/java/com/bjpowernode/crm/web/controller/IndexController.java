package com.bjpowernode.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    /**
     * @RequestMapping: value = "http://127.0.0.1:8080/crm/*",前面一段项目的地址可以省略
     *                          直接用"/*"表示
     *              如下的value意思是进入项目就访问controller的方法index()
     * */
    @RequestMapping("/")
    public String index(){
        //请求转发
        return "index";
    }

}
