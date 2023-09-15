package com.example.bookrecipe.Model.Repository;

import com.example.bookrecipe.Model.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByRecipeRecipeId(Long recipeId);
}
