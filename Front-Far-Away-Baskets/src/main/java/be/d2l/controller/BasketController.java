package be.d2l.controller;

import be.d2l.Utils.Utils;
import be.d2l.model.Basket;
import be.d2l.model.Product;
import be.d2l.model.User;
import be.d2l.proxy.BasketProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/")
public class BasketController {

    @Autowired
    private BasketProxy proxy;
    private Utils utils;

    public BasketController(BasketProxy proxy, Utils utils){
        this.proxy = proxy;
        this.utils = utils;
    }

    @GetMapping("/{idUser}")
    public String getBasket(Model model,@PathVariable("idUser") int idUser,@CookieValue(value = "token", defaultValue = "none") String token){

        int sumPrice = 0;
        int numberProduct = 0;
        Product productToAdd;
        List<Basket> baskets = (List<Basket>) proxy.getBasketOfUser(idUser,token);

        for(Basket basket : baskets){
            productToAdd = proxy.getProduct(basket.getIdProduct(), token);
            basket.setProduct(productToAdd);
            numberProduct += basket.getQuantity();
            sumPrice += productToAdd.getPrice() * basket.getQuantity();
        }
        model.addAttribute("emailUser", utils.getStringFromPayloadWithName("mail", token));
        model.addAttribute("idUser", utils.getIntFromPayloadWithName("userId", token));
        model.addAttribute("baskets", baskets);
        model.addAttribute("sumPrice",sumPrice);
        model.addAttribute("numberProduct", numberProduct);
        return "baskets";
    }

    @PostMapping("delete/baskets/{idProduct}/{idUser}")
    public ModelAndView delete(@PathVariable("idProduct") int idProduct, @PathVariable("idUser") int idUser,@CookieValue(value = "token", defaultValue = "none") String token){
        Basket basketToDelete = new Basket();
        basketToDelete.setIdProduct(idProduct);
        basketToDelete.setIdUser(idUser);
        proxy.deleteProductOfBasket(basketToDelete, token);
        return new ModelAndView("redirect:/" + idUser);
    }

    @GetMapping("/update/baskets/{idUser}/{idProduct}")
    public ModelAndView update(@PathVariable("idProduct") int idProduct, @PathVariable("idUser") int idUser, @RequestParam int quantity,@CookieValue(value = "token", defaultValue = "none") String token){
        Basket basketToUpdate = new Basket();
        basketToUpdate.setIdProduct(idProduct);
        basketToUpdate.setIdUser(idUser);
        proxy.updateProductQuantity(basketToUpdate, quantity, token);
        return new ModelAndView("redirect:/" + idUser);

    }

    @PostMapping("/pay/baskets/{idUser}")
    public String payBasket(Model model, @PathVariable("idUser") int idUser, @CookieValue(value = "token", defaultValue = "none") String token){

        List<Basket> baskets = (List<Basket>) proxy.getBasketOfUser(idUser, token);
        for(Basket basket : baskets){
            proxy.deleteProductOfBasket(basket, token);
        }
        User user = proxy.getUserByMail(utils.getStringFromPayloadWithName("mail", token), token);
        model.addAttribute("user", user);
        return "pay";
    }

    @GetMapping("/home")
    public ModelAndView toBasketPage(Model model){
        return new ModelAndView(new RedirectView("http://localhost:8002"));
    }
}
