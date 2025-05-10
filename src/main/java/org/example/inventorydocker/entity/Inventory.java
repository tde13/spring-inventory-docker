package org.example.inventorydocker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Inventory")
@Entity
@Getter
@Setter
public class Inventory {

  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
  private Integer id;
  @Column(nullable = false)
  private Integer productId; // Foreign key to Product
  @Column(nullable = false)
  private Integer quantityAvailable;
  @Column(nullable = false, updatable = false)
  private Timestamp lastUpdated;

  @PrePersist
  protected void onCreate() {
    if (this.lastUpdated != null) {
      throw new IllegalArgumentException("createdAt should not be manually set. It is auto-generated.");
    }
    this.lastUpdated = Timestamp.from(Instant.now());
  }

  public Integer getId() {
    return id;
  }

  public Integer getProductId() {
    return productId;
  }

  public Integer getQuantityAvailable() {
    return quantityAvailable;
  }

  public Timestamp getLastUpdated() {
    return lastUpdated;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setProductId(Integer productId) {
    this.productId = productId;
  }

  public void setQuantityAvailable(Integer quantityAvailable) {
    this.quantityAvailable = quantityAvailable;
  }

  public void setLastUpdated(Timestamp lastUpdated) {
    this.lastUpdated = lastUpdated;
  }
}
