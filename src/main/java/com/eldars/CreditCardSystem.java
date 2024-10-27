package com.eldars;

import lombok.Builder;
import lombok.extern.apachecommons.CommonsLog;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@CommonsLog
@Builder
public class CreditCardSystem {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, Usuario> users = new HashMap<>();
    private static final Map<String, CreditCard> cards = new HashMap<>();

    public static void main(String[] args) {
        while (true) {
            try {
                System.out.println("1. Registrar Persona");
                System.out.println("2. Registrar Tarjeta");
                System.out.println("3. Ver Tarjetas por DNI");
                System.out.println("4. Consultar Tasas por Fecha");
                System.out.println("5. Salir");
                int option = scanner.nextInt();
                scanner.nextLine(); // Consumir salto de línea

                switch (option) {
                    case 1 -> registerUser();
                    case 2 -> registerCard();
                    case 3 -> getCardsByDni();
                    case 4 -> consultServiceFee();
                    case 5 -> System.exit(0);
                    default -> System.out.println("Opción no válida.");
                }
            } catch (Exception e){
                log.error("Error: " + e.getMessage());
                scanner.nextLine(); // Limpiar entrada en caso de error
            }
        }
    }

    private static void registerUser() {
        System.out.println("Ingrese nombre: ");
        String name = scanner.nextLine();
        System.out.println("Ingrese apellido: ");
        String lastName = scanner.nextLine();
        System.out.println("Ingrese DNI: ");
        String dni = scanner.nextLine();
        System.out.println("Ingrese fecha de nacimiento (dd-MM-yyyy): ");
        String birthDateStr = scanner.nextLine();
        System.out.println("Ingrese email: ");
        String email = scanner.nextLine();

        Usuario usuario = Usuario.builder()
                                            .dni(dni)
                                            .nombre(name)
                                            .apellido(lastName)
                                            .fechaNacimiento(LocalDate.parse(birthDateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                                            .email(email)
                                            .tarjetas(new ArrayList<>())
                                            .build();
        // Crear registro de usuario
        users.put(dni, usuario);
        System.out.println("Usuario registrado con éxito.");
    }

    private static void registerCard() {
        System.out.println("Ingrese el DNI del usuario: ");
        String dni = scanner.nextLine();
        if (!users.containsKey(dni)) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        System.out.println("Ingrese marca de la tarjeta (VISA, NARA, AMEX): ");
        String brand = scanner.nextLine();
        System.out.println("Ingrese número de tarjeta: ");
        String cardNumber = scanner.nextLine();
        System.out.println("Ingrese fecha de vencimiento (dd-MM-yyyy): ");
        String expirationDate = scanner.nextLine();
        System.out.println("Ingrese nombre que figura en la tarjeta: ");
        String cardFullName = scanner.nextLine();

        Usuario usuario = users.get(dni);
        List<CreditCard> cards = usuario.getTarjetas();
        CreditCard card = new CreditCard(
                            brand,
                            cardNumber,
                            LocalDate.parse(expirationDate, DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                            cardFullName);
        cards.add(card);
        usuario.setTarjetas(cards);


        System.out.println("Tarjeta registrada con éxito.");
    }

    private static void getCardsByDni() {
        System.out.println("Ingrese el DNI: ");
        String dni = scanner.nextLine();
        if (!users.containsKey(dni)) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        List<CreditCard> cards = users.get(dni).getTarjetas();
        if (cards.isEmpty()) {
            System.out.println("No tiene tarjetas registradas.");
        } else {
            for (CreditCard card : cards) {
                System.out.println(card.toString());
            }
        }
    }

    private static void consultServiceFee() {
        System.out.println("Ingrese la fecha (dd-MM-yyyy) o presione Enter para usar la fecha actual: ");
        String dateInput = scanner.nextLine();
        LocalDate date = dateInput.isEmpty() ? LocalDate.now() : LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        BigDecimal visaFee = new VisaServiceFee().calculateFee(date);
        BigDecimal naraFee = new NaraServiceFee().calculateFee(date);
        BigDecimal amexFee = new AmexServiceFee().calculateFee(date);

        System.out.println("Tasa VISA: " + visaFee);
        System.out.println("Tasa NARA: " + naraFee);
        System.out.println("Tasa AMEX: " + amexFee);
    }
}

