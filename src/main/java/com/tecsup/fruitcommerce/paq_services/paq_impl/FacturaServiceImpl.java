package com.tecsup.fruitcommerce.paq_services.paq_impl;

import com.tecsup.fruitcommerce.paq_models.Factura;
import com.tecsup.fruitcommerce.paq_models.Venta;
import com.tecsup.fruitcommerce.paq_repositories.FacturaRepository;
import com.tecsup.fruitcommerce.paq_services.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FacturaServiceImpl implements FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Override
    public Factura crearFactura(Factura factura) {
        return facturaRepository.save(factura);
    }

    @Override
    public Optional<Factura> obtenerFacturaPorId(Integer id) {
        return facturaRepository.findById(id);
    }

    @Override
    public List<Factura> obtenerTodasLasFacturas() {
        return facturaRepository.findAll();
    }

    @Override
    public void eliminarFactura(Integer id) {
        facturaRepository.deleteById(id);
    }

    public Factura crearFacturaDesdeVenta(Venta venta) {
        Factura factura = new Factura();

        // Generar un número de factura único
        String numeroFactura = "FAC-" + System.currentTimeMillis();
        factura.setNumeroFactura(numeroFactura);

        // Asignar la fecha actual
        String fechaEmision = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        factura.setFechaEmision(fechaEmision);

        // Asignar el monto total de la venta a la factura
        factura.setMontoTotal(venta.getTotal());

        // Asociar la venta a la factura
        factura.setVenta(venta);

        // Guardar la factura en la base de datos
        return facturaRepository.save(factura);
    }
}