package darktower.solarflow.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class InvoiceDetailDTO {

    @NotNull(message = "The product cannot be void")
    private Integer productId;

    @NotNull(message = "The amount cannot be zero")
    @Min(value = 1, message = "You must purchase at least one product")
    private Integer quantity;

    public Integer getProductId() { return productId; }
    public void setProductId(Integer productId) { this.productId = productId; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
