package hk.com.prudential.assignment.service;

import hk.com.prudential.assignment.common.DataResult;
import hk.com.prudential.assignment.dao.CarRepository;
import hk.com.prudential.assignment.dao.CustomerRepository;
import hk.com.prudential.assignment.dao.OrderRepository;
import hk.com.prudential.assignment.entity.Car;
import hk.com.prudential.assignment.entity.Customer;
import hk.com.prudential.assignment.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class RentService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public DataResult<Integer> rentCar(Integer customerId, Integer carId, Date startTime, Date endTime) {

        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isEmpty()) {
            return DataResult.error("The customer does not exist.");
        }

        Optional<Car> carOptional = carRepository.findById(carId);
        if (carOptional.isEmpty()) {
            return DataResult.error("The car does not exist.");
        }

        Car car = carOptional.get();
        if (car.getStock() <= 0) {
            return DataResult.error("The car is out of stock");
        }

        Order order = new Order();
        order.setCustomerId(customerId);
        order.setCarId(carId);
        order.setStartTime(startTime);
        order.setEndTime(endTime);
        // renting
        order.setStatus(0);
        orderRepository.save(order);

        car.setStock(car.getStock() - 1);
        carRepository.save(car);

        return DataResult.success(order.getId());

    }

    @Transactional
    public DataResult<Void> returnCar(Integer customerId, Integer orderId) {

        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isEmpty()) {
            return DataResult.error("The customer does not exist.");
        }

        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isEmpty()) {
            return DataResult.error("The order does not exist.");
        }

        Order order = orderOptional.get();
        Integer carId = order.getCarId();

        if (order.getStatus().equals(1)) {
            return DataResult.error("The car has returned");
        }

        Car car = carRepository.findById(carId).get();
        car.setStock(car.getStock() + 1);
        carRepository.save(car);

        // returned
        order.setStatus(1);
        orderRepository.save(order);

        return DataResult.success(null);

    }

}
