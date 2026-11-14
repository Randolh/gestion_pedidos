package campus.randolh.gestion_pedidos.controller;

import campus.randolh.gestion_pedidos.model.DetallePedido;
import campus.randolh.gestion_pedidos.model.Pedido;
import campus.randolh.gestion_pedidos.model.Producto;
import campus.randolh.gestion_pedidos.service.PedidoService;
import campus.randolh.gestion_pedidos.service.ProductoService;
import campus.randolh.gestion_pedidos.view.ConsolaView;
import campus.randolh.gestion_pedidos.view.InputHelper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PedidoController {

    private final ProductoService productoService;
    private final PedidoService pedidoService;
    private final ConsolaView view;
    private final InputHelper inputHelper;

    // Inyeccion de dependencias por constructor
    public PedidoController(ProductoService productoService,
                            PedidoService pedidoService,
                            ConsolaView view,
                            InputHelper inputHelper) {
        this.productoService = productoService;
        this.pedidoService = pedidoService;
        this.view = view;
        this.inputHelper = inputHelper;
    }

    // Flujo principal del menu de la aplicacion
    public void iniciar() {
        boolean ejecutando = true;
        while (ejecutando) {
            view.mostrarMenuPrincipal();
            int opcion = inputHelper.leerEntero("Seleccione una opcion (1-4): ", 1, 4);
            switch (opcion) {
                case 1:
                    verMenuProductos();
                    break;
                case 2:
                    crearPedido();
                    break;
                case 3:
                    verResumenVentas();
                    break;
                case 4:
                    ejecutando = false;
                    view.mostrarMensaje("Saliendo del sistema...");
                    break;
                default:
                    view.mostrarError("Opcion no valida.");
                    break;
            }
        }
    }

    // Visualiza el catalogo de productos
    public void verMenuProductos() {
        List<Producto> productos = productoService.listarProductos();
        view.mostrarCatalogo(productos);
    }

    // Gestiona la creacion y confirmacion de un nuevo pedido
    public void crearPedido() {
        List<Producto> productos = productoService.listarProductos();
        view.mostrarCatalogo(productos);

        List<DetallePedido> detalles = new ArrayList<>();
        boolean agregarMas = true;

        while (agregarMas) {
            String codigo = inputHelper.leerTexto("Ingrese el codigo del producto (o '0' para cancelar): ");
            if (codigo.equalsIgnoreCase("0")) {
                break;
            }

            Producto producto = productoService.buscarPorCodigo(codigo);
            if (producto == null) {
                view.mostrarError("Codigo no encontrado en el menu.");
                continue;
            }

            DetallePedido detalleExistente = buscarDetallePorCodigo(detalles, producto.getCodigo());
            if (detalleExistente != null) {
                view.mostrarMensaje("El producto ya esta en el pedido con cantidad actual: " + detalleExistente.getCantidad());
                boolean modificar = inputHelper.leerConfirmacion("Desea actualizar la cantidad?");
                if (modificar) {
                    int nuevaCantidad = inputHelper.leerEnteroOpcional("Nueva cantidad", detalleExistente.getCantidad(), 1, 1000);
                    detalleExistente.setCantidad(nuevaCantidad);
                    detalleExistente.setSubtotal(producto.getPrecio() * nuevaCantidad);
                    view.mostrarMensaje("Cantidad actualizada.");
                }
            } else {
                int cantidad = inputHelper.leerEnteroPositivo("Ingrese la cantidad: ");
                DetallePedido detalle = pedidoService.crearDetalle(producto, cantidad);
                detalles.add(detalle);
                view.mostrarMensaje("Producto agregado al pedido.");
            }

            view.mostrarDetallesActuales(detalles);
            agregarMas = inputHelper.leerConfirmacion("Desea agregar otro producto?");
        }

        if (detalles.isEmpty()) {
            view.mostrarMensaje("No se agregaron productos al pedido.");
            return;
        }

        view.mostrarDetallesActuales(detalles);

        // Opcion para revisar o actualizar cantidades antes de facturar
        boolean deseaModificar = inputHelper.leerConfirmacion("Desea modificar la cantidad de algun producto antes de facturar?");
        if (deseaModificar) {
            int posicion = inputHelper.leerEntero("Ingrese el numero de item (#) a modificar: ", 1, detalles.size());
            DetallePedido seleccionado = detalles.get(posicion - 1);
            int cantidadActualizada = inputHelper.leerEnteroOpcional("Nueva cantidad", seleccionado.getCantidad(), 1, 1000);
            seleccionado.setCantidad(cantidadActualizada);
            seleccionado.setSubtotal(seleccionado.getProducto().getPrecio() * cantidadActualizada);
            view.mostrarMensaje("Item actualizado.");
            view.mostrarDetallesActuales(detalles);
        }

        boolean confirmar = inputHelper.leerConfirmacion("Desea confirmar y registrar este pedido?");
        if (confirmar) {
            Pedido pedido = pedidoService.registrarPedido(detalles);
            view.mostrarMensaje("Pedido registrado exitosamente.");
            view.mostrarFactura(pedido);
        } else {
            view.mostrarMensaje("Pedido cancelado. No se genero factura.");
        }
    }

    // Visualiza el resumen acumulado de ventas
    public void verResumenVentas() {
        int totalPedidos = pedidoService.obtenerTotalPedidos();
        double totalVendido = pedidoService.obtenerTotalVentas();
        view.mostrarResumenVentas(totalPedidos, totalVendido);
    }

    // Busca un detalle existente dentro del pedido actual por codigo de producto
    private DetallePedido buscarDetallePorCodigo(List<DetallePedido> detalles, String codigo) {
        for (DetallePedido detalle : detalles) {
            if (detalle.getProducto().getCodigo().equalsIgnoreCase(codigo)) {
                return detalle;
            }
        }
        return null;
    }
}
