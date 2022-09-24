package hk.com.prudential.assignment.dao;

import hk.com.prudential.assignment.entity.Customer;
import hk.com.prudential.assignment.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
