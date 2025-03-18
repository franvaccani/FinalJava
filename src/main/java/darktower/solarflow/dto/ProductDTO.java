package darktower.solarflow.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductDTO {

    @NotBlank(message = "The product description cannot be empty")
    private String description;

    @NotBlank(message = "The product code cannot be empty")
    private String code;

    @NotNull(message = "The stock cannot be zero")
    @Min(value = 0, message = "The stock cannot be negative")
    private Integer stock;

    @NotNull(message = "The price cannot be zero")
    @Min(value = 1, message = "The price must be greater than 0")
    private Double price;

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}
