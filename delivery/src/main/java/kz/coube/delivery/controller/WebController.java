package kz.coube.delivery.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {

    @GetMapping(value = "/calculate", produces = "text/html")
    @ResponseBody
    public Resource showCalculatePage() {
        return new ClassPathResource("internal/calculate.html");
    }
}

