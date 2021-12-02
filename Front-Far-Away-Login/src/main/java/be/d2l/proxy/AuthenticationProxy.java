package be.d2l.proxy;

import be.d2l.model.User;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(name = "far-away-gateway", url = "localhost:9007")
public interface AuthenticationProxy {

    @PostMapping("/authentication/login")
    public ObjectNode login(@RequestBody User user);

    @PostMapping("/authentication/register")
    public ObjectNode register(@RequestBody User user);
}
