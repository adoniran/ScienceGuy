/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
/**
 *
 * @author adoniran
 */
@Entity
@Table(name="tb_menssagem")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name ="Discriminator_MSG",discriminatorType= DiscriminatorType.STRING,length =1)
public abstract class Mensagem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    @Column(name="menssagem")
    private String Menssagem;
    
    @NotNull
    @OneToOne(optional=false,cascade=CascadeType.ALL)
    @JoinColumn(name="id_remetente",referencedColumnName = "ID")
    private Usuario remetente;
    
    @Column(name = "Data_MSG")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataMsg;
    
    @PrePersist
    public void setDataCriacao() {
        this.setDataMsg(new Date());
}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenssagem() {
        return Menssagem;
    }

    public void setMenssagem(String Menssagem) {
        this.Menssagem = Menssagem;
    }

    public Usuario getRemetente() {
        return remetente;
    }

    public void setRemetente(Usuario remetente) {
        this.remetente = remetente;
    }

    public Date getDataMsg() {
        return dataMsg;
    }

    public void setDataMsg(Date dataMsg) {
        this.dataMsg = dataMsg;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mensagem)) {
            return false;
        }
        Mensagem other = (Mensagem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.menssagem[ id=" + id + " ]";
    }
    
}
