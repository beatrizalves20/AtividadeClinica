package com.mycompany.exercicioclinica;


import com.mycompany.exercicioclinica.ContaCalcular;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author vival
 */
public class ContaCalcularImpl implements ContaCalcular {

    @Override
    public String calcularConta(Prontuario prontuario) {
        return prontuario.imprimaConta();
    }
    
}
