package com.momo.momo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class maincontroller {

    @GetMapping("index")
    public  String homepage(){
        System.out.println("Main controller");
        return "index";
    }
}
