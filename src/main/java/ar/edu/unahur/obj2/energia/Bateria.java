package ar.edu.unahur.obj2.energia;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.energia.Observador.Suscrito;

public class Bateria {
    private String id;
    private Integer energia;
    private List<Suscrito> interesados = new ArrayList<>();
    public Bateria(String id, Integer energia) {
        this.id = id;
        this.energia = energia;
    }
    public Integer getEnergia() {
        return energia;
    }
    public void cargar(Integer energiaACargar) {
        if (energiaACargar <= 0){
            throw new IllegalArgumentException("La cantidad de energia a cargar debe ser mayor a 0");
        }
        this.energia += energiaACargar;

        notificarInteresados(energiaACargar, "carga");
    }
    public void consumir(Integer energiaConsumir) throws ReservaInsuficienteException {
        if (energiaConsumir <= 0){
            throw new IllegalArgumentException("La cantidad de energia a consumir debe ser mayor a 0");
        }        

        if (this.energia - energiaConsumir < -5000){
            throw new ReservaInsuficienteException("Se supera el limite absoluto de reserva");
        }

        this.energia -= energiaConsumir;

        notificarInteresados(energiaConsumir, "consumo");
    }

    public String getId() {
        return id;
    }
    
    //observador

    public void registrarInteresado(Suscrito observador) {
        this.interesados.add(observador);
    }

    public void eliminarInteresado(Suscrito observador){
        this.interesados.remove(observador);
    }

    public void notificarInteresados(Integer kwh, String movimiento){
        for (Suscrito interesado: interesados){
            interesado.reaccionar(this, kwh, movimiento);
        }
    }
}
