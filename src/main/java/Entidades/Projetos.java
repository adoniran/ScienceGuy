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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author adoniran
 */
@Entity
@Table(name ="tb_projetos")
public class Projetos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="nome_project")
    private String nome;
    @Column(name="DESCRICAO_project")
    private String descricao;    
    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinTable(name = "tb_usuarios_projetos", joinColumns = {
        @JoinColumn(name = "id_projeto")},
            inverseJoinColumns = {
                @JoinColumn(name = "id_usuario")})
    private List<Usuario> participantes;
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void adicionar(Usuario usuario) {
        if (this.participantes == null) {
            this.participantes = new ArrayList<>();
        }

        participantes.add(usuario);
        usuario.addProjeto(this);
    }

    public List<Usuario> getParticipantes() {
        return participantes;
    
        
}
}