package com.tweetapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.model.Reply;

@Repository
public interface ReplyRepositoy extends JpaRepository<Reply, Integer> {
	
	List<Reply> findAll();

}
