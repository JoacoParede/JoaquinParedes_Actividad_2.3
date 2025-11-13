import java.util.ArrayList;

public class Bodega {
    private ArrayList<Producto> inventario;

    public Bodega() {
        this.inventario = new ArrayList<>();
    }

    public void agregarProducto(Producto p) {
        this.inventario.add(p);
    }

    public Producto buscarProducto(String nombre) {
        for (Producto p : inventario) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    public void generarReporteTotal() {
        System.out.println("\n--- REPORTE DE INVENTARIO TOTAL ---");
        for (Producto producto : inventario) {
            System.out.println(producto);
        }
        System.out.println("------------------------------------");
    }
    
    public void generarReporteStockCritico() {
        System.out.println("\n--- REPORTE DE PRODUCTOS EN STOCK CRÍTICO ---");
        boolean hayCriticos = false;
        for (Producto producto : inventario) {
            if (producto.getStock() <= producto.getStockCritico()) {
                System.out.println(producto.getNombre() + " - ¡ATENCIÓN! Stock actual: " + producto.getStock());
                hayCriticos = true;
            }
        }
        if (!hayCriticos) {
            System.out.println("Ningún producto se encuentra en stock crítico.");
        }
        System.out.println("----------------------------------------------");
    }

    public ArrayList<Producto> getInventario() {
        return inventario;
    }
}