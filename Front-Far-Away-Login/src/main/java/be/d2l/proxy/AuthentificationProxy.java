package be.d2l.proxy;

import be.d2l.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(name = "far-away-gateway", url = "localhost:9007")
public interface AuthentificationProxy {

    @GetMapping("/users/login")
    public User login(User user);

    //GetMapping???
    //public User register(User user);
}
