package be.d2l.controller;

import be.d2l.proxy.ProductProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/update/product")
public class ProductUpdateController {

    @Autowired
    private ProductProxy proxy;

    public ProductUpdateController(ProductProxy proxy){
        this.proxy = proxy;
    }

    @GetMapping("/{id}")
    public ModelAndView updateProduct(@PathVariable("id") int productId, Model model){
        return new ModelAndView(new RedirectView("http://localhost:8002/update/" + productId));
    }
}
