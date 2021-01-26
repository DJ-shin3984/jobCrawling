package model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainService {
	
	private MainDAO dao;

	@Autowired
	public MainService(MainDAO mainDao) {
		super();
		this.dao = mainDao;
	}
	


	
}