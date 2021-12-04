package be.d2l.controller;

import be.d2l.model.Basket;
import be.d2l.model.Comment;
import be.d2l.model.Product;
import be.d2l.proxy.ProductProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductProxy proxy;

    public ProductController(ProductProxy proxy){
        this.proxy = proxy;
    }

    @GetMapping("/{id}")
    public String ProductPage(@PathVariable("id") int productId, Model model){

        int userId = 2; //TODO change to id in token
        model.addAttribute("emptyComment", new Comment()); //Used for writing new comments

        //Product info
        Product product = proxy.getProduct(productId);
        model.addAttribute("product", product);

        //Average product rating
        String averageRating;
        if(proxy.averageRatingOfComment(productId) != -1) {
            averageRating = proxy.averageRatingOfComment(productId) + "/5";
        } else {
            averageRating = "Ce produit n'as pas encore été évalué";
        }
        model.addAttribute("averageRating", averageRating);

        //Ratings & comments of the active user
        Iterable<Comment> currentUserComments = proxy.getCommentsByIdProductAndIdUser(productId, userId);
        model.addAttribute("currentUserComments", currentUserComments);

        //Ratings & comments of other users
        Iterable<Comment> otherUsersComments = proxy.getCommentsByIdProductExceptUser(productId, userId);
        for (Comment comment: otherUsersComments) {
            comment.setAuthor(proxy.getUserById(comment.getIdUser()));
        }
        model.addAttribute("otherUsersComments", otherUsersComments);

        return "product";
    }

    @PostMapping("delete/product/{id}")
    public ModelAndView deleteSuit(@PathVariable("id") int id, Model model){
        proxy.deleteProduct(id);
        return new ModelAndView(new RedirectView("http://localhost:8002/"));
    }

    @PostMapping("delete/comment/{productId}/{commentId}")
    public ModelAndView deleteComment(@PathVariable("productId") int productId,@PathVariable("commentId") int commentId, Model model){
        proxy.deleteComment(commentId);
        return new ModelAndView("redirect:/" + productId);
    }

    @PostMapping("addComment/{productId}")
    public ModelAndView addComment(@PathVariable("productId") int productId,@ModelAttribute Comment comment, Model model){
        comment.setIdUser(2);//TODO set this with token value
        comment.setIdProduct(productId);
        proxy.addComment(comment);
        return new ModelAndView("redirect:/" + productId);
    }

    @PostMapping("addToBasket/{productId}")
    public ModelAndView addToBasket(@PathVariable("productId") int productId, Basket basket, Model model){
        basket.setIdUser(2);//TODO set this with token value
        basket.setIdProduct(productId);
        proxy.addProductToBasket(basket);
        return new ModelAndView("redirect:/" + productId);
    }

}
