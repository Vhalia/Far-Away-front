package be.d2l.controller;

import be.d2l.model.Basket;
import be.d2l.model.Comment;
import be.d2l.model.Product;
import be.d2l.proxy.ProductProxy;
import be.d2l.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
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

    @GetMapping("/{id}")
    public String ProductPage(@PathVariable("id") int productId, Model model, @CookieValue(value = "token", defaultValue = "none") String token){

        int userId = utils.getIntFromPayloadWithName("userId", token);

        model.addAttribute("emptyComment", new Comment()); //Used for writing new comments

        //Product info
        Product product = proxy.getProduct(productId, token);
        model.addAttribute("product", product);

        //Average product rating
        double averageRating = proxy.averageRatingOfComment(productId, token);
        String averageRatingToDisplay = null;
        if(proxy.averageRatingOfComment(productId, token) != -1) {
            averageRatingToDisplay = String.format("%.1f", averageRating) + "/5";
        } else {
            averageRatingToDisplay = "Ce produit n'as pas encore été évalué";
        }
        model.addAttribute("averageRating", averageRatingToDisplay);

        //Ratings & comments of the active user
        Iterable<Comment> currentUserComments = proxy.getCommentsByIdProductAndIdUser(productId, userId, token);
        List<Comment> currentUserCommentsToDisplay = new ArrayList<Comment>();
        for (Comment comment: currentUserComments) {
            if(!comment.getIsDeleted())
                currentUserCommentsToDisplay.add(comment);
        }
        model.addAttribute("currentUserComments", currentUserCommentsToDisplay);

        //Ratings & comments of other users
        Iterable<Comment> otherUsersComments = proxy.getCommentsByIdProductExceptUser(productId, userId, token);
        List<Comment> otherUsersCommentsToDisplay = new ArrayList<Comment>();
        for (Comment comment: otherUsersComments) {
            if(!comment.getIsDeleted()){
                comment.setAuthor(proxy.getUserById(comment.getIdUser(), token));
                otherUsersCommentsToDisplay.add(comment);
            }
        }
        model.addAttribute("otherUsersComments", otherUsersCommentsToDisplay);

        return "product";
    }

    @PostMapping("delete/product/{id}")
    public ModelAndView deleteSuit(@PathVariable("id") int id, Model model, @CookieValue(value = "token", defaultValue = "none") String token){
        proxy.deleteProduct(id, token);
        return new ModelAndView(new RedirectView("http://localhost:8002/"));
    }

    @PostMapping("delete/comment/{productId}/{commentId}")
    public ModelAndView deleteComment(@PathVariable("productId") int productId,@PathVariable("commentId") int commentId, Model model, @CookieValue(value = "token", defaultValue = "none") String token){
        Comment deletedComment = proxy.getCommentById(commentId, token);
        deletedComment.setIsDeleted(true);
        proxy.updateComment(commentId, deletedComment, token);
        return new ModelAndView("redirect:/" + productId);
    }

    @PostMapping("addComment/{productId}")
    public ModelAndView addComment(@PathVariable("productId") int productId,@ModelAttribute Comment comment, Model model, @CookieValue(value = "token", defaultValue = "none") String token){
        comment.setIdUser(utils.getIntFromPayloadWithName("userId", token));
        comment.setIdProduct(productId);
        proxy.addComment(comment, token);
        return new ModelAndView("redirect:/" + productId);
    }

    @PostMapping("addToBasket/{productId}")
    public ModelAndView addToBasket(@PathVariable("productId") int productId, Basket basket, Model model, @CookieValue(value = "token", defaultValue = "none") String token){
        basket.setIdUser(utils.getIntFromPayloadWithName("userId", token));
        basket.setIdProduct(productId);
        proxy.addProductToBasket(basket, token);
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

    @GetMapping("/authentification")
    public ModelAndView toAuthentificationPage(Model model){
        return new ModelAndView(new RedirectView("http://localhost:8001/"));
    }

}
