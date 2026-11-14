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
        // Omite la ejecucion interactiva durante pruebas unitarias
        for (StackTraceElement elemento : Thread.currentThread().getStackTrace()) {
            if (elemento.getClassName().startsWith("org.junit.")) {
                return;
            }
        }
        pedidoController.iniciar();
    }
}
