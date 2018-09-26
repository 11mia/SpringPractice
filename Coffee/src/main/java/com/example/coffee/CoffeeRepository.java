package com.example.coffee;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CoffeeRepository extends JpaRepository<Coffee, Integer>{//extends CrudRepository<Coffee, String> {
    Coffee findByName(String name);
    Coffee findById(int id);
    List<Coffee> findAll(Sort sort);
    Coffee findByTotid(String totid);
    List<Coffee> findByTotidStartingWith(String dummy);
    @Query
   List<Coffee> findByTotidStartingWithAndIsdeletedLike(String dummy,char tf);
   @Query
    List<Coffee> findByIsdeletedLike(char tf,Sort sort);
    @Query
    List<Coffee> findByIsdeletedLike(char tf);

    List<Coffee> findByTotidEndingWith(String cid);
    void deleteByTotid(String totid);
    //void deleteAllByTotid(String totid);
    
    @Query
    List<Coffee> findByTotidStartingWithAndIsdeletedLike(String dummy,char tf,Sort sort);

}