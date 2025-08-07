/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package casoevaluado2;

import java.util.Random;
import javax.swing.JOptionPane;

public class Hotel {

    private Habitaciones matrizHabitacion[][] = new Habitaciones[5][5];

    public void precargaHabi() {
        int numero = 101;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                String tipo = (j % 2 == 0) ? "Simple" : "Doble";
                double precio = tipo.equalsIgnoreCase("Simple") ? 30 + i * 5 : 40 + i * 5;
                String estado = "Libre";
                matrizHabitacion[i][j] = new Habitaciones(numero, tipo, precio, estado);
                numero++;
            }
            numero += 100 - 5;
        }
    }

    public void enseñarHabitaciones() {
        StringBuilder mostrar1 = new StringBuilder();
        for (int i = 0; i < matrizHabitacion.length; i++) {
            mostrar1.append("Piso ").append(5 - i).append("\n");
            for (int j = 0; j < matrizHabitacion[i].length; j++) {
                Habitaciones habitacion = matrizHabitacion[i][j];
                mostrar1.append(habitacion.getNumero()).append("+ ")
                        .append(habitacion.getTipo()).append(" $")
                        .append(habitacion.getPrecio()).append(" + ")
                        .append(habitacion.getEstado()).append("\n");
            }
            mostrar1.append("\n");
        }
        JOptionPane.showMessageDialog(null, mostrar1.toString());
    }

    public void modHabitacion() {
        int numero = Integer.parseInt(JOptionPane.showInputDialog("Digite el número de habitación a modificar:"));
        for (Habitaciones[] i : matrizHabitacion) {
            for (Habitaciones habitacion : i) {
                if (habitacion.getNumero() == numero) {
                    String nuevoTipo = JOptionPane.showInputDialog("Nuevo tipo:", habitacion.getTipo());
                    double nuevoPrecio = Double.parseDouble(JOptionPane.showInputDialog("Nuevo precio:", habitacion.getPrecio()));
                    String nuevoEstado = JOptionPane.showInputDialog("Nuevo estado (Libre/Ocupada/Sucia):", habitacion.getEstado());
                    habitacion.setTipo(nuevoTipo);
                    habitacion.setPrecio(nuevoPrecio);
                    habitacion.setEstado(nuevoEstado);
                    JOptionPane.showMessageDialog(null, "Habitación modificada con éxito.");
                    return;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Habitación no encontrada.");
    }

    public void mostrarResumen() {
        int libres = 0, ocupadas = 0, sucias = 0;
        double ganancias = 0;
        int total = 0;
        for (Habitaciones[] i : matrizHabitacion) {
            for (Habitaciones habitacion : i) {
                total++;
                switch (habitacion.getEstado()) {
                    case "Libre":
                        libres++;
                        break;
                    case "Ocupada":
                        ocupadas++;
                        ganancias += habitacion.getPrecio();
                        break;
                    case "Sucia":
                        sucias++;
                        break;
                }
            }
        }
        String resumen = "Habi Libres: " + libres + "\n"
                + "Habi Ocupadas: " + ocupadas + "\n"
                + "Habi Sucias: " + sucias + "\n"
                + "Porcentaje Libre: " + (libres * 100 / total) + "%\n"
                + "Porcentaje Ocupadas: " + (ocupadas * 100 / total) + "%\n"
                + "Porcentaje Sucias: " + (sucias * 100 / total) + "%\n"
                + "Ganancia actual: $" + ganancias;
        JOptionPane.showMessageDialog(null, resumen);
    }

    //Profe lo hice aqui porque me daba un bucl infinito raro si lo hacia en el main
    public void menuPrincipal() {
        int opcion;
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Digite la opcion"+"\n"
                    + "1. Ver habitaciones\n"
                    + "2. Modificar habitación\n"
                    + "3. Ver resumen del hotel\n"
                    + "4. Salir"));

            switch (opcion) {
                case 1:
                    enseñarHabitaciones();
                    break;
                case 2:
                    modHabitacion();
                    break;
                case 3:
                    mostrarResumen();
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Gracias por usar .");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        } while (opcion != 4);
    }
}


