package com.example.users.Repository;

import com.example.users.Model.Blog;
import com.example.users.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    Blog findBlogById(Integer id);
    List<Blog> findAllByUser(User user);
    Blog findBlogByTitle(String title);
}
