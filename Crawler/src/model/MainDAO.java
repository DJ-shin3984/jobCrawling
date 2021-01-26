package model;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MainDAO {
	@Autowired
	private SqlSessionFactory factory;


}

