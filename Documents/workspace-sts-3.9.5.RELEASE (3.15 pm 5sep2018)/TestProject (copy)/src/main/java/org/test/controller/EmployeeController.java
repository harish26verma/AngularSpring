package org.test.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.test.model.Employee;
import org.test.service.UserService;

@Controller // annotation
@CrossOrigin

@RequestMapping(value = "/user")
public class EmployeeController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/addEmployeeData", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public @ResponseBody void addEmployeeData(final @RequestBody Employee request)
			throws SolrServerException, IOException {

		userService.add(request);

	}

	@RequestMapping(value = "/getEmployeeDataToSolr", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getEmployeeDataToSolr(final @RequestBody Employee request)
			throws IOException, SolrServerException {

		return userService.getEmployee(request.getUserId());

	}

//Delete employee
	@RequestMapping(value = "/deleteEmployeeDataFromSolr", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public @ResponseBody void deleteEmployeeDataFromSolr(final @RequestBody Employee request)
			throws IOException, SolrServerException {

		userService.delete(request.getUserId());
	}

}