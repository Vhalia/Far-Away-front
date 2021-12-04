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
    Product getProduct(@PathVariable("id") int id);

    @GetMapping("/comments/average/{idProduct}")
    int averageRatingOfComment(@PathVariable("idProduct") int idProduct);

    @GetMapping("/users/{mail}")
    User getUserByMail(@PathVariable("mail") String mail);

    @GetMapping("/users/withid/{id}")
    User getUserById(@PathVariable("id") int id);

    @GetMapping("/comments/{idProduct}/{idUser}")
    Iterable<Comment> getCommentsByIdProductAndIdUser(@PathVariable("idProduct") int idProduct,@PathVariable("idUser") int idUser);

    @GetMapping("/comments/{idProduct}")
    Iterable<Comment> getCommentsByIdProductExceptUser(@PathVariable("idProduct") int idProduct,@RequestParam(required = false) Integer idUser);

    @PutMapping("/suits/{id}")
    Product updateProduct(@PathVariable("id") int id, @RequestBody Product product);

    @DeleteMapping("/suits/{id}")
    void deleteProduct(@PathVariable("id") int id);

    @DeleteMapping("/comments/{idComment}")
    void deleteComment(@PathVariable("idComment") int idComment);

    @PostMapping("/comments")
    Comment addComment(@RequestBody Comment newComment);

    @PostMapping("/baskets")
    Basket addProductToBasket(@RequestBody Basket newBasket);

    @GetMapping("/comments/commentbyid/{idComment}")
    Comment getCommentById(@PathVariable("idComment") int idComment);

    @PutMapping("/comments/{idComment}")
    void updateComment(@PathVariable("idComment") int idComment, @RequestBody Comment updateComment);

}
