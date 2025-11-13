public class Producto {
    private String nombre;
    private int stock;
    private int stockCritico;

    public Producto(String nombre, int stockInicial, int stockCritico) {
        this.nombre = nombre;
        this.stock = stockInicial;
        this.stockCritico = stockCritico;
    }

    public String getNombre() {
        return nombre;
    }

    public int getStock() {
        return stock;
    }
    
    public int getStockCritico() {
        return stockCritico;
    }

    public void agregarStock(int cantidad) {
        if (cantidad > 0) {
            this.stock += cantidad;
        }
    }

    public boolean restarStock(int cantidad) {
        if (cantidad > 0 && this.stock >= cantidad) {
            this.stock -= cantidad;
            return true;
        }
        return false; 
    }

    @Override
    public String toString() {
        return "Producto: " + nombre + " | Stock: " + stock + " | Stock Crítico: " + stockCritico;
    }
}