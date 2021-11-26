package be.d2l.proxy;

import be.d2l.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(name = "far-away-gateway", url = "localhost:9007")
public interface AuthentificationProxy {

    @PostMapping("/users/login")
    public User login(User user);

    //GetMapping???
    //public User register(User user);
}
