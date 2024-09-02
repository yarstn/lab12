package com.example.users.Service;

import com.example.users.Model.Blog;
import com.example.users.Model.User;
import com.example.users.Repository.AuthRepository;
import com.example.users.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;

    public List<Blog> getAll() {
        return blogRepository.findAll();
    }
    public List<Blog> getMyBlog(Integer id) {
        User user = authRepository.findUserById(id);
        return blogRepository.findAllByUser(user);
    }
    public void addBlog(Integer user_id, Blog blog) {
        User user = authRepository.findUserById(user_id);
        if (user == null){
            throw new RuntimeException("user not found");
        }
        blog.setUser(user);
        blogRepository.save(blog);
    }
    public void updateBlog(Integer user_id, Blog blog, Integer id2) {
        User user = authRepository.findUserById(user_id);
        Blog to = blogRepository.findBlogById(id2);
        if (user == null){
            throw new RuntimeException("user not found");
        }
        if(to== null){
            throw new RuntimeException("blog not found");
        }
        if (to.getUser().getId() != user_id){
            throw new RuntimeException("sorry you dont have authority");
        }
        to.setTitle(blog.getTitle());
        to.setBody(blog.getBody());
        to.setUser(user);
        blogRepository.save(to);
    }
    public void deleteBlog(Integer user_id,Integer id) {
        User user = authRepository.findUserById(user_id);
        Blog to = blogRepository.findBlogById(id);
        if (user == null){
            throw new RuntimeException("user not found");
        }
        if (to == null){
            throw new RuntimeException("Blog not found");
        }
        if (to.getUser().getId() != user_id){
            throw new RuntimeException("sorry you dont have authority");
        }
        blogRepository.delete(to);
    }
    public Blog getBlogById(Integer user_id, Integer id) {
        User user = authRepository.findUserById(user_id);
        Blog to = blogRepository.findBlogById(id);
        if (user == null){
            throw new RuntimeException("user not found");
        }
        if (to == null){
            throw new RuntimeException("Blog not found");
        }
        if (to.getUser().getId() != user_id){
            throw new RuntimeException("sorry you dont have authority");
        }
       return to;
    }

    public Blog getBlogByTitle(Integer user_id,String title) {
        User user = authRepository.findUserById(user_id);
        Blog blog= blogRepository.findBlogByTitle(title);
        if (user == null){
            throw new RuntimeException("user not found");
        }
        if (blog == null){
            throw new RuntimeException("Blog not found");
        }
        // Check if the blog has a user associated
        if (blog.getUser() == null) {
            throw new RuntimeException("Blog does not have an associated user");
        }

        if (blog.getUser().getId() != user_id) {
            throw new RuntimeException("sorry you dont have authority");
        }
        return blog;
    }

}
