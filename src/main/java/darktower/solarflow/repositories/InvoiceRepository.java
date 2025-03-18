package darktower.solarflow.repositories;

import darktower.solarflow.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
