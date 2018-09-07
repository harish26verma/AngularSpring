package org.test.dao.impl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Repository;
import org.test.dao.UserDao;
import org.test.model.Employee;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Override
	public void add(Employee user) throws SolrServerException, IOException {
		SolrServer client = getSolrServer();
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("fname", user.getFname());
		// doc.addField("id", UUID.randomUUID().toString());
		doc.addField("lname", user.getLname());
		doc.addField("userId", user.getUserId());
		doc.addField("mobile", user.getMobile());
		doc.addField("email", user.getEmail());
		client.add(doc);
		client.commit();
		
	}

	@Override
	public void delete(String id) throws SolrServerException, IOException {
		SolrServer client = getSolrServer();
		client.deleteByQuery("userId:" +id);
		client.commit();

	}

	@Override
	public HttpSolrServer getSolrServer() {
		return new HttpSolrServer("http://localhost:8983/solr/employeeSOlr");
	}

	@Override
	public List<Map<String, Object>> getEmployee(String id) {
		
		List<Map<String, Object>> docs = new ArrayList<>();
		HttpSolrServer client=getSolrServer();
		SolrQuery parameters = new SolrQuery();
		parameters.set("q", "*:*");
		try {
			client.commit();
			QueryResponse response = client.query(parameters);
			for (SolrDocument solrDocument : response.getResults()) {
				docs.add(solrDocument);
			}
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}
		return docs;
		// TODO Auto-generated method stub
		
	} 
	
	

}
