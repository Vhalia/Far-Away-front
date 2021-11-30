package be.d2l.proxy;

import be.d2l.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "far-away-gateway", url = "localhost:9007")
public interface ProductProxy {

    @GetMapping("/suits")
    Iterable<Product> getProducts(@RequestParam(required = false) String category,
                              @RequestParam(required = false, defaultValue = "0")float min,
                              @RequestParam(required = false, defaultValue = "0") float max,
                              @RequestParam(required = false) String order);
}
