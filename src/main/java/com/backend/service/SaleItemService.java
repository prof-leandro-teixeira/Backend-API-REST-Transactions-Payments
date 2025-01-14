package com.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entity.Product;
import com.backend.entity.Sale;
import com.backend.entity.SaleItem;
import com.backend.repository.ProductRepository;
import com.backend.repository.SaleItemRepository;
import com.backend.repository.SaleRepository;
import com.backend.requestdto.SaleItemRequestDTO;
import com.backend.utils.ResourceNotFoundException;

@Service
public class SaleItemService {

    @Autowired
    private SaleItemRepository saleItemRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<SaleItem> getAllSaleItems() {
        return saleItemRepository.findAll();
    }

    public SaleItem getSaleItemById(Long saleItemId) {
        Optional<SaleItem> saleItem = saleItemRepository.findById(saleItemId);
        return saleItem.orElseThrow(() -> new ResourceNotFoundException("SaleItem with ID " + saleItemId + " not found."));
    }

    public SaleItem createSaleItem(SaleItemRequestDTO saleItemRequestDTO) {
        Sale sale = saleRepository.findById(saleItemRequestDTO.getSaleId())
            .orElseThrow(() -> new ResourceNotFoundException("Sale with ID " + saleItemRequestDTO.getSaleId() + " not found."));
        Product product = productRepository.findById(saleItemRequestDTO.getProductId())
            .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + saleItemRequestDTO.getProductId() + " not found."));

        SaleItem saleItem = new SaleItem();
        saleItem.setSale(sale);
        saleItem.setProduct(product);
        saleItem.setQuantity(saleItemRequestDTO.getQuantity());
        saleItem.setPrice(saleItemRequestDTO.getPrice());

        return saleItemRepository.save(saleItem);
    }

    public SaleItem updateSaleItem(Long saleItemId, SaleItemRequestDTO saleItemRequestDTO) {
        SaleItem existingSaleItem = saleItemRepository.findById(saleItemId)
            .orElseThrow(() -> new ResourceNotFoundException("SaleItem with ID " + saleItemId + " not found."));
        Sale sale = saleRepository.findById(saleItemRequestDTO.getSaleId())
            .orElseThrow(() -> new ResourceNotFoundException("Sale with ID " + saleItemRequestDTO.getSaleId() + " not found."));
        Product product = productRepository.findById(saleItemRequestDTO.getProductId())
            .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + saleItemRequestDTO.getProductId() + " not found."));

        existingSaleItem.setSale(sale);
        existingSaleItem.setProduct(product);
        existingSaleItem.setQuantity(saleItemRequestDTO.getQuantity());
        existingSaleItem.setPrice(saleItemRequestDTO.getPrice());

        return saleItemRepository.save(existingSaleItem);
    }

    public void deleteSaleItem(Long saleItemId) {
        SaleItem existingSaleItem = saleItemRepository.findById(saleItemId)
            .orElseThrow(() -> new ResourceNotFoundException("SaleItem with ID " + saleItemId + " not found."));
        saleItemRepository.delete(existingSaleItem);
    }
}
