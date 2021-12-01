package be.d2l.proxy;

import be.d2l.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(name = "far-away-gateway", url = "localhost:9007")
public interface ProductProxy {

    @GetMapping("/suits")
    Iterable<Product> getProducts(@RequestParam(required = false) String category,
                              @RequestParam(required = false, defaultValue = "0")float min,
                              @RequestParam(required = false, defaultValue = "0") float max,
                              @RequestParam(required = false) String order);

    @PostMapping("/suits")
    void createProduct(@RequestBody Product product);

    @DeleteMapping("/suits/{id}")
    void deleteProduct(@PathVariable("id") int id);

    @GetMapping("/suits/{id}")
    Product getProduct(@PathVariable("id") int id);

    @PutMapping("/suits/{id}")
    void updateProduct(@PathVariable("id") int id, @RequestBody Product product);

}
