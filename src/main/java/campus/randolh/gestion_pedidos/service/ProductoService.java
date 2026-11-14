package campus.randolh.gestion_pedidos.service;

import campus.randolh.gestion_pedidos.model.Producto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {

    private final List<Producto> productos;

    // Inicializacion del catalogo de productos
    public ProductoService() {
        this.productos = new ArrayList<>();
        this.productos.add(new Producto("P01", "Cafe Americano", 15.00));
        this.productos.add(new Producto("P02", "Cafe con Leche", 18.00));
        this.productos.add(new Producto("P03", "Cappuccino", 22.50));
        this.productos.add(new Producto("P04", "Te Helado", 14.00));
        this.productos.add(new Producto("P05", "Croissant Clasico", 16.50));
        this.productos.add(new Producto("P06", "Sandwich Especial", 28.00));
    }

    // Retorna todos los productos del catalogo
    public List<Producto> listarProductos() {
        return productos;
    }

    // Busca un producto por su codigo
    public Producto buscarPorCodigo(String codigo) {
        if (codigo == null) {
            return null;
        }
        for (Producto producto : productos) {
            if (producto.getCodigo().equalsIgnoreCase(codigo.trim())) {
                return producto;
            }
        }
        return null;
    }

    // Verifica si un producto existe
    public boolean existeProducto(String codigo) {
        return buscarPorCodigo(codigo) != null;
    }
}
