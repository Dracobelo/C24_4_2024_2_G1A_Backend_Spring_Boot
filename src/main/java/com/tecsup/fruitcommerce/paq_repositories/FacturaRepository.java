package com.tecsup.fruitcommerce.paq_repositories;

import com.tecsup.fruitcommerce.paq_models.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {

}