package kz.coube.admin.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminWebController {

    @GetMapping(value = "/admin", produces = "text/html")
    @ResponseBody
    public Resource showAdminPage() {
        return new ClassPathResource("internal/admin.html");
    }
}
