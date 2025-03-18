package darktower.solarflow.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class InvoiceDTO {

    @NotNull(message = "The client cannot be null")
    private Integer clientId;

    @NotNull(message = "The list of details cannot be empty")
    @Valid
    private List<InvoiceDetailDTO> details;

    public Integer getClientId() { return clientId; }
    public void setClientId(Integer clientId) { this.clientId = clientId; }

    public List<InvoiceDetailDTO> getDetails() { return details; }
    public void setDetails(List<InvoiceDetailDTO> details) { this.details = details; }
}
