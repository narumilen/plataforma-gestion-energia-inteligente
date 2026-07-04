package ar.edu.unahur.obj2.energia;

public class Bateria {
    private String id;
    private Integer energia;
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
    }
    public void consumir(Integer energiaConsumir) throws ReservaInsuficienteException {
        if (energiaConsumir <= 0){
            throw new IllegalArgumentException("La cantidad de energia a consumir debe ser mayor a 0");
        }        

        if (this.energia - energiaConsumir < -5000){
            throw new ReservaInsuficienteException("Se supera el limite absoluto de reserva");
        }

        this.energia -= energiaConsumir;

    }

    public String getId() {
        return id;
    }
}
