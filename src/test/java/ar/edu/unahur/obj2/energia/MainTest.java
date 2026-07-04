package ar.edu.unahur.obj2.energia;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.energia.Observador.AlarmaReservaCritica;
import ar.edu.unahur.obj2.energia.Observador.NotificacionAdministrador;
import ar.edu.unahur.obj2.energia.Observador.Registro;
import ar.edu.unahur.obj2.energia.Observador.Suscrito;
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

    @Test
    public void bateriaNotificaInteresadosAlCargar(){
        Bateria bateria = new Bateria("BAT", 100);
        final Boolean[] fueNotificado = {false};
        Suscrito observador = (bat, kwh, mov) -> fueNotificado[0] = true;

        bateria.registrarInteresado(observador);
        bateria.cargar(50);

        assertTrue(fueNotificado[0], "Los inscriptos fuerom notificados");
    }

    
    @Test
    public void sistemasreaccionanYAlarmaSeEnciende() throws ReservaInsuficienteException{
        Bateria bateria = new Bateria("bat", 1000);
        Registro auditoria = new Registro();
        NotificacionAdministrador administrador = new NotificacionAdministrador();
        AlarmaReservaCritica alarma = new AlarmaReservaCritica();

        bateria.registrarInteresado(auditoria);
        bateria.registrarInteresado(alarma);
        bateria.registrarInteresado(administrador);

        bateria.cargar(500);

        assertEquals(1, auditoria.getTamañoHistorial());
        assertEquals("Se cargaron 500 kwh en su bateria bat movimiento: carga", administrador.getUltimoMensaje());
        assertEquals(false, alarma.alarmaEstaEncendida());

        bateria.consumir(2000);

        assertEquals(2, auditoria.getTamañoHistorial());
         assertEquals("Se consumieron 2000 kwh en su bateria bat movimiento: consumo", administrador.getUltimoMensaje());
        assertEquals(true, alarma.alarmaEstaEncendida());
    }
}
