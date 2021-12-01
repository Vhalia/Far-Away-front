package be.d2l.controller;

import be.d2l.model.Basket;
import be.d2l.model.Product;
import be.d2l.proxy.BasketProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class BasketController {

    @Autowired
    private BasketProxy proxy;

    public BasketController(BasketProxy proxy){
        this.proxy = proxy;
    }

    @GetMapping("/{idUser}")
    public String getBasket(Model model,@PathVariable("idUser") int idUser){
        int sumPrice = 0;
        int numberProduct = 0;
        Product productToAdd;
        List<Basket> baskets = (List<Basket>) proxy.getBasketOfUser(idUser);

        for(Basket basket : baskets){
            productToAdd = proxy.getProduct(basket.getIdProduct());
            basket.setProduct(productToAdd);
            numberProduct += basket.getQuantity();
            sumPrice += productToAdd.getPrice() * basket.getQuantity();
        }

        model.addAttribute("baskets", baskets);
        model.addAttribute("sumPrice",sumPrice);
        model.addAttribute("numberProduct", numberProduct);
        return "baskets";
    }

    @PostMapping("delete/baskets/{idProduct}/{idUser}")
    public ModelAndView delete(@PathVariable("idProduct") int idProduct, @PathVariable("idUser") int idUser){
        Basket basketToDelete = new Basket();
        basketToDelete.setIdProduct(idProduct);
        basketToDelete.setIdUser(idUser);
        proxy.deleteProductOfBasket(basketToDelete);
        return new ModelAndView("redirect:/" + idUser);
    }

    @GetMapping("/update/baskets/{idUser}/{idProduct}")
    public ModelAndView update(@PathVariable("idProduct") int idProduct, @PathVariable("idUser") int idUser, @RequestParam int quantity){
        Basket basketToUpdate = new Basket();
        basketToUpdate.setIdProduct(idProduct);
        basketToUpdate.setIdUser(idUser);
        proxy.updateProductQuantity(basketToUpdate, quantity);
        return new ModelAndView("redirect:/" + idUser);

    }
}
