package be.d2l.controller;

import be.d2l.model.Product;
import be.d2l.proxy.ProductProxy;
import be.d2l.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductProxy proxy;
    private Utils utils;

    public ProductController(ProductProxy proxy, Utils utils){

        this.proxy = proxy;
        this.utils = utils;
    }

    @GetMapping
    public String HomePage(@RequestParam(required = false, defaultValue = "0")float min,
                           @RequestParam(required = false, defaultValue = "0") float max,
                           @RequestParam(required = false) String order, Model model,
                           @CookieValue(value = "token", defaultValue = "none") String token) {
        model.addAttribute("connected", !token.equals("none"));
        model.addAttribute("productListSpace", proxy.getProducts("espace", min, max, order));
        model.addAttribute("productListSubmarine", proxy.getProducts("sous-marin", min, max, order));
        return "home";
    }

    @GetMapping("addSuit")
    public String AddingPage(Model model, @CookieValue(value = "token", defaultValue = "none") String token){
        model.addAttribute("connected", !token.equals("none"));
        model.addAttribute("suit", new Product());
        return "addSuit";
    }

    @PostMapping("suits")
    public ModelAndView createSuit(@ModelAttribute Product suit, @CookieValue(value = "token", defaultValue = "none") String token){
        proxy.createProduct(suit, token);
        return new ModelAndView("redirect:/");
    }

    @PostMapping("delete/suits/{id}")
    public ModelAndView deleteSuit(@PathVariable("id") int id, Model model, @CookieValue(value = "token", defaultValue = "none") String token){
        proxy.deleteProduct(id, token);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/basket")
    public ModelAndView toBasketPage(Model model,  @CookieValue(value = "token", defaultValue = "none") String token){
        int id = utils.getIntFromPayloadWithName("userId",token);
        return new ModelAndView(new RedirectView("http://localhost:8003/" + id));
    }

    @GetMapping("/authentification")
    public ModelAndView toAuthentificationPage(Model model){
        return new ModelAndView(new RedirectView("http://localhost:8001/"));
    }
}
