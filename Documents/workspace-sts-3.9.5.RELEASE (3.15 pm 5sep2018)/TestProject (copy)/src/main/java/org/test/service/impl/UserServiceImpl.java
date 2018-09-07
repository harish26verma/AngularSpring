package org.test.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.test.dao.UserDao;
import org.test.model.Employee;
import org.test.service.UserService;

@Service("userService")
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Transactional
	@Override
	public void add(Employee user) throws SolrServerException, IOException {

		userDao.add(user);

	}

	@Transactional
	@Override
	public void delete(String id) throws SolrServerException, IOException {
		userDao.delete(id);
	}

	public HttpSolrServer getSolrServer() {
		return userDao.getSolrServer();
	}

	@Override
	public List<Map<String, Object>> getEmployee(String id) {

		return userDao.getEmployee(id);

	}

}
