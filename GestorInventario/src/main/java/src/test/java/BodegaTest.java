import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BodegaTest {

    private Producto productoParaPruebas;

    @BeforeEach
    public void setUp() {
        productoParaPruebas = new Producto("Leche", 10, 5);
    }

    @Test
    public void pruebaAgregarStockSumaCorrectamente() {
        int stockInicial = productoParaPruebas.getStock(); 
        int cantidadAAgregar = 5;

        productoParaPruebas.agregarStock(cantidadAAgregar);

        int stockEsperado = stockInicial + cantidadAAgregar; 
        assertEquals(stockEsperado, productoParaPruebas.getStock(), "El método agregarStock debe sumar correctamente.");
    }

    @Test
    public void pruebaRestarStockDescuentaCorrectamente() {
        int stockInicial = productoParaPruebas.getStock();
        int cantidadARestar = 3;

        boolean exito = productoParaPruebas.restarStock(cantidadARestar);

        assertTrue(exito, "La operación de restar debe ser exitosa.");
        int stockEsperado = stockInicial - cantidadARestar;
        assertEquals(stockEsperado, productoParaPruebas.getStock(), "El método restarStock debe descontar correctamente.");
    }

    @Test
    public void pruebaRestarStockNoPermiteNegativos() {
        int stockInicial = productoParaPruebas.getStock();
        int cantidadARestar = 11; 

        boolean exito = productoParaPruebas.restarStock(cantidadARestar);

        assertFalse(exito, "La operación de restar NO debe ser exitosa si la venta es mayor al stock.");
        assertEquals(stockInicial, productoParaPruebas.getStock(), "El stock debe permanecer inalterado si la venta falla.");
    }
}