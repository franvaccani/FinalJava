package darktower.solarflow.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "invoice_details")
public class InvoiceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_detail_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    @NotNull(message = "The amount cannot be zero")
    @Min(value = 1, message = "You must purchase at least one product")
    private Integer quantity;

    @Column(nullable = false)
    @NotNull(message = "The price cannot be zero")
    @Min(value = 1, message = "The price must be greater than 0")
    private Double priceAtPurchase;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPriceAtPurchase() {
        return priceAtPurchase;
    }

    public void setPriceAtPurchase(Double priceAtPurchase) {
        this.priceAtPurchase = priceAtPurchase;
    }
}
