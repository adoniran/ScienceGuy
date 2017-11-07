/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.List;
import java.util.ArrayList;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author adoniran
 */
public class ValidadorCategoria implements ConstraintValidator<Evento, String> {
    
    private List<String> TipoEvento;

    @Override
    public void initialize(Evento evento) {
        this.TipoEvento = new ArrayList<>();
        this.TipoEvento.add("CANCELAMENTO");
        this.TipoEvento.add("DADOS");
        this.TipoEvento.add("FINANCEIRO");
        this.TipoEvento.add("ERROR");
        this.TipoEvento.add("MANUTENÇÃO");
        this.TipoEvento.add("GERENCIA");
}

 @Override
    public boolean isValid(String valor, ConstraintValidatorContext context) {
        return valor == null ? false : TipoEvento.contains(valor);
}
}