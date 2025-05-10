package org.example.inventorydocker.controller;

import java.util.Optional;
import org.example.inventorydocker.entity.Inventory;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.example.inventorydocker.repo.InventoryRepo;
import org.example.inventorydocker.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

  @Autowired
  private InventoryRepo inventoryRepo;

  @Autowired
  private InventoryService inventoryService;;

  // Add methods to handle inventory-related operations here

  // Example: Get all inventory items
  @GetMapping("/findAll")
  public List<Inventory> findAll() {
    return inventoryRepo.findAll();
  }

  @PostMapping("/save")
  public Inventory save(@RequestBody Inventory inventory) {
    // Check if inventory already exists for the given productId
    Optional<Inventory> existingInventory = inventoryRepo.findByProductId(inventory.getProductId());
    if (existingInventory.isPresent()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Inventory already exists for product ID " + inventory.getProductId() + ". Please update instead.");
    }

    // Validate productId via product service
    if (inventoryService.validateInventoryPayload(inventory)) {
      return inventoryRepo.save(inventory);
    } else {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Invalid productId: " + inventory.getProductId());
    }
  }


  @GetMapping("/findById")
  public Inventory findById(@RequestParam Integer id) {
    return inventoryRepo.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory not found with id: " + id));
  }

  @DeleteMapping("/deleteById")
  public void deleteById(@RequestParam Integer id) {
    if (!inventoryRepo.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory not found with id: " + id);
    }
    inventoryRepo.deleteById(id);
  }

  @PutMapping("/update")
  public Inventory updateInventory(@RequestBody Inventory updatedInventory) {
    Inventory existingInventory = inventoryRepo.findById(updatedInventory.getId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory not found with id: " + updatedInventory.getId()));

    if (updatedInventory.getProductId() != null &&
        !updatedInventory.getProductId().equals(existingInventory.getProductId())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Changing productId is not allowed.");
    }

    if (updatedInventory.getQuantityAvailable() != null) {
      existingInventory.setQuantityAvailable(updatedInventory.getQuantityAvailable());
    }

    return inventoryRepo.save(existingInventory);
  }

}
