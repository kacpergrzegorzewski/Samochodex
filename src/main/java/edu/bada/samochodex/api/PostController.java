package edu.bada.samochodex.api;

import edu.bada.samochodex.model.Post;
import edu.bada.samochodex.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/poczty")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public String viewPostPage(Model model) {
        List<Post> posts = postService.getAll();
        model.addAttribute("posts", posts);

        return "posts/posts";
    }

    @GetMapping("/dodaj")
    public String showPostAddForm(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);

        return "posts/add_post";
    }

    @PostMapping("/zapisz")
    public String savePost(@ModelAttribute("poczta") Post post) {
        postService.save(post);

        return "redirect:/poczty";
    }

    @GetMapping("/edytuj/{id}")
    public ModelAndView showPostEditForm(@PathVariable("id") long id) {
        ModelAndView mav = new ModelAndView("posts/edit_post");

        Post post = postService.getById(id);
        mav.addObject("editPost", post);

        return mav;
    }

    @GetMapping("/usuń/{id}")
    public String deletePost(@PathVariable("id") long id) {
        postService.deleteById(id);

        return "redirect:/poczty";
    }
}
