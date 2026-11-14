package campus.randolh.gestion_pedidos.view;

import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Scanner;

@Component
public class InputHelper {

    private final Scanner scanner;

    // Inicializacion del scanner
    public InputHelper() {
        this.scanner = new Scanner(System.in);
    }

    // Solicita un texto obligatorio hasta que sea valido
    public String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                if (!scanner.hasNextLine()) {
                    return "";
                }
                String entrada = scanner.nextLine().trim();
                if (!entrada.isEmpty()) {
                    return entrada;
                }
                System.out.println("Error: El dato no puede estar vacio.");
            } catch (NoSuchElementException e) {
                return "";
            }
        }
    }

    // Solicita un numero entero dentro de un rango hasta que sea valido
    public int leerEntero(String mensaje, int min, int max) {
        while (true) {
            System.out.print(mensaje);
            try {
                if (!scanner.hasNextLine()) {
                    return max;
                }
                String entrada = scanner.nextLine().trim();
                int valor = Integer.parseInt(entrada);
                if (valor >= min && valor <= max) {
                    return valor;
                }
                System.out.println("Error: Ingrese un numero entre " + min + " y " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Error: Entrada invalida. Debe ser un numero entero.");
            } catch (NoSuchElementException e) {
                return max;
            }
        }
    }

    // Solicita un numero entero positivo hasta que sea valido
    public int leerEnteroPositivo(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                if (!scanner.hasNextLine()) {
                    return 1;
                }
                String entrada = scanner.nextLine().trim();
                int valor = Integer.parseInt(entrada);
                if (valor > 0) {
                    return valor;
                }
                System.out.println("Error: La cantidad debe ser mayor a cero.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Entrada invalida. Debe ser un numero entero.");
            } catch (NoSuchElementException e) {
                return 1;
            }
        }
    }

    // Solicita un numero decimal mayor o igual a un valor minimo hasta que sea valido
    public double leerDouble(String mensaje, double min) {
        while (true) {
            System.out.print(mensaje);
            try {
                if (!scanner.hasNextLine()) {
                    return min;
                }
                String entrada = scanner.nextLine().trim();
                double valor = Double.parseDouble(entrada);
                if (valor >= min) {
                    return valor;
                }
                System.out.println("Error: El valor debe ser mayor o igual a " + min + ".");
            } catch (NumberFormatException e) {
                System.out.println("Error: Entrada invalida. Debe ser un numero decimal.");
            } catch (NoSuchElementException e) {
                return min;
            }
        }
    }

    // Solicita confirmacion de Si o No hasta que sea valida
    public boolean leerConfirmacion(String mensaje) {
        while (true) {
            System.out.print(mensaje + " (S/N): ");
            try {
                if (!scanner.hasNextLine()) {
                    return false;
                }
                String entrada = scanner.nextLine().trim().toUpperCase();
                if (entrada.equals("S") || entrada.equals("SI")) {
                    return true;
                }
                if (entrada.equals("N") || entrada.equals("NO")) {
                    return false;
                }
                System.out.println("Error: Ingrese S para Si o N para No.");
            } catch (NoSuchElementException e) {
                return false;
            }
        }
    }

    // Permite actualizar texto o mantener el valor actual si se presiona Enter
    public String leerTextoOpcional(String mensaje, String valorActual) {
        System.out.print(mensaje + " [Actual: " + valorActual + "] (Enter para mantener): ");
        try {
            if (!scanner.hasNextLine()) {
                return valorActual;
            }
            String entrada = scanner.nextLine().trim();
            if (entrada.isEmpty()) {
                return valorActual;
            }
            return entrada;
        } catch (NoSuchElementException e) {
            return valorActual;
        }
    }

    // Permite actualizar un entero o mantener el valor actual si se presiona Enter
    public int leerEnteroOpcional(String mensaje, int valorActual, int min, int max) {
        while (true) {
            System.out.print(mensaje + " [Actual: " + valorActual + "] (Enter para mantener): ");
            try {
                if (!scanner.hasNextLine()) {
                    return valorActual;
                }
                String entrada = scanner.nextLine().trim();
                if (entrada.isEmpty()) {
                    return valorActual;
                }
                int valor = Integer.parseInt(entrada);
                if (valor >= min && valor <= max) {
                    return valor;
                }
                System.out.println("Error: Ingrese un numero entre " + min + " y " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Error: Entrada invalida. Debe ser un numero entero.");
            } catch (NoSuchElementException e) {
                return valorActual;
            }
        }
    }

    // Permite actualizar un numero decimal o mantener el valor actual si se presiona Enter
    public double leerDoubleOpcional(String mensaje, double valorActual, double min) {
        while (true) {
            System.out.print(mensaje + " [Actual: " + valorActual + "] (Enter para mantener): ");
            try {
                if (!scanner.hasNextLine()) {
                    return valorActual;
                }
                String entrada = scanner.nextLine().trim();
                if (entrada.isEmpty()) {
                    return valorActual;
                }
                double valor = Double.parseDouble(entrada);
                if (valor >= min) {
                    return valor;
                }
                System.out.println("Error: El valor debe ser mayor o igual a " + min + ".");
            } catch (NumberFormatException e) {
                System.out.println("Error: Entrada invalida. Debe ser un numero decimal.");
            } catch (NoSuchElementException e) {
                return valorActual;
            }
        }
    }
}
