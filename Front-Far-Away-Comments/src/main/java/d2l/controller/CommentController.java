package d2l.controller;

import d2l.model.Comment;
import d2l.proxy.CommentProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class CommentController {

    @Autowired
    private CommentProxy proxy;

    public CommentController(CommentProxy proxy) {
        this.proxy = proxy;
    }

    @GetMapping
    public String getAll(Model model) {
        List<Comment> comments = (List<Comment>) proxy.findAll();
        model.addAttribute("comments", comments.stream().sorted(Comment.commentComparator).collect(Collectors.toList()));
        return "comments";
    }
}
