package darktower.solarflow.repositories;

import darktower.solarflow.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
