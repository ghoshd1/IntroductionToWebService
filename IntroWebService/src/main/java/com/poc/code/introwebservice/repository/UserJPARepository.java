package com.poc.code.introwebservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc.code.introwebservice.model.User;

@Repository
public interface UserJPARepository extends JpaRepository<User, Integer>{

	
	
}
