package be.d2l.controller;

import be.d2l.model.User;
import be.d2l.proxy.AuthenticationProxy;
import be.d2l.util.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/")
public class AuthenticationController {

    private AuthenticationProxy proxy;

    private Utils util;

    private ObjectMapper jsonMapper;

    public AuthenticationController(AuthenticationProxy proxy, Utils util) {
        this.proxy = proxy;
        this.util = util;
        this.jsonMapper = new ObjectMapper();
    }

    @GetMapping
    public String loginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/authentication/login")
    public ModelAndView login(@ModelAttribute User user, HttpServletResponse response) {
        ObjectNode node = proxy.login(user);
        User userLogged = jsonMapper.convertValue(node.get("user"), User.class);
        String token = jsonMapper.convertValue(node.get("token"), String.class);
        Cookie cookie = util.createCookie(token);
        response.addCookie(cookie);
        return new ModelAndView(new RedirectView("http://localhost:8003/" + userLogged.getId())); //TODO:Redirect to home

    }

    @PostMapping("/authentication/register")
    public ModelAndView register(@ModelAttribute User user, Model model, HttpServletResponse response) {
        ObjectNode node = proxy.register(user);
        User userLogged = jsonMapper.convertValue(node.get("user"), User.class);
        String token = jsonMapper.convertValue(node.get("token"), String.class);
        Cookie cookie = util.createCookie(token);
        response.addCookie(cookie);
        model.addAttribute("user", userLogged);
        //return new ModelAndView(new RedirectView("http://localhost:8002/")); TODO:Redirect to home
        return new ModelAndView("redirect:/");
    }
}
