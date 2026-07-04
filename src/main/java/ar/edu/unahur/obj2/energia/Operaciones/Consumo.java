package ar.edu.unahur.obj2.energia.Operaciones;

import ar.edu.unahur.obj2.energia.Bateria;
import ar.edu.unahur.obj2.energia.ReservaInsuficienteException;

public class Consumo implements Operaciones{
    private Bateria bateria;
    private Integer consumo;

    public Consumo(Bateria bateria, Integer consumir) {
        if (consumir <= 0) throw new IllegalArgumentException();
        this.bateria = bateria;
        this.consumo = consumir;
    }

    @Override
    public void execute(){
        try{
            bateria.consumir(consumo);
        } catch (ReservaInsuficienteException e){
            throw new IllegalStateException("Error al consumir la carga", e);
        }
    }

    @Override
    public void undo(){
        bateria.cargar(consumo);
    }
}
