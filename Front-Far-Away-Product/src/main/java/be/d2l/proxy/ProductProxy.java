package be.d2l.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient(name = "far-away-gateway", url = "localhost:9007")
public class ProductProxy {


}
