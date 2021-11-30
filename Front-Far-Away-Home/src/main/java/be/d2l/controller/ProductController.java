package be.d2l.controller;

import be.d2l.model.Product;
import be.d2l.proxy.ProductProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductProxy proxy;

    public ProductController(ProductProxy proxy){
        this.proxy = proxy;
    }

    @GetMapping
    public String HomePage(@RequestParam(required = false, defaultValue = "0")float min,
                           @RequestParam(required = false, defaultValue = "0") float max,
                           @RequestParam(required = false) String order, Model model) {
        model.addAttribute("productListSpace", proxy.getProducts("espace", min, max, order));
        model.addAttribute("productListSubmarine", proxy.getProducts("sous-marin", min, max, order));
        return "home";
    }
}
