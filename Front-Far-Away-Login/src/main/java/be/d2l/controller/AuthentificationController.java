package be.d2l.controller;

import be.d2l.proxy.AuthentificationProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AuthentificationController {

    @Autowired
    private AuthentificationProxy proxy;

    public AuthentificationController(AuthentificationProxy proxy) {
        this.proxy = proxy;
    }

    @GetMapping
    public String loginPage() {
        return "login";
    }
}
