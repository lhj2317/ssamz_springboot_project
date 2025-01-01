package com.ssamz.jblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssamz.jblog.domain.Post; 

public interface PostRepository extends JpaRepository<Post, Integer> {

}
