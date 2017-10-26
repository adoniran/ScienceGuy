/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author adoniran
 */
@Entity
@Table(name="tb_Msg_proj")
@DiscriminatorValue(value = "P")
@PrimaryKeyJoinColumn(name="MSG_ID", referencedColumnName = "ID")
public class MensagemProj extends Mensagem implements Serializable {

   @Column(name="titulo")
   private String titulo;
   
   @Column (name="votos")
   private Long votos;
   
   @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_projeto", referencedColumnName = "ID")
   private Projetos projetoDestino;
   
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getVotos() {
        return votos;
    }

    public void setVotos(Long votos) {
        this.votos = votos;
    }

    public Projetos getProjetoDestino() {
        return projetoDestino;
    }

    public void setProjetoDestino(Projetos projetoDestino) {
        this.projetoDestino = projetoDestino;
    }
    
   
    @Override
    public String toString() {
        return "Entidades.Msg_proj[ id=" + getId() + " ]";
    }
    
}
