package com.example.blogamine.Repository;

import com.example.blogamine.Entite.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, String> {
    Blog findBlogByBlogCode(String code);
    List<Blog> findBlogByStatusIs(Boolean status);
}
