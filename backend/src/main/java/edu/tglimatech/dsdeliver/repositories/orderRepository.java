package edu.tglimatech.dsdeliver.repositories;

import edu.tglimatech.dsdeliver.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface orderRepository extends JpaRepository<Order, Long>
{
}
