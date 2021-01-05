package edu.tglimatech.dsdeliver.controllers;

import edu.tglimatech.dsdeliver.dto.ProductDTO;
import edu.tglimatech.dsdeliver.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController
{
    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll()
    {
        List<ProductDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

}
