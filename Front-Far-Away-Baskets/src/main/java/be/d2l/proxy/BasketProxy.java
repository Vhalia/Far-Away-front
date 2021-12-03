package be.d2l.proxy;

import be.d2l.model.Basket;
import be.d2l.model.Product;
import be.d2l.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(name = "far-away-gateway", url = "localhost:9007")
public interface BasketProxy {

    @GetMapping("/baskets/{idUser}")
    Iterable<Basket> getBasketOfUser(@PathVariable("idUser") int idUser,@RequestHeader(name = "Authorization", required = false) String token);

    @GetMapping("/suits/{id}")
    Product getProduct(@PathVariable("id") int id,@RequestHeader(name = "Authorization", required = false) String token);

    @DeleteMapping("/baskets")
    void deleteProductOfBasket(@RequestBody Basket BasketToDelete,@RequestHeader(name = "Authorization", required = false) String token);

    @PutMapping("/baskets")
    Basket updateProductQuantity(@RequestBody Basket BasketToUpdate, @RequestParam int quantity,@RequestHeader(name = "Authorization", required = false) String token);

    @GetMapping("users/{mail}")
    User getUserByMail(@PathVariable("mail") String mail,@RequestHeader(name = "Authorization", required = false) String token);


}
