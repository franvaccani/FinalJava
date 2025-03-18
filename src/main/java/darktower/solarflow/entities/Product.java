package darktower.solarflow.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150)
    @NotBlank(message = "The product description cannot be empty")
    private String description;

    @Column(unique = true, nullable = false, length = 50)
    @NotBlank(message = "The product code cannot be empty")
    private String code;

    @Column(nullable = false)
    @NotNull(message = "The stock cannot be zero")
    @Min(value = 0, message = "The stock cannot be negative")
    private Integer stock;

    @Column(nullable = false)
    @NotNull(message = "The price cannot be zero")
    @Min(value = 1, message = "The price must be greater than 0")
    private Double price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
