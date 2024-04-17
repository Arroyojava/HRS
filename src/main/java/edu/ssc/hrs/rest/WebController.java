package edu.ssc.hrs.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/customer-portal")
    public String site() {
        return "front-end/site";
    }

    @GetMapping("/customer-portal/about-us")
    public String aboutUs() {
        return "front-end/site";
    }
}