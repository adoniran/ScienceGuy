/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author adoniran
 */
@Entity
@Table(name="tb_Msg_User")
@DiscriminatorValue(value = "U")
@PrimaryKeyJoinColumn(name="MSG_ID", referencedColumnName = "ID")
public class MensagemUser extends Mensagem implements Serializable {
//cascade duvida: existe usuario sem mensagem mas não existe mensagem sem usuario como representar?
@OneToOne(optional=false)
@JoinColumn(name="id_destinatario",referencedColumnName = "ID")
private Usuario destinatario;

    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }
    
    @Override
    public String toString() {
        return "Entidades.Msg_Usuario[ id=" + getId() + " ]";
    }
    
}
