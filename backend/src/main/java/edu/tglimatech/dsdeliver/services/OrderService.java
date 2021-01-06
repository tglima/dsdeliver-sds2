package edu.tglimatech.dsdeliver.services;

import edu.tglimatech.dsdeliver.dto.OrderDTO;
import edu.tglimatech.dsdeliver.dto.ProductDTO;
import edu.tglimatech.dsdeliver.entities.Order;
import edu.tglimatech.dsdeliver.entities.OrderStatus;
import edu.tglimatech.dsdeliver.entities.Product;
import edu.tglimatech.dsdeliver.repositories.OrderRepository;
import edu.tglimatech.dsdeliver.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService
{
    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<OrderDTO> findAll()
    {
        List<Order> list = repository.findAllOrdersPending();
        return list.stream().map(o -> new OrderDTO(o)).collect(Collectors.toList());
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto)
    {
        Order order = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(),Instant.now(), OrderStatus.PENDING);

        for (ProductDTO p: dto.getProducts())
        {
            Product product = productRepository.getOne(p.getId());
            order.getProducts().add(product);
        }

        order =repository.save(order);
        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO setDelivered(Long id)
    {
        Order order = repository.getOne(id);
        order.setStatus(OrderStatus.DELIVERED);
        order = repository.save(order);
        return new OrderDTO(order);
    }

}
