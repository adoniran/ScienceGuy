package Entidades;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author adoniran
 */
 

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidadorTipoConta implements ConstraintValidator<ValidaTipoConta, String> {
    
    private List<String> TipoConta;

    @Override
    public void initialize(ValidaTipoConta validaTipoConta) {
        this.TipoConta = new ArrayList<>();
        
        this.TipoConta.add("CONTA SALARIO");
        this.TipoConta.add("POUPANÇA");
        this.TipoConta.add("CONTA CORRENTE");
}

 @Override
    public boolean isValid(String valor, ConstraintValidatorContext context) {
        return valor == null ? false : TipoConta.contains(valor);
}

   
}