package com.tecsup.fruitcommerce.paq_services.paq_impl;

import com.tecsup.fruitcommerce.paq_models.Carrito;
import com.tecsup.fruitcommerce.paq_models.Comprador;
import com.tecsup.fruitcommerce.paq_repositories.CarritoRepository;
import com.tecsup.fruitcommerce.paq_repositories.CompradorRepository;
import com.tecsup.fruitcommerce.paq_services.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CarritoServiceImpl implements CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private CompradorRepository compradorRepository;

    @Override
    public Optional<Carrito> obtenerCarritoPorId(Integer id) {
        return carritoRepository.findById(id);
    }

    @Override
    public Carrito crearCarrito(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    @Override
    public void eliminarCarrito(Integer id) {
        carritoRepository.deleteById(id);
    }
    @Override
    public Carrito crearCarrito(Long idComprador) {
        Comprador comprador = compradorRepository.findById(idComprador)
                .orElseThrow(() -> new IllegalArgumentException("Comprador no encontrado"));
        Carrito carrito = new Carrito();
        carrito.setComprador(comprador);
        carrito.setFechaCreacion(LocalDate.now().toString());
        return carritoRepository.save(carrito);
    }
    @Override
    public Optional<Carrito> buscarCarritoPorCompradorId(Long compradorId) {
        return carritoRepository.findByCompradorIdComprador(compradorId);
    }

    @Override
    public Optional<Carrito> obtenerCarritoPorUsuarioId(Long usuarioId) {
        return carritoRepository.findByCompradorUserUserId(usuarioId);
    }
}