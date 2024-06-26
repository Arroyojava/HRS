package edu.ssc.hrs.entity.service;

import edu.ssc.hrs.entity.Customer;
import edu.ssc.hrs.entity.Inventory;
import edu.ssc.hrs.entity.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Iterable<Inventory> findAll() {
        return inventoryRepository.findAll();
    }

    public Optional<Inventory> findById(Long id) {
        return inventoryRepository.findById(id);
    }

    public Inventory save(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public long count() {
        return inventoryRepository.count();
    }

    public Inventory getInventoryById(Long id) {
        Optional<Inventory> inventoryOptional = inventoryRepository.findById(id);
        if (inventoryOptional.isPresent()) {
            return inventoryOptional.get();
        } else {
            throw new RuntimeException("Inventory not found for id: " + id);
        }
    }

    public void updateInventory(Inventory inventory) {
        inventoryRepository.save(inventory);
    }


    public void deleteById(Long id) {
        inventoryRepository.deleteById(id);
    }
}

