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
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author adoniran
 */
@Entity
@Table(name ="tb_projetos")
@NamedQuery(name="Projetos.todos", query="SELECT p FROM Projetos p")
@NamedNativeQueries(
        {
            @NamedNativeQuery(
                    name = "PROJETO.PorNome",
                    query = "SELECT id, nome_project, motivacao_necessidade, necessidade, situacao, area_projeto FROM tb_projetos WHERE nome_project = ?1",
                    resultClass = Projetos.class
            )
        }
)
public class Projetos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    @Size(max=30, message="Apenas {max} são permitidos")
    @Pattern(regexp = "\\p{Upper}{1}\\p{Lower}+", message = "Primeira letra Do nome do Seu projeto Deve ser maiuscula")
    @Column(name="nome_project")
    private String nome;
    @NotBlank
    @Column(name="descricao_project")
    private String descricao;    
    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinTable(name = "tb_usuarios_projetos", joinColumns = {
        @JoinColumn(name = "id_projeto")},
            inverseJoinColumns = {
                @JoinColumn(name = "id_usuario")})
    private List<Usuario> participantes;
    @Size(max=256)
    @Column(name="motivacao_necessidade")
    private String MotivacaoNecessidade;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name="necessidade")
    private Necessidade necessidade;
    @NotNull
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