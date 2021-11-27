package be.d2l.controller;

import be.d2l.proxy.ProductProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductProxy proxy;

    public ProductController(ProductProxy proxy){
        this.proxy = proxy;
    }
}
