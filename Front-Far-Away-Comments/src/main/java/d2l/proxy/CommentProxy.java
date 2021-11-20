package d2l.proxy;

import d2l.model.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(name = "far-away-gateway", url = "localhost:9007")
public interface CommentProxy {

    @GetMapping("/comments")
    Iterable<Comment> findAll();
}
