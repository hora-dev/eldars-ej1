package com.eldars;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class Usuario {
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaNacimiento;
    private String email;
    private List<CreditCard> tarjetas;


    public void agregarTarjeta(CreditCard tarjeta) {
        this.tarjetas.add(tarjeta);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", email='" + email + '\'' +
                ", tarjetas=" + tarjetas +
                '}';
    }
}
