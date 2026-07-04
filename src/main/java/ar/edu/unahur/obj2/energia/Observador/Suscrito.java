package ar.edu.unahur.obj2.energia.Observador;

import ar.edu.unahur.obj2.energia.Bateria;

public interface Suscrito {
    void reaccionar(Bateria bateria, Integer kWh, String movimiento);
}
