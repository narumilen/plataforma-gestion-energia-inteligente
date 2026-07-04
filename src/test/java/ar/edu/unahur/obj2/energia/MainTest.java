package ar.edu.unahur.obj2.energia;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.energia.Operaciones.Carga;
import ar.edu.unahur.obj2.energia.Operaciones.ControladorOperaciones;
import ar.edu.unahur.obj2.energia.Operaciones.Operaciones;
import ar.edu.unahur.obj2.energia.Operaciones.Rutina;

public class MainTest {

    @Test
    public void queUnaBateriaSeCreaConEnergia70kWh(){
        Bateria bateria = new Bateria("BAT",70);

        assertEquals(70, bateria.getEnergia());
    }

    @Test
    public void queUnaBateriaCargaEnergiaDe70A80(){
        Bateria bateria = new Bateria("BAT",70);
        bateria.cargar(10);
        assertEquals(80, bateria.getEnergia());      
    }

    @Test
    public void queUnaBateriacon80kWhConsume10kWh() throws ReservaInsuficienteException{
        Bateria bateria = new Bateria("BAT",80);
        bateria.consumir(10);
        assertEquals(70, bateria.getEnergia());    
    }

    @Test
    public void consumoSuperaLimiteReservaLanzaExcepcion(){
        Bateria bateria = new Bateria("BAT", 0);

        assertThrows(
            ReservaInsuficienteException.class, 
            () -> {bateria.consumir(5001);}
        );
    }

    @Test
    public void consumirExactamente5000NoLanzaExcepcion(){
        Bateria bateria = new Bateria("BAT", 0);
        assertDoesNotThrow(() -> {bateria.consumir(5000);});
        assertEquals(-5000, bateria.getEnergia());
    }

    @Test 
    public void cargar0lanzaExcepcion(){
        Bateria bateria = new Bateria("BAT", 0);
        assertThrows(IllegalArgumentException.class, 
            () -> {bateria.cargar(0);}
        
        );
    }

    @Test
    public void testRutinaEjecutaMuchasOperaciones() throws ReservaInsuficienteException{
        Bateria bateria = new Bateria("BAT", 0);
        Rutina rutina = new Rutina();

        rutina.agregarOperacion(new Carga(bateria, 30));
        rutina.agregarOperacion(new Carga(bateria, 37));

        rutina.execute();

        assertEquals(67, bateria.getEnergia());
    }

    @Test
    public void testControladorEjecutaOperacion(){
        Bateria bateria = new Bateria("BAt",100);
        ControladorOperaciones controlador = new ControladorOperaciones();
        Operaciones carga = new Carga(bateria, 50);

        controlador.ejecutar(carga);

        assertEquals(150, bateria.getEnergia());
    }
}
