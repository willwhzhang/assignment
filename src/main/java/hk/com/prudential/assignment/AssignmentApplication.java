package hk.com.prudential.assignment;

import hk.com.prudential.assignment.dao.CarRepository;
import hk.com.prudential.assignment.dao.CustomerRepository;
import hk.com.prudential.assignment.entity.Car;
import hk.com.prudential.assignment.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class AssignmentApplication {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CarRepository carRepository;

	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);
	}

	@PostConstruct
	public void init() {

		long count = customerRepository.count();
		if (count == 0) {
			Customer customer = new Customer();
			customer.setName("Will");
			customer.setEmail("will@example.com");
			customerRepository.save(customer);
		}

		long count1 = carRepository.count();
		if (count1 == 0) {
			Car toyotaCar = new Car();
			toyotaCar.setModel("Toyota Camry");
			toyotaCar.setStock(2);
			carRepository.save(toyotaCar);

			Car bmwCar = new Car();
			bmwCar.setModel("BMW 650");
			bmwCar.setStock(2);
			carRepository.save(bmwCar);
		}

	}

}
