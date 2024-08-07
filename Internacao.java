package com.mycompany.exercicioclinica;


/**
 *
 * @author vival
 */
public class Internacao {
    private TipoLeito tipoLeito;
    private int qtdeDias;

    public Internacao(TipoLeito tipoLeito, int qtdeDias) {
        this.tipoLeito = tipoLeito;
        this.qtdeDias = qtdeDias;
    }

    public TipoLeito getTipoLeito() {
        return tipoLeito;
    }

    public void setTipoLeito(TipoLeito tipoLeito) {
        this.tipoLeito = tipoLeito;
    }

    public int getQtdeDias() {
        return qtdeDias;
    }

    public void setQtdeDias(int qtdeDias) {
        this.qtdeDias = qtdeDias;
    }
    
    public float calcularValorDiarias() {
        float valorDiarias = 0.0f;
        switch (tipoLeito) {
            case ENFERMARIA -> {
                if (qtdeDias <= 3) {
                    valorDiarias += 40.00 * qtdeDias;
                } else if (qtdeDias <= 8) {
                    valorDiarias += 35.00 * qtdeDias;
                } else {
                    valorDiarias += 30.00 * qtdeDias;
                }
            }
            case APARTAMENTO -> {
                if (qtdeDias <= 3) {
                    valorDiarias += 100.00 * qtdeDias;
                } else if (qtdeDias <= 8) {
                    valorDiarias += 90.00 * qtdeDias;
                } else {
                    valorDiarias += 80.00 * qtdeDias;
                }
            }
        }
        return valorDiarias;
    }
}
