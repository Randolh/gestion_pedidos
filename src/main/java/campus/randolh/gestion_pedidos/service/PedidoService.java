package campus.randolh.gestion_pedidos.service;

import campus.randolh.gestion_pedidos.model.DetallePedido;
import campus.randolh.gestion_pedidos.model.Pedido;
import campus.randolh.gestion_pedidos.model.Producto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    private static final double PORCENTAJE_IMPUESTO = 0.10;

    private final List<Pedido> pedidos;
    private int contadorPedidos;

    // Inicializacion del servicio de pedidos
    public PedidoService() {
        this.pedidos = new ArrayList<>();
        this.contadorPedidos = 1;
    }

    // Crea un detalle de producto y cantidad
    public DetallePedido crearDetalle(Producto producto, int cantidad) {
        double subtotal = producto.getPrecio() * cantidad;
        return new DetallePedido(producto, cantidad, subtotal);
    }

    // Registra un nuevo pedido calculando subtotales, impuesto y total
    public Pedido registrarPedido(List<DetallePedido> detalles) {
        double subtotal = 0.0;
        for (DetallePedido detalle : detalles) {
            subtotal += detalle.getSubtotal();
        }
        double impuesto = subtotal * PORCENTAJE_IMPUESTO;
        double total = subtotal + impuesto;
        int numero = contadorPedidos++;

        Pedido pedido = new Pedido(numero, LocalDateTime.now(), new ArrayList<>(detalles), subtotal, impuesto, total);
        pedidos.add(pedido);
        return pedido;
    }

    // Retorna todos los pedidos registrados
    public List<Pedido> listarPedidos() {
        return pedidos;
    }

    // Retorna la cantidad total de pedidos
    public int obtenerTotalPedidos() {
        return pedidos.size();
    }

    // Retorna el monto acumulado total de ventas
    public double obtenerTotalVentas() {
        double total = 0.0;
        for (Pedido pedido : pedidos) {
            total += pedido.getTotal();
        }
        return total;
    }
}
