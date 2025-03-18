package darktower.solarflow.controllers;

import darktower.solarflow.dto.InvoiceDTO;
import darktower.solarflow.entities.Invoice;
import darktower.solarflow.entities.InvoiceDetail;
import darktower.solarflow.entities.Client;
import darktower.solarflow.entities.Product;
import darktower.solarflow.services.InvoiceService;
import darktower.solarflow.repositories.ClientRepository;
import darktower.solarflow.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    @Lazy
    private InvoiceService invoiceService;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public String createInvoice(@Valid @RequestBody InvoiceDTO invoiceDTO) {
        Optional<Client> clientOpt = clientRepository.findById(invoiceDTO.getClientId());
        if (clientOpt.isEmpty()) {
            return "Error: Client with ID " + invoiceDTO.getClientId() + " not found.";
        }

        List<InvoiceDetail> details = invoiceDTO.getDetails().stream().map(detailDTO -> {
            InvoiceDetail detail = new InvoiceDetail();
            Optional<Product> productOpt = productRepository.findById(detailDTO.getProductId());
            if (productOpt.isPresent()) {
                detail.setProduct(productOpt.get());
                detail.setQuantity(detailDTO.getQuantity());
                detail.setPriceAtPurchase(productOpt.get().getPrice());
            }
            return detail;
        }).collect(Collectors.toList());

        Invoice invoice = new Invoice();
        invoice.setClient(clientOpt.get());
        invoice.setDetails(details);

        return invoiceService.createInvoice(invoice);
    }
}
