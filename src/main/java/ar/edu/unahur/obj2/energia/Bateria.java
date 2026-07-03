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
    public void cargar(Integer energia) {
        this.energia = energia;
    }
    public void consumir(Integer energiaConsumir) {
        this.energia = energia - energiaConsumir;
    }
    public String getId() {
        return id;
    }
}
