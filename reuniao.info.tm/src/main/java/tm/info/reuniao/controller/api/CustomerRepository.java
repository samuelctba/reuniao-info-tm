package tm.info.reuniao.controller.api;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import tm.info.reuniao.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {
	
	public Customer findByFirstName(String fisrtName);
	public List<Customer> findByLastName(String lastName);

}
