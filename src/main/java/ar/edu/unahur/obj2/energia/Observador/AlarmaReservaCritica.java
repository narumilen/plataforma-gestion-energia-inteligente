package ar.edu.unahur.obj2.energia.Observador;

import ar.edu.unahur.obj2.energia.Bateria;

public class AlarmaReservaCritica implements Suscrito{
    private Boolean alarmaEncendida = false;

    @Override
    public void reaccionar(Bateria bateria, Integer kWh, String movimiento) {
       if(bateria.getEnergia() < 0){
        this.alarmaEncendida = true;
       }
       else {
        alarmaEncendida = false; //por si la alarma ya esta encendida y luego la bateria recibe energia que supera 0 la desactiva
       }
    }

    public Boolean alarmaEstaEncendida(){
        return this.alarmaEncendida;
    }
}
