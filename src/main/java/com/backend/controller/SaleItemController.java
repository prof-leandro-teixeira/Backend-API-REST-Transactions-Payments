package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.entity.SaleItem;
import com.backend.requestdto.SaleItemRequestDTO;
import com.backend.service.SaleItemService;

@RestController
@RequestMapping("/api/sale-items")
public class SaleItemController {

    @Autowired
    private SaleItemService saleItemService;

    @GetMapping
    public List<SaleItem> getAllSaleItems() {
        return saleItemService.getAllSaleItems();
    }

    @GetMapping("/{saleItemId}")
    public ResponseEntity<SaleItem> getSaleItemById(@PathVariable Long saleItemId) {
        SaleItem saleItem = saleItemService.getSaleItemById(saleItemId);
        return ResponseEntity.ok(saleItem);
    }

    @PostMapping
    public ResponseEntity<?> createSaleItems(@RequestBody List<SaleItemRequestDTO> saleItems) {
        saleItems.forEach(item -> saleItemService.createSaleItem(item));
        
        String message = saleItems.size() == 1 
            ? "Item processado com sucesso!" 
            : "Itens processados com sucesso!";
            
        return ResponseEntity.ok(message);
    }

    @PutMapping("/{saleItemId}")
    public ResponseEntity<SaleItem> updateSaleItem(@PathVariable Long saleItemId,
                                                   @RequestBody SaleItemRequestDTO saleItemRequestDTO) {
        SaleItem saleItem = saleItemService.updateSaleItem(saleItemId, saleItemRequestDTO);
        return ResponseEntity.ok(saleItem);
    }

    @DeleteMapping("/{saleItemId}")
    public ResponseEntity<Void> deleteSaleItem(@PathVariable Long saleItemId) {
        saleItemService.deleteSaleItem(saleItemId);
        return ResponseEntity.noContent().build();
    }
}
