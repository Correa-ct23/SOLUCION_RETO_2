package com.biblioteca.controller;

import com.biblioteca.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @PostMapping("/prestar")
    public String prestarLibro(
            @RequestParam String idUsuario,
            @RequestParam String isbnLibro,
            @RequestParam int dias) {

        prestamoService.prestarLibro(idUsuario, isbnLibro, dias);
        return "Préstamo realizado correctamente";
    }
}


@Service
public class PrestamoService {

    @Autowired
    private LibroService libroService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private NotificacionService notificacionService;

    public void prestarLibro(String idUsuario, String isbnLibro, int dias) {

        if (!libroService.estaDisponible(isbnLibro)) {
            throw new RuntimeException("Libro no disponible.");
        }

        if (usuarioService.tieneMultas(idUsuario)) {
            throw new RuntimeException("Usuario tiene multas pendientes.");
        }

        LocalDate fechaDevolucion = calcularFechaDevolucion(dias);
        double costo = calcularCosto(isbnLibro, dias);

        guardarPrestamo(idUsuario, isbnLibro, fechaDevolucion, costo);
        libroService.actualizarStock(isbnLibro);

        notificacionService.enviarRecordatorio(idUsuario, isbnLibro, fechaDevolucion);
    }

    private LocalDate calcularFechaDevolucion(int dias) {
        return LocalDate.now().plusDays(dias);
    }

    private double calcularCosto(String isbn, int dias) {
        if (isbn.startsWith("PREM")) {
            return dias * 2;
        }
        return dias;
    }

    private void guardarPrestamo(String idUsuario, String isbn, LocalDate fechaDevolucion, double costo) {
        System.out.println("Guardando préstamo en BD...");
    }
}


@Service
public class LibroService {

    public boolean estaDisponible(String isbn) {
        System.out.println("Verificando disponibilidad del libro...");
        return true;
    }

    public void actualizarStock(String isbn) {
        System.out.println("Actualizando inventario del libro...");
    }
}


@Service
public class UsuarioService {

    public boolean tieneMultas(String idUsuario) {
        System.out.println("Verificando multas del usuario...");
        return false;
    }
}


@Service
public class NotificacionService {

    public void enviarRecordatorio(String idUsuario, String isbn, LocalDate fechaDevolucion) {
        System.out.println("Enviando email de recordatorio...");
    }
}
