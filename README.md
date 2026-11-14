# Sistema de Gestion de Pedidos

Aplicacion de consola desarrollada con Spring Boot para la administracion y control de pedidos de una cafeteria o restaurante, implementando una arquitectura MVC limpia y principios de inyeccion de dependencias por constructor con Java Beans administrados mediante `@Component` y `@Service`.

---

## Requisitos Previos

- **Java JDK:** Version 17 o superior (Java 17, 21 o 25).
- **Git:** Para clonar el repositorio.
- **Maven:** No es necesario tenerlo instalado globalmente, el proyecto incluye el Maven Wrapper (`./mvnw`).

---

## Clonar y Ejecutar el Proyecto

### 1. Clonar el repositorio desde GitHub

```bash
git clone https://github.com/Randolh/gestion_pedidos.git
cd gestion_pedidos
```

> Reemplaza `<tu-usuario>` por tu nombre de usuario de GitHub.

### 2. Ejecutar la aplicacion

#### En Linux o macOS:
```bash
./mvnw spring-boot:run
```

#### En Windows (CMD o PowerShell):
```cmd
mvnw.cmd spring-boot:run
```

### 3. Opcion alternativa: Generar y ejecutar el archivo JAR

```bash
# Compilar y empaquetar el proyecto
./mvnw clean package

# Ejecutar el archivo JAR generado
java -jar target/gestion_pedidos-0.0.1-SNAPSHOT.jar
```

---

## Estructura del Proyecto (Arquitectura MVC)

```
campus.randolh.gestion_pedidos
│
├── GestionPedidosApplication.java    // Clase principal con main (solo arranca Spring Boot)
│
├── runner
│   └── AppRunner.java                 // Componente CommandLineRunner (@Component)
│
├── controller
│   └── PedidoController.java         // Controlador del flujo de aplicacion y menus (@Component)
│
├── service
│   ├── PedidoService.java            // Logica de negocio y registro de pedidos en memoria (@Service)
│   └── ProductoService.java          // Catalogo de productos y consultas en memoria (@Service)
│
├── view
│   ├── ConsolaView.java               // Salidas de texto con System.out.println (@Component)
│   └── InputHelper.java               // Lecturas con Scanner, validaciones en bucle e inputs opcionales (@Component)
│
└── model
    ├── Producto.java                  // Entidad producto (codigo, nombre, precio)
    ├── DetallePedido.java             // Entidad detalle (producto, cantidad, subtotal)
    └── Pedido.java                    // Entidad pedido (numero, fecha, detalles, subtotal, impuesto, total)
```

---

## Funcionalidades del Sistema

1. **Ver menu de productos:** Consulta del catalogo con codigos, nombres y precios unitarios.
2. **Crear pedido:**
   - Seleccion de productos por codigo.
   - Solicitud de cantidades con validacion positiva.
   - Actualizacion opcional de cantidades si el producto ya existe en el pedido.
   - Opcion de modificar cantidades antes de facturar mediante entradas opcionales.
   - Calculo automatico de subtotal por producto, subtotal general, impuesto del 10% y total general.
   - Emision de factura legible en consola con numero, fecha, desglose y totales.
3. **Ver resumen de ventas:** Muestra la cantidad total de pedidos registrados y el total acumulado en dinero.
4. **Salir del sistema:** Cierre ordenado de la aplicacion de consola.

---

## Respuestas para la Sustentacion Oral

### 1. Que dependencias existen entre las clases del proyecto?
- `AppRunner` depende de `PedidoController`.
- `PedidoController` depende de `ProductoService`, `PedidoService`, `ConsolaView` e `InputHelper`.
- `ProductoService` y `PedidoService` son servicios independientes que gestionan los datos y la logica de negocio.

### 2. Que clases fueron definidas como beans y por que?
Fueron definidas exclusivamente con las anotaciones permitidas (`@Component` y `@Service`):
- **`@Service` (`ProductoService`, `PedidoService`):** Contienen las reglas de negocio, logica de calculo de subtotales, impuestos (10%), total acumulado y almacenamiento en memoria.
- **`@Component` (`PedidoController`, `ConsolaView`, `InputHelper`, `AppRunner`):** Clases encargadas de la orquestacion, entrada con Scanner, salida con System.out.println y ejecucion del CommandLineRunner.

Se definen como beans para que el contenedor de Spring gestione su ciclo de vida y resuelva sus dependencias de manera desacoplada.

### 3. Como se aplico la inyeccion de dependencias por constructor?
En cada clase de negocio y componente, las dependencias se declaran como atributos `private final` y se reciben explicitamente como parametros en el constructor de la clase. Spring identifica dicho constructor e inyecta automaticamente las instancias correspondientes administradas en el contenedor sin necesidad de usar `@Autowired` en campos ni instanciar dependencias manualmente.

### 4. Que tareas realizo Spring automaticamente que en Java tradicional deberian hacerse manualmente?
- Escaneo de componentes mediante `@SpringBootApplication` / `@ComponentScan`.
- Instanciacion de todas las clases y resolucion del orden de dependencias.
- Inyeccion automatica de dependencias en los constructores.
- Gestion del ciclo de vida de los componentes.
- Ejecucion automatica del flujo de consola tras arrancar el contexto gracias a `CommandLineRunner`.

### 5. Que ventajas identifico al usar Spring frente a Java puro?
- **Bajo acoplamiento:** Las clases reciben sus dependencias listas para usar en vez de instanciarlas directamente dentro de si mismas.
- **Facilidad para pruebas unitarias:** Es sencillo instanciar y probar clases pasando dependencias reales o simuladas.
- **Mantenibilidad y separacion de responsabilidades:** El codigo se organiza claramente siguiendo el patron MVC.
- **Menor codigo repetitivo:** Se elimina la instanciacion manual y encadenada de objetos.

---

## Enlace del Video de la Presentacion

- Link del video:
