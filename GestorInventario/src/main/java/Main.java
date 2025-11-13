import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bodega bodega = new Bodega();
        Scanner scanner = new Scanner(System.in);

        bodega.agregarProducto(new Producto("Azúcar 1kg", 20, 10));
        bodega.agregarProducto(new Producto("Aceite 1L", 5, 5));
        bodega.agregarProducto(new Producto("Arroz 1kg", 30, 15));

        int opcion;
        do {
            System.out.println("\n=== GESTOR DE INVENTARIO (4 Casos de Uso) ===");
            System.out.println("1. Gestionar Productos (Crear/Listar)");
            System.out.println("2. Registrar Entrada (Sumar Stock)");
            System.out.println("3. Registrar Salida (Restar Stock)");
            System.out.println("4. Generar Reportes (Total/Crítico)");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                scanner.nextLine();
                opcion = -1;
            }

            switch (opcion) {
                case 1: manejarGestionProductos(bodega, scanner); break;
                case 2: manejarRegistroStock(bodega, scanner, true); break;
                case 3: manejarRegistroStock(bodega, scanner, false); break;
                case 4: manejarReportes(bodega, scanner); break;
                case 0: System.out.println("Saliendo del sistema. ¡Adiós!"); break;
                default: if (opcion != -1) System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);

        scanner.close();
    }
    
    private static void manejarGestionProductos(Bodega bodega, Scanner scanner) {
        System.out.println("\n--- GESTIÓN DE PRODUCTOS ---");
        System.out.println("1. Listar productos");
        System.out.println("2. Crear nuevo producto");
        System.out.print("Seleccione una opción: ");
        
        int opcion = scanner.nextInt();
        scanner.nextLine();

        if (opcion == 1) {
            bodega.generarReporteTotal();
        } else if (opcion == 2) {
            System.out.print("Nombre del nuevo producto: ");
            String nombre = scanner.nextLine();
            System.out.print("Stock inicial: ");
            int stock = scanner.nextInt();
            System.out.print("Stock crítico: ");
            int critico = scanner.nextInt();
            scanner.nextLine();
            
            bodega.agregarProducto(new Producto(nombre, stock, critico));
            System.out.println("Producto '" + nombre + "' agregado.");
        }
    }
    
    private static void manejarRegistroStock(Bodega bodega, Scanner scanner, boolean esEntrada) {
        String operacion = esEntrada ? "ENTRADA" : "SALIDA";
        System.out.println("\n--- REGISTRAR " + operacion + " ---");
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();
        
        Producto p = bodega.buscarProducto(nombre);
        
        if (p == null) {
            System.out.println("Producto no encontrado.");
            return;
        }
        
        System.out.print("Cantidad a " + (esEntrada ? "sumar" : "restar") + ": ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();

        if (esEntrada) {
            p.agregarStock(cantidad); 
            System.out.println("Se agregaron " + cantidad + " unidades. Nuevo stock: " + p.getStock());
        } else {
            boolean exito = p.restarStock(cantidad);
            if (exito) {
                System.out.println("Se restaron " + cantidad + " unidades. Nuevo stock: " + p.getStock());
            } else {
                System.out.println("ERROR: Venta fallida. Stock insuficiente para vender " + cantidad + " unidades. Stock actual: " + p.getStock());
            }
        }
    }
    
    private static void manejarReportes(Bodega bodega, Scanner scanner) {
        System.out.println("\n--- REPORTES ---");
        System.out.println("1. Inventario Total");
        System.out.println("2. Stock Crítico");
        System.out.print("Seleccione el reporte: ");
        
        int opcion = scanner.nextInt();
        scanner.nextLine();

        if (opcion == 1) {
            bodega.generarReporteTotal();
        } else if (opcion == 2) {
            bodega.generarReporteStockCritico();
        }
    }
}