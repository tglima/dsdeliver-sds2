package edu.tglimatech.dsdeliver.services;

import edu.tglimatech.dsdeliver.dto.OrderDTO;
import edu.tglimatech.dsdeliver.entities.Order;
import edu.tglimatech.dsdeliver.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService
{
    @Autowired
    private OrderRepository repository;

    @Transactional(readOnly = true)
    public List<OrderDTO> findAll()
    {
        List<Order> list = repository.findAllOrdersPending();
        return list.stream().map(o -> new OrderDTO(o)).collect(Collectors.toList());
    }

}
