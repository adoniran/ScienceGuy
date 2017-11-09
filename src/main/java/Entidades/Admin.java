/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author adoniran
 */
@Entity
@Table(name = "tb_admins")
@DiscriminatorValue(value = "A")
@PrimaryKeyJoinColumn(name = "Usuario_ID", referencedColumnName = "ID")
public class Admin extends Usuario implements Serializable {
    @Valid
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Log> log;
    
    @NotBlank
    @Size(min=4 ,max = 12,message = "A senha não deve conter menos que{min}e nem mais que{max}")
    @Pattern(regexp = "((?=.*\\w).{4,12})", message = "A senha não deve conter caracteres")//ATENÇÃO
    @Column(name = "senha_confirmacao")
    private String confirmacao;
    
    public List<Log> getLogs() {
        return log;
    }

    public void addLog(Log log) {
        if (this.log == null) {
            this.log = new ArrayList<>();

        }
        this.log.add(log);

    }

    public String getConfirmacao() {
        return confirmacao;
    }

    public void setConfirmacao(String confirmacao) {
        this.confirmacao = confirmacao;
    }
    

    @Override
    public String toString() {
        return "Entidades.Admin[ id=" + getId() + " ]";
    }

}
