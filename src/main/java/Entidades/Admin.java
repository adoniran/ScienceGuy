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
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author adoniran
 */
@Entity
@Table(name="tb_admins")
@DiscriminatorValue(value = "A")
@PrimaryKeyJoinColumn(name="Usuario_ID", referencedColumnName = "ID")
public class Admin extends Usuario implements Serializable  {

@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval=true)
private List<Log> log;

    

    public List<Log> getLogs() {
        return log;
    }

    
    public void addLog(Log log){
        if(this.log == null){
            this.log = new ArrayList<>();
        
        }
        this.log.add(log);
    
    }

    @Override
    public String toString() {
        return "Entidades.Admin[ id=" + getId() + " ]";
    }
    
}
