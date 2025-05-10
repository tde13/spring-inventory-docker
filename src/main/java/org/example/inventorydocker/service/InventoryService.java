package org.example.inventorydocker.service;

import org.example.inventorydocker.entity.Inventory;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class InventoryService {
  @Autowired
  private RestTemplate restTemplate;

  public boolean validateInventoryPayload(Inventory inventory) {
    try {
      System.out.println("Validating productId: " + inventory.getProductId());
      String url = "http://spring-product-docker:8080/api/products/findById?id=" + inventory.getProductId();
      ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);
      System.out.println("Response: " + response);
      System.out.println("Response status code: " + response.getStatusCode());
      if (!response.getStatusCode().is2xxSuccessful()) {
        return false;
      }
      response.getBody();
      return true;

    } catch (Exception e) {
      e.printStackTrace();
      // Could be HttpClientErrorException, ResourceAccessException, etc.
      return false;
    }
  }


}
