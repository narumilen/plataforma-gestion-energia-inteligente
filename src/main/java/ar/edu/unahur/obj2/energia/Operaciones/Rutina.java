package ar.edu.unahur.obj2.energia.Operaciones;

import java.util.ArrayList;
import java.util.List;

public class Rutina implements Operaciones{
    private List<Operaciones> loteOperaciones = new ArrayList<>();

    public void agregarOperacion(Operaciones operacion){
        this.loteOperaciones.add(operacion);
    }

    @Override
    public void execute() {
        for (Operaciones op: loteOperaciones){
            op.execute();
        }
        loteOperaciones.clear();
    }

    @Override
    public void undo() {
        for (Operaciones op: loteOperaciones){
            op.undo();
        }
    }
}
