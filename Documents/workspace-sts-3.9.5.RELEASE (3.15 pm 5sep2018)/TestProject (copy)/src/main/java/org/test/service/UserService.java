package org.test.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.test.model.Employee;

public interface UserService {

	public void add(Employee todoEntry) throws SolrServerException, IOException;

	public HttpSolrServer getSolrServer();

	public void delete(String id) throws SolrServerException, IOException;

	public List<Map<String, Object>> getEmployee(String id);
}
