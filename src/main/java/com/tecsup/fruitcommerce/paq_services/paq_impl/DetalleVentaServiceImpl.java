package com.tecsup.fruitcommerce.paq_services.paq_impl;

import com.tecsup.fruitcommerce.paq_models.DetalleVenta;
import com.tecsup.fruitcommerce.paq_repositories.DetalleVentaRepository;
import com.tecsup.fruitcommerce.paq_services.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Override
    public DetalleVenta crearDetalleVenta(DetalleVenta detalleVenta) {
        return detalleVentaRepository.save(detalleVenta);
    }

    @Override
    public Optional<DetalleVenta> obtenerDetallePorId(Integer id) {
        return detalleVentaRepository.findById(id);
    }

    @Override
    public List<DetalleVenta> obtenerDetallesPorVentaId(Integer ventaId) {
        return detalleVentaRepository.findByVentaIdVenta(ventaId);
    }

    @Override
    public void eliminarDetalleVenta(Integer id) {
        detalleVentaRepository.deleteById(id);
    }
}