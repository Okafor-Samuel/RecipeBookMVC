package com.example.bookrecipe.Model.Repository;


import com.example.bookrecipe.Model.Service.UserFavouriteRecipes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserFavouriteRecipesRepository extends JpaRepository<UserFavouriteRecipes, Long> {
    List<UserFavouriteRecipes> findByUserUserId(Long userId);
}
