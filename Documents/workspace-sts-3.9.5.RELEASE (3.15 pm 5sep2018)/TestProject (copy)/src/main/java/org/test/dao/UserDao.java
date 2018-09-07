package org.test.dao;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.test.model.Employee;

public interface UserDao {
		
    public void add(Employee user) throws SolrServerException, IOException;
    
    public void delete(String id) throws SolrServerException, IOException;
  	public HttpSolrServer getSolrServer();
  	public List<Map<String, Object>> getEmployee(String id);
    

}
