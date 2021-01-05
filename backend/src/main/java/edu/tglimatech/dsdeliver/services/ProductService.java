package edu.tglimatech.dsdeliver.services;

import edu.tglimatech.dsdeliver.dto.ProductDTO;
import edu.tglimatech.dsdeliver.entities.Product;
import edu.tglimatech.dsdeliver.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService
{
    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public List<ProductDTO> findAll()
    {
        List<Product> list = repository.findAllByOrderByNameAsc();
        return list.stream().map(p -> new ProductDTO(p)).collect(Collectors.toList());
    }

}
