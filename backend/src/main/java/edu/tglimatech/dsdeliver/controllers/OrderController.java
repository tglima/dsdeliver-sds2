package edu.tglimatech.dsdeliver.controllers;

import edu.tglimatech.dsdeliver.dto.OrderDTO;
import edu.tglimatech.dsdeliver.dto.ProductDTO;
import edu.tglimatech.dsdeliver.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity<OrderDTO> insert(@RequestBody OrderDTO dto)
    {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
