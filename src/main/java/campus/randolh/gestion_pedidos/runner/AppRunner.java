package campus.randolh.gestion_pedidos.runner;

import campus.randolh.gestion_pedidos.controller.PedidoController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    private final PedidoController pedidoController;

    // Inyeccion de dependencias por constructor
    public AppRunner(PedidoController pedidoController) {
        this.pedidoController = pedidoController;
    }

    // Ejecucion del flujo principal
    @Override
    public void run(String... args) {
        pedidoController.iniciar();
    }
}
