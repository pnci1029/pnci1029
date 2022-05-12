package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface Articlerepository extends CrudRepository<Article, Long> {


}
