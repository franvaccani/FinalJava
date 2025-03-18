package darktower.solarflow.controllers;

import darktower.solarflow.entities.InvoiceDetail;
import darktower.solarflow.services.InvoiceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice_details")
public class InvoiceDetailController {

    @Autowired
    @Lazy
    private InvoiceDetailService invoiceDetailService;

    // Get detail invoice
    @GetMapping("/{invoiceId}")
    public List<InvoiceDetail> getInvoiceDetails(@PathVariable Integer invoiceId) {
        return invoiceDetailService.getInvoiceDetailsByInvoiceId(invoiceId);
    }
}
