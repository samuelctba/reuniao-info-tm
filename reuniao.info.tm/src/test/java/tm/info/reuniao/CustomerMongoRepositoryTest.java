package tm.info.reuniao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tm.info.reuniao.controller.api.CustomerRepository;
import tm.info.reuniao.model.Customer;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerMongoRepositoryTest {
	@Autowired
	private CustomerRepository userMongoRepository;

	@Before
	public void setUp() throws Exception {
		Customer user1 = new Customer("Samuel", "Camargo");
		Customer user2 = new Customer("Dhebora", "Camargo");
		// save product, verify has ID value after save
		assertNull(user1.getId());
		assertNull(user2.getId());// null before save
		this.userMongoRepository.save(user1);
		this.userMongoRepository.save(user2);
		assertNotNull(user1.getId());
		assertNotNull(user2.getId());
	}

	@Test
	public void testFetchData() {
		/* Test data retrieval */
		Customer userA = userMongoRepository.findByFirstName("Samuel");
		assertNotNull(userA);
		assertEquals("Samuel", userA.getFirstName());
		/* Get all products, list should only have two */
		Iterable<Customer> users = userMongoRepository.findAll();
		int count = 0;
		for (Customer p : users) {
			count++;
		}
		System.out.println("count: " + count);
		assertEquals(count, 2);
	}

	@Test
	public void testDataUpdate() {
		/* Test update */
		Customer userB = userMongoRepository.findByFirstName("Dhebora");
		userB.setFirstName("Bob");
		userMongoRepository.save(userB);
		Customer userC = userMongoRepository.findByFirstName("Bob");
		assertNotNull(userC);
		assertEquals("Bob", userC.getFirstName());
	}

	@After
	public void tearDown() throws Exception {
		this.userMongoRepository.deleteAll();
	}

}
