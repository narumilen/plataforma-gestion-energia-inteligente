package ar.edu.unahur.obj2.energia.Observador;

import ar.edu.unahur.obj2.energia.Bateria;

public class NotificacionAdministrador implements Suscrito{

    private String ultimoMensaje;

    @Override
    public void reaccionar(Bateria bateria, Integer kWh, String movimiento) {
        if (movimiento.equals("carga")){
            this.ultimoMensaje = "Se cargaron " + kWh + " kwh en su bateria " + bateria.getId() + " movimiento: " + movimiento;
        }
        else{
            this.ultimoMensaje = "Se consumieron " + kWh + " kwh en su bateria " + bateria.getId() + " movimiento: " + movimiento;
        }
    }

    public String getUltimoMensaje() {
        return ultimoMensaje;
    }

}
