package com.sevastopall.spring.http.controller;

import com.sevastopall.spring.dto.UserReadDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api/v1")
@SessionAttributes({"user"})
public class GreetingController {

    @GetMapping(value = "/hello")
    public ModelAndView hello(ModelAndView modelAndView,
                              HttpServletRequest request) {
        modelAndView.setViewName("greeting/hello");
        modelAndView.addObject("user", new UserReadDto(1L, "Ivan"));
        return modelAndView;
    }

    @GetMapping(value = "/bye")
    public ModelAndView bye(@SessionAttribute("user") UserReadDto user, ModelAndView modelAndView) {
        modelAndView.setViewName("greeting/bye");

        return modelAndView;
    }

    @GetMapping(value = "/hello/{id}")
    public ModelAndView hello2(ModelAndView modelAndView,
                              HttpServletRequest request,
                              @RequestParam Integer age,
                              @RequestHeader String accept,
                              @CookieValue("JSESSIONID") String jsessionId,
                              @PathVariable Integer id) {
        String ageParamValue = request.getParameter("age");
        String acceptHeader = request.getHeader("accept");
        Cookie[] cookies = request.getCookies();

        modelAndView.setViewName("greeting/hello");

        return modelAndView;
    }


}
