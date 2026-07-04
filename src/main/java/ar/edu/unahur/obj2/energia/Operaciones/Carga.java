package ar.edu.unahur.obj2.energia.Operaciones;

import ar.edu.unahur.obj2.energia.Bateria;
import ar.edu.unahur.obj2.energia.ReservaInsuficienteException;

public class Carga implements Operaciones{
    private Bateria bateria;
    private Integer carga;

    public Carga(Bateria bateria, Integer cargar) {
        if (cargar <= 0) throw new IllegalArgumentException();
        this.bateria = bateria;
        this.carga = cargar;
    }

    @Override
    public void execute(){
        bateria.cargar(carga);
    }

    @Override
    public void undo(){
        try{
            bateria.consumir(carga);
        } catch (ReservaInsuficienteException e){
            throw new IllegalStateException("error al revertir la carga", e);
        }
    }
}
