package com.lunatech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

@Controller
public class MainController {

    @ApiIgnore
    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("home");
    }
}
