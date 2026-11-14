package campus.randolh.gestion_pedidos.view;

import campus.randolh.gestion_pedidos.model.DetallePedido;
import campus.randolh.gestion_pedidos.model.Pedido;
import campus.randolh.gestion_pedidos.model.Producto;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class ConsolaView {

    private final DateTimeFormatter formateadorFecha;

    // Inicializacion del formateador de fecha
    public ConsolaView() {
        this.formateadorFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    }

    // Muestra el menu principal en consola
    public void mostrarMenuPrincipal() {
        System.out.println();
        System.out.println("========================================");
        System.out.println("       SISTEMA DE GESTION DE PEDIDOS     ");
        System.out.println("========================================");
        System.out.println("1. Ver menu de productos");
        System.out.println("2. Crear pedido");
        System.out.println("3. Ver resumen de ventas");
        System.out.println("4. Salir del sistema");
        System.out.println("========================================");
    }

    // Muestra el catalogo completo de productos
    public void mostrarCatalogo(List<Producto> productos) {
        System.out.println();
        System.out.println("------------------------------------------------");
        System.out.println("             MENU DE PRODUCTOS                  ");
        System.out.println("------------------------------------------------");
        System.out.printf("%-10s %-25s %-10s%n", "CODIGO", "PRODUCTO", "PRECIO");
        System.out.println("------------------------------------------------");
        for (Producto producto : productos) {
            System.out.printf("%-10s %-25s $%-9.2f%n",
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getPrecio());
        }
        System.out.println("------------------------------------------------");
    }

    // Muestra los productos agregados al pedido en construccion
    public void mostrarDetallesActuales(List<DetallePedido> detalles) {
        System.out.println();
        System.out.println("--- Resumen actual del pedido ---");
        System.out.printf("%-4s %-10s %-20s %-8s %-10s %-10s%n", "#", "CODIGO", "PRODUCTO", "CANT", "PRECIO", "SUBTOTAL");
        System.out.println("------------------------------------------------------------------");
        int indice = 1;
        double subtotalAcumulado = 0.0;
        for (DetallePedido detalle : detalles) {
            System.out.printf("%-4d %-10s %-20s %-8d $%-9.2f $%-9.2f%n",
                    indice++,
                    detalle.getProducto().getCodigo(),
                    detalle.getProducto().getNombre(),
                    detalle.getCantidad(),
                    detalle.getProducto().getPrecio(),
                    detalle.getSubtotal());
            subtotalAcumulado += detalle.getSubtotal();
        }
        System.out.println("------------------------------------------------------------------");
        System.out.printf("Subtotal actual acumulado: $%.2f%n", subtotalAcumulado);
    }

    // Imprime la factura de un pedido registrado
    public void mostrarFactura(Pedido pedido) {
        System.out.println();
        System.out.println("========================================");
        System.out.println("           FACTURA DE VENTA             ");
        System.out.println("========================================");
        System.out.println("Numero de pedido: #" + pedido.getNumeroPedido());
        System.out.println("Fecha y hora    : " + pedido.getFecha().format(formateadorFecha));
        System.out.println("----------------------------------------");
        System.out.printf("%-18s %-6s %-8s %-8s%n", "PRODUCTO", "CANT", "PRECIO", "SUBTOTAL");
        System.out.println("----------------------------------------");
        for (DetallePedido detalle : pedido.getDetalles()) {
            System.out.printf("%-18s %-6d $%-7.2f $%-7.2f%n",
                    detalle.getProducto().getNombre(),
                    detalle.getCantidad(),
                    detalle.getProducto().getPrecio(),
                    detalle.getSubtotal());
        }
        System.out.println("----------------------------------------");
        System.out.printf("Subtotal      : $%.2f%n", pedido.getSubtotal());
        System.out.printf("Impuesto (10%%): $%.2f%n", pedido.getImpuesto());
        System.out.printf("Total a pagar : $%.2f%n", pedido.getTotal());
        System.out.println("========================================");
        System.out.println("        Gracias por su compra           ");
        System.out.println("========================================");
    }

    // Muestra el resumen acumulado de ventas
    public void mostrarResumenVentas(int totalPedidos, double totalVendido) {
        System.out.println();
        System.out.println("========================================");
        System.out.println("         RESUMEN GENERAL DE VENTAS      ");
        System.out.println("========================================");
        System.out.println("Cantidad total de pedidos : " + totalPedidos);
        System.out.printf("Total vendido acumulado   : $%.2f%n", totalVendido);
        System.out.println("========================================");
    }

    // Muestra un mensaje informativo
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    // Muestra un mensaje de advertencia o error
    public void mostrarError(String mensaje) {
        System.out.println("Error: " + mensaje);
    }
}
