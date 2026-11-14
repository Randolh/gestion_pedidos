package campus.randolh.gestion_pedidos.model;

import java.time.LocalDateTime;
import java.util.List;

// Modelo que representa un pedido completo
public class Pedido {

    private int numeroPedido;
    private LocalDateTime fecha;
    private List<DetallePedido> detalles;
    private double subtotal;
    private double impuesto;
    private double total;

    public Pedido() {
    }

    public Pedido(int numeroPedido, LocalDateTime fecha, List<DetallePedido> detalles, double subtotal, double impuesto, double total) {
        this.numeroPedido = numeroPedido;
        this.fecha = fecha;
        this.detalles = detalles;
        this.subtotal = subtotal;
        this.impuesto = impuesto;
        this.total = total;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
