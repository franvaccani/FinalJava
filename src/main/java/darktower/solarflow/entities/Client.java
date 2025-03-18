package darktower.solarflow.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 75)
    @NotBlank(message = "The name cannot be empty")
    @Size(min = 2, max = 75, message = "The name must be between 2 and 75 characters")
    private String name;

    @Column(nullable = false, length = 75)
    @NotBlank(message = "The last name cannot be empty")
    @Size(min = 2, max = 75, message = "The last name must be between 2 and 75 characters long")
    private String lastname;

    @Column(unique = true, nullable = false, length = 11)
    @NotBlank(message = "The document number cannot be empty")
    @Pattern(regexp = "\\d{11}", message = "The document number must have 11 digits")
    private String docnumber;

    @OneToMany(mappedBy = "client")
    private List<Invoice> invoices;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDocnumber() {
        return docnumber;
    }

    public void setDocnumber(String docnumber) {
        this.docnumber = docnumber;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
}
