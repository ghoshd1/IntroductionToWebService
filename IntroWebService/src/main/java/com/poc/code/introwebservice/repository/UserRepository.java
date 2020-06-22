package com.poc.code.introwebservice.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.poc.code.introwebservice.model.User;

@Repository
public class UserRepository {
	
	private static int userid=0;
	
	private static List<User> userList = new ArrayList<>();
	
	static {
		userList.add(new User(++userid,"Deb",28));
		userList.add(new User(++userid,"Abhishek",29));
		userList.add(new User(++userid,"Ashish",28));
	}
	
	public List<User> getAllUser() {
		return userList;
	}

	public Optional<User> findById(int id) {
		Iterator<User> iterator = userList.iterator();
		while(iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId()==id) {
				return Optional.of(user);
			}
		}
		return Optional.empty();
	}

	public User save(User user) {
		user = new User(++userid,user.getName(),user.getAge());
		userList.add(user);
		return user;
	}

	public Optional<User> deleteById(int id) {
		Iterator<User> iterator = userList.iterator();
		while(iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId()==id) {
				iterator.remove();
				return Optional.of(user);
			}
		}
		return Optional.empty();
	}
}
