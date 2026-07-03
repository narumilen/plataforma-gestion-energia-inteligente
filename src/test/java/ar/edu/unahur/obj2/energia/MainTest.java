package ar.edu.unahur.obj2.energia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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
        assertEquals(10, bateria.getEnergia());      
    }

    @Test
    public void queUnaBateriacon80kWhConsume10kWh(){
        Bateria bateria = new Bateria("BAT",80);
        bateria.consumir(10);
        assertEquals(70, bateria.getEnergia());    
    }
}
