package be.d2l.controller;

import be.d2l.model.Comment;
import be.d2l.proxy.ProductProxy;
import be.d2l.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/update/comment")
public class CommentUpdateController {

    @Autowired
    private ProductProxy proxy;
    private Utils utils;

    public CommentUpdateController(ProductProxy proxy, Utils utils){
        this.proxy = proxy;
        this.utils = utils;

    }

    @GetMapping("/{id}")
    public String update(@PathVariable("id") int id, Model model, @CookieValue(value = "token", defaultValue = "none") String token){
        model.addAttribute("comment", proxy.getCommentById(id, token));
        return "updateComment";
    }

    @PostMapping("/{productId}/{commentId}")
    public ModelAndView updateComment(@PathVariable("productId") int productId, @PathVariable("commentId") int commentId, @ModelAttribute Comment comment, @CookieValue(value = "token", defaultValue = "none") String token){
        proxy.updateComment(commentId,comment, token);
        return new ModelAndView("redirect:/" + productId);
    }

    @GetMapping("/home")
    public ModelAndView toBasketPage(Model model){
        return new ModelAndView(new RedirectView("http://localhost:8002"));
    }
    @GetMapping("/basket")
    public ModelAndView toBasketPage(Model model,  @CookieValue(value = "token", defaultValue = "none") String token){
        int id = utils.getIntFromPayloadWithName("userId",token);
        return new ModelAndView(new RedirectView("http://localhost:8003/" + id));
    }


}
