package be.d2l.controller;

import be.d2l.model.Comment;
import be.d2l.proxy.ProductProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/update/comment")
public class CommentUpdateController {

    @Autowired
    private ProductProxy proxy;

    public CommentUpdateController(ProductProxy proxy){
        this.proxy = proxy;
    }

    @GetMapping("/{id}")
    public String update(@PathVariable("id") int id, Model model){
        model.addAttribute("comment", proxy.getCommentById(id));
        return "updateComment";
    }

    @PostMapping("/{productId}/{commentId}")
    public ModelAndView updateComment(@PathVariable("productId") int productId, @PathVariable("commentId") int commentId, @ModelAttribute Comment comment){
        proxy.updateComment(commentId,comment);
        return new ModelAndView("redirect:/" + productId);
    }

}
