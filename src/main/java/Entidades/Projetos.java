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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author adoniran
 */
@Entity
@Table(name ="tb_projetos")
@NamedQuery(name="Projetos.todos", query="SELECT p FROM Projetos p")
public class Projetos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="nome_project")
    private String nome;
    @Column(name="descricao_project")
    private String descricao;    
    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinTable(name = "tb_usuarios_projetos", joinColumns = {
        @JoinColumn(name = "id_projeto")},
            inverseJoinColumns = {
                @JoinColumn(name = "id_usuario")})
    private List<Usuario> participantes;
    
    @Column(name="motivacao_necessidade")
    private String MotivacaoNecessidade;
    
    @Enumerated(EnumType.STRING)
    @Column(name="necessidade")
    private Necessidade necessidade;
    
    @Enumerated(EnumType.STRING)
    @Column(name="situacao")
    private Situacao situacao;
    
    @Enumerated(EnumType.STRING)
    @Column(name="area_projeto")
    private Area area;
    
    @OneToMany(mappedBy = "projeto", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Arquivos> arquivos;
    
    

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

    public void addUsuario(Usuario usuario) {
        if (this.participantes == null) {
            this.participantes = new ArrayList<>();
        }

        participantes.add(usuario);
        usuario.addProjeto(this);
    }

    public List<Usuario> getParticipantes() {
        return participantes;
    
        
}

    public String getMotivacaoNecessidade() {
        return MotivacaoNecessidade;
    }

    public void setMotivacaoNecessidade(String MotivacaoNecessidade) {
        this.MotivacaoNecessidade = MotivacaoNecessidade;
    }

    public Necessidade getNecessidade() {
        return necessidade;
    }

    public void setNecessidade(Necessidade necessidade) {
        this.necessidade = necessidade;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public List<Arquivos> getArquivos() {
        return arquivos;
    }

     public void addArquivo(Arquivos arq) {
        if (this.arquivos == null) {
            this.arquivos = new ArrayList<>();
        }

        arquivos.add(arq);
        arq.setProjeto(this);
    }

}