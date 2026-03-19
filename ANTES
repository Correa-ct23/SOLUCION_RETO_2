package com.biblioteca;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SistemaBiblioteca {

    public void prestarLibro(String idUsuario, String isbnLibro, int dias) {

        if (!verificarDisponibilidadLibro(isbnLibro)) {
            throw new RuntimeException("Libro no disponible.");
        }

        if (tieneMultasPendientes(idUsuario)) {
            throw new RuntimeException("Usuario tiene multas pendientes.");
        }

        LocalDate fechaDevolucion = calcularFechaDevolucion(dias);

        double costo = calcularCostoPrestamo(isbnLibro, dias);

        guardarPrestamoEnBD(idUsuario, isbnLibro, fechaDevolucion, costo);

        actualizarStockLibro(isbnLibro);

        enviarEmailRecordatorio(idUsuario, isbnLibro, fechaDevolucion);
    }

    private boolean verificarDisponibilidadLibro(String isbn) {
        System.out.println("Verificando disponibilidad del libro en BD...");
        return true;
    }

    private boolean tieneMultasPendientes(String idUsuario) {
        System.out.println("Verificando multas del usuario...");
        return false;
    }

    private LocalDate calcularFechaDevolucion(int dias) {
        return LocalDate.now().plusDays(dias);
    }

    private double calcularCostoPrestamo(String isbn, int dias) {
        if (isbn.startsWith("PREM")) {
            return dias * 2;
        }
        return dias;
    }

    private void guardarPrestamoEnBD(String idUsuario, String isbn, LocalDate fechaDevolucion, double costo) {
        System.out.println("Guardando préstamo...");
    }

    private void actualizarStockLibro(String isbn) {
        System.out.println("Actualizando stock...");
    }

    private void enviarEmailRecordatorio(String idUsuario, String isbn, LocalDate fechaDevolucion) {
        System.out.println("Enviando email...");
    }
}
