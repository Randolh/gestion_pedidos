package campus.randolh.gestion_pedidos.model;

// Modelo que representa un item dentro de un pedido
public class DetallePedido {

    private Producto producto;
    private int cantidad;
    private double subtotal;

    public DetallePedido() {
    }

    public DetallePedido(Producto producto, int cantidad, double subtotal) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
