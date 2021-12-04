package be.d2l.proxy;

import be.d2l.model.Basket;
import be.d2l.model.Comment;
import be.d2l.model.Product;
import be.d2l.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(name = "far-away-gateway", url = "localhost:9007")
public interface ProductProxy {

    @GetMapping("/suits/{id}")
    Product getProduct(@PathVariable("id") int id, @RequestHeader(name = "Authorization", required = false) String token);

    @GetMapping("/comments/average/{idProduct}")
    int averageRatingOfComment(@PathVariable("idProduct") int idProduct, @RequestHeader(name = "Authorization", required = false) String token);

    @GetMapping("/users/{mail}")
    User getUserByMail(@PathVariable("mail") String mail, @RequestHeader(name = "Authorization", required = false) String token);

    @GetMapping("/users/withid/{id}")
    User getUserById(@PathVariable("id") int id, @RequestHeader(name = "Authorization", required = false) String token);

    @GetMapping("/comments/{idProduct}/{idUser}")
    Iterable<Comment> getCommentsByIdProductAndIdUser(@PathVariable("idProduct") int idProduct,@PathVariable("idUser") int idUser, @RequestHeader(name = "Authorization", required = false) String token);

    @GetMapping("/comments/{idProduct}")
    Iterable<Comment> getCommentsByIdProductExceptUser(@PathVariable("idProduct") int idProduct,@RequestParam(required = false) Integer idUser, @RequestHeader(name = "Authorization", required = false) String token);

    @PutMapping("/suits/{id}")
    Product updateProduct(@PathVariable("id") int id, @RequestBody Product product, @RequestHeader(name = "Authorization", required = false) String token);

    @DeleteMapping("/suits/{id}")
    void deleteProduct(@PathVariable("id") int id, @RequestHeader(name = "Authorization", required = false) String token);

    @PutMapping("/comments/{idComment}")
    Comment updateComment(@PathVariable("idComment") int idComment, @RequestBody Comment updateComment, @RequestHeader(name = "Authorization", required = false) String token);

    @PostMapping("/comments")
    Comment addComment(@RequestBody Comment newComment, @RequestHeader(name = "Authorization", required = false) String token);

    @PostMapping("/baskets")
    Basket addProductToBasket(@RequestBody Basket newBasket, @RequestHeader(name = "Authorization", required = false) String token);

    @GetMapping("/comments/commentbyid/{idComment}")
    Comment getCommentById(@PathVariable("idComment") int idComment, @RequestHeader(name = "Authorization", required = false) String token);

}
