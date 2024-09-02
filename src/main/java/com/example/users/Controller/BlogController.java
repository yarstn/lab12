package com.example.users.Controller;

import com.example.users.Model.Blog;
import com.example.users.Model.User;
import com.example.users.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/blog")
@RestController //json
public class BlogController {
    private final BlogService blogService;


    @GetMapping("/get-all")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(blogService.getAll());
    }
@GetMapping("/get-my")
    public ResponseEntity getBlogs(@AuthenticationPrincipal User user) {
        blogService.getMyBlog(user.getId());
        return ResponseEntity.status(200).body(  blogService.getMyBlog(user.getId()));
    }

    @PostMapping("/add")
    public  ResponseEntity addBlog(@AuthenticationPrincipal User user, @RequestBody @Valid Blog blog){
        blogService.addBlog(user.getId(), blog);
        return ResponseEntity.status(200).body( "Blog added successfully");
    }
    @PutMapping("/update/{id2}")
    public  ResponseEntity updateBlog(@AuthenticationPrincipal User user, @RequestBody @Valid Blog blog, @PathVariable Integer id2){
        blogService.updateBlog(user.getId(), blog, id2);
        return ResponseEntity.status(200).body( "Blog updated successfully");
    }
    @DeleteMapping("/delete/{id}")
    public  ResponseEntity deleteBlog(@AuthenticationPrincipal User user, @PathVariable Integer id){
        blogService.deleteBlog(user.getId(), id);
        return ResponseEntity.status(200).body( "Blog deleted successfully");
    }
    @GetMapping("/get/id/{id}")
    public ResponseEntity getBlogById(@AuthenticationPrincipal User user, @PathVariable Integer id){
        return ResponseEntity.status(200).body(blogService.getBlogById(user.getId(),id));
    }
    @GetMapping("/get/title/{title}")
    public ResponseEntity getBlogByTitle(@AuthenticationPrincipal User user, @PathVariable String title){
        return ResponseEntity.status(200).body(blogService.getBlogByTitle(user.getId(), title));
    }
}
