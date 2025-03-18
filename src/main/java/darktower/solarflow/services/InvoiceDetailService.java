package darktower.solarflow.services;

import darktower.solarflow.entities.InvoiceDetail;
import darktower.solarflow.repositories.InvoiceDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceDetailService {

    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;

    // Get detail invoice
    public List<InvoiceDetail> getInvoiceDetailsByInvoiceId(Integer invoiceId) {
        return invoiceDetailRepository.findByInvoiceId(invoiceId);
    }
}
