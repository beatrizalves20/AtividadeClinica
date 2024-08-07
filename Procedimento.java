package com.mycompany.exercicioclinica;


/**
 *
 * @author vival
 */
public class Procedimento {
    private TipoProcedimento tipoProcedimento;

    public Procedimento(TipoProcedimento tipoProcedimento) {
        this.tipoProcedimento = tipoProcedimento;
    }

    public TipoProcedimento getTipoProcedimento() {
        return tipoProcedimento;
    }

    public void setTipoProcedimento(TipoProcedimento tipoProcedimento) {
        this.tipoProcedimento = tipoProcedimento;
    }
    
    
    public float calcularCusto() {
        return switch (tipoProcedimento) {
            case BASICO -> 50.00f;
            case COMUM -> 150.00f;
            case AVANCADO -> 500.00f;
            default -> 0.00f;
        };
    }
}