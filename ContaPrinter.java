package com.mycompany.exercicioclinica;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author vival
 */
import java.text.NumberFormat;

public class ContaPrinter {
    public String imprimaConta(Prontuario prontuario) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        StringBuilder conta = new StringBuilder("----------------------------------------------------------------------------------------------");

        float valorDiarias = 0.0f;
        float valorTotalProcedimentos = 0.00f;

        if (prontuario.getInternacao() != null) {
            valorDiarias = prontuario.getInternacao().calcularValorDiarias();
        }

        for (Procedimento procedimento : prontuario.getProcedimentos()) {
            valorTotalProcedimentos += procedimento.calcularCusto();
        }

        conta.append("\nA conta do(a) paciente ").append(prontuario.getNomePaciente()).append(" tem valor total de __ ")
                .append(formatter.format(valorDiarias + valorTotalProcedimentos)).append(" __");
        conta.append("\n\nConforme os detalhes abaixo:");

        if (prontuario.getInternacao() != null) {
            conta.append("\n\nValor Total Diárias:\t\t\t").append(formatter.format(valorDiarias));
            conta.append("\n\t\t\t\t\t").append(prontuario.getInternacao().getQtdeDias()).append(" diária")
                    .append(prontuario.getInternacao().getQtdeDias() > 1 ? "s" : "")
                    .append(" em ").append(prontuario.getInternacao().getTipoLeito() == TipoLeito.APARTAMENTO ? "apartamento" : "enfermaria");
        }

        if (!prontuario.getProcedimentos().isEmpty()) {
            conta.append("\n\nValor Total Procedimentos:\t\t").append(formatter.format(valorTotalProcedimentos));

            int qtdeProcedimentosBasicos = 0;
            int qtdeProcedimentosComuns = 0;
            int qtdeProcedimentosAvancados = 0;

            for (Procedimento procedimento : prontuario.getProcedimentos()) {
                switch (procedimento.getTipoProcedimento()) {
                    case BASICO:
                        qtdeProcedimentosBasicos++;
                        break;
                    case COMUM:
                        qtdeProcedimentosComuns++;
                        break;
                    case AVANCADO:
                        qtdeProcedimentosAvancados++;
                        break;
                }
            }

            if (qtdeProcedimentosBasicos > 0) {
                conta.append("\n\t\t\t\t\t").append(qtdeProcedimentosBasicos).append(" procedimento").append(qtdeProcedimentosBasicos > 1 ? "s" : "").append(" básico").append(qtdeProcedimentosBasicos > 1 ? "s" : "");
            }

            if (qtdeProcedimentosComuns > 0) {
                conta.append("\n\t\t\t\t\t").append(qtdeProcedimentosComuns).append(" procedimento").append(qtdeProcedimentosComuns > 1 ? "s" : "").append(" comum").append(qtdeProcedimentosComuns > 1 ? "s" : "");
            }

            if (qtdeProcedimentosAvancados > 0) {
                conta.append("\n\t\t\t\t\t").append(qtdeProcedimentosAvancados).append(" procedimento").append(qtdeProcedimentosAvancados > 1 ? "s" : "").append(" avançado").append(qtdeProcedimentosAvancados > 1 ? "s" : "");
            }
        }

        conta.append("\n\nVolte sempre, a casa é sua!");
        conta.append("\n----------------------------------------------------------------------------------------------");

        return conta.toString();
    }
}

