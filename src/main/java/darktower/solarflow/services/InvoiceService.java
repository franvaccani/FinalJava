package darktower.solarflow.services;

import darktower.solarflow.entities.Invoice;
import darktower.solarflow.entities.InvoiceDetail;
import darktower.solarflow.entities.Client;
import darktower.solarflow.entities.Product;
import darktower.solarflow.repositories.ClientRepository;
import darktower.solarflow.repositories.InvoiceRepository;
import darktower.solarflow.repositories.ProductRepository;
import darktower.solarflow.repositories.InvoiceDetailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    private static final Logger log = LoggerFactory.getLogger(InvoiceService.class);

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;

    public String createInvoice(Invoice invoiceRequest) {
        log.info("Starting invoice creation...");

        // Chk invoiceRequest
        if (invoiceRequest == null || invoiceRequest.getClient() == null) {
            log.error("Error: invoiceRequest o client es NULL");
            return "Internal error when creating the invoice: incomplete data.";
        }

        // Chk client
        Optional<Client> clientOpt = clientRepository.findById(invoiceRequest.getClient().getId());
        if (clientOpt.isEmpty()) {
            log.error("Error: Client with ID {} not found.", invoiceRequest.getClient().getId());
            return "Error: Client with ID " + invoiceRequest.getClient().getId() + " not found.";
        }
        invoiceRequest.setClient(clientOpt.get());

        // Chk product and stock
        double total = 0;
        List<InvoiceDetail> updatedDetails = new ArrayList<>();

        for (InvoiceDetail detail : invoiceRequest.getDetails()) {
            Optional<Product> productOpt = productRepository.findById(detail.getProduct().getId());

            if (productOpt.isEmpty()) {
                log.error("Error: Product with ID {} not found.", detail.getProduct().getId());
                return "Error: Product with ID " + detail.getProduct().getId() + " not found.";
            }

            Product product = productOpt.get();

            // Chk stock
            if (detail.getQuantity() > product.getStock()) {
                log.error("Error: Insufficient stock for the product {}.", product.getDescription());
                return "Error: Insufficient stock for the product " + product.getDescription() + ".";
            }

            // Update stock
            product.setStock(product.getStock() - detail.getQuantity());
            productRepository.save(product);

            InvoiceDetail newDetail = new InvoiceDetail();
            newDetail.setProduct(product);
            newDetail.setQuantity(detail.getQuantity());
            newDetail.setPriceAtPurchase(product.getPrice());
            newDetail.setInvoice(invoiceRequest);

            updatedDetails.add(newDetail);

            total += newDetail.getPriceAtPurchase() * newDetail.getQuantity();
        }

        // Date + Total
        invoiceRequest.setDetails(updatedDetails);
        invoiceRequest.setCreatedAt(new Date());
        invoiceRequest.setTotal(total);

        try {
            // Save invoice DB
            invoiceRequest = invoiceRepository.save(invoiceRequest);

            // Save detail invoice
            for (InvoiceDetail detail : updatedDetails) {
                detail.setInvoice(invoiceRequest);
                invoiceDetailRepository.save(detail);
            }

            log.info("Invoice created successfully. ID: {}", invoiceRequest.getId());
            return "Invoice created successfully. ID: " + invoiceRequest.getId() + ", Total: $" + total;
        } catch (Exception e) {
            log.error("Error saving invoice to database", e);
            return "Internal error in the database.";
        }
    }
}
