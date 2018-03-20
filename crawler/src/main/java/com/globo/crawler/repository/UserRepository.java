package com.globo.crawler.repository;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends AbstractRepository {


	public boolean getUserByNameAndPassword(String name, String password) {
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		
		parameters.put("name", name);
		parameters.put("password", password);
		
		return sqlSession.selectOne("mappers.core.UserMapper.getUserByNameAndPassword",
				parameters);
	}

}
