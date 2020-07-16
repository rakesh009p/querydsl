package com.cgi.controller;

import java.util.List;

import com.cgi.model.Customer;
import com.cgi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cgi.service.QueryDSLService;

@RestController
public class QueryDSLController {

	@Autowired
	private QueryDSLService service;
	@Autowired
	private CustomerRepository repository;
	@PostMapping("/saveCustomer")
	public int saveCustomer(@RequestBody List<Customer> customers) {
		repository.save(customers);
		return customers.size();
	}

//	@GetMapping("/findAll")
//	public Iterable<Customer> findAllCustomers() {
//		return repository.findAll();
//	}

	@GetMapping("/findByFName/{firstName}")
	public List<Customer> findByFirstName(@PathVariable String firstName) {
		return repository.findByFirstname(firstName);
	}


	@GetMapping("/serachMultiField/{firstname}/{age}")
	public List<Customer> serachByMultiField(@PathVariable String firstname, @PathVariable int age) {
		return service.searchMultiField(firstname, age);
	}

	@GetMapping("/customSearch/{firstName}")
	public List<Customer> getCustomerByField(@PathVariable String firstName) {
		return service.getCustomerSerachData(firstName);
	}

	@GetMapping("/search/{text}")
	public List<Customer> doMultimatchQuery(@PathVariable String text) {
		return service.multiMatchQuery(text);
	}

}
