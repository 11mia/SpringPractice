package com.example.coffee;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CoffeeRepository extends JpaRepository<Coffee, Integer>{//extends CrudRepository<Coffee, String> {
    Coffee findByName(String name);
}