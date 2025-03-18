package darktower.solarflow.repositories;

import darktower.solarflow.entities.InvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, Integer> {

    // Get detail invoices
    List<InvoiceDetail> findByInvoiceId(Integer invoiceId);
}
