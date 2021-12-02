package be.d2l.controller;

import be.d2l.model.Product;
import be.d2l.proxy.ProductProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/update")
public class UpdateController {

    @Autowired
    private ProductProxy proxy;

    public UpdateController(ProductProxy proxy){
        this.proxy = proxy;
    }

    @GetMapping("/{id}")
    public String update(@PathVariable("id") int id, Model model) {
        model.addAttribute("suit", proxy.getProduct(id));
        return "updateSuit";
    }

    @PostMapping("/{id}")
    public ModelAndView updateSuit(@PathVariable("id") int id, @ModelAttribute Product product, @CookieValue(value = "token", defaultValue = "none") String token) {
        proxy.updateProduct(id, product, token);
        return new ModelAndView("redirect:/");
    }
}
