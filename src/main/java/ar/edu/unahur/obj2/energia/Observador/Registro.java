package ar.edu.unahur.obj2.energia.Observador;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.energia.Bateria;

public class Registro implements Suscrito{
    private final List<String> historial = new ArrayList<>();

    @Override
    public void reaccionar(Bateria bateria, Integer kWh, String movimiento) {
        String log = "Bateria con ID: " + bateria.getId() + " realizó " + movimiento + " de " + kWh + " energía.";
        historial.add(log);
    }

    public Integer getTamañoHistorial() {
        return this.historial.size();
    }

}
