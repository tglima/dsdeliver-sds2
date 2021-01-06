package edu.tglimatech.dsdeliver.controllers;

import edu.tglimatech.dsdeliver.dto.OrderDTO;
import edu.tglimatech.dsdeliver.dto.ProductDTO;
import edu.tglimatech.dsdeliver.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController
{
    @Autowired
    OrderService service;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll()
    {
        List<OrderDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
}
