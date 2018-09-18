package com.example.coffee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CoffeeRepository extends JpaRepository<Coffee, Integer>{//extends CrudRepository<Coffee, String> {
    Coffee findByName(String name);
    Coffee findById(int id);
   // void save(Model model);
    @Query("SELECT a FROM Coffee a WHERE a.isDeleted='n'")
    List<Coffee> findNotDeleted();
}