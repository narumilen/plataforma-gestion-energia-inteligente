package ar.edu.unahur.obj2.energia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    private void queUnaBateriaSeCreaConEnergia70kWh(){
        Bateria bateria = new Bateria("BAT",70);

        assertEquals(70, bateria.getEnergia());
    }
}
