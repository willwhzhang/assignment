package hk.com.prudential.assignment.dao;

import hk.com.prudential.assignment.entity.Car;
import hk.com.prudential.assignment.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
}
