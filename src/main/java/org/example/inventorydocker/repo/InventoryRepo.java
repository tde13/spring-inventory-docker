package org.example.inventorydocker.repo;

import java.util.Optional;
import org.example.inventorydocker.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepo extends JpaRepository<Inventory, Integer> {

  Optional<Inventory> findByProductId(Integer productId);

}
