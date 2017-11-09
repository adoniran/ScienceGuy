/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author ALUNO
 */
@Entity
@Table(name = "tb_usuario")
@NamedQueries(
        {
            @NamedQuery(name = "Usuarios.todos", query = "SELECT u FROM Usuario u "),
            @NamedQuery(name = "Usuarios.DDDTel", query = "SELECT u FROM Usuario u WHERE u.telefones LIKE :tel")
        })
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "Discriminator_user", discriminatorType = DiscriminatorType.STRING, length = 1)
@DiscriminatorValue(value = "U")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotBlank
    @Size(max = 60, message = "Não é permitido que o login ultrapasse 60 caracteres")
    @Column(name = "login")
    private String login;
    @NotBlank
    @Size(max = 30)
    @Pattern(regexp = "\\p{Upper}{1}\\p{Lower}+", message = "Primeira letra deve ser maiuscula seguida de letras menusculas exemplo:Pedro")
    @Column(name = "nome")
    private String nome;
    @NotNull
    @Email
    @Column(name = "email")
    private String email;
    @NotBlank
    @Size(min = 6, max = 21, message = "A senha deve conter no minimo {min} caracteres e no maximo {max}")
    @Pattern(regexp = "((?=.*\\p{Digit})(?=.*\\p{Lower})(?=.*\\p{Upper})(?=.*\\p{Punct}).{6,20})", message = "A senha deve conter ao menos uma letra menuscula, uma letra maiuscula e um numero")//ATENÇÃO
    @Column(name = "senha")
    private String senha;
    @Size(max = 3, message = "Não é permitido possuir mais de 3 telefones")
    @ElementCollection
    @CollectionTable(name = "tb_telefone",
            joinColumns = @JoinColumn(name = "id"))
    @Column(name = "numero_telefone")
    protected Collection<String> telefones;

    @ManyToMany(mappedBy = "participantes", cascade = CascadeType.ALL)
    private List<Projetos> proj;
    @Valid
    @OneToOne(mappedBy = "donoConta", cascade = CascadeType.ALL, orphanRemoval = true)
    private Conta conta;
    @Embedded
    @Valid
    private Endereco endereco;

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
        conta.setDonoConta(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Collection<String> getTelefones() {
        return telefones;
    }

    public void addTelefone(String telefone) {
        if (telefones == null) {
            telefones = new HashSet<>();
        }
        telefones.add(telefone);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Projetos> getProj() {
        return proj;
    }

    public void addProjeto(Projetos proj) {
        if (this.proj == null) {
            this.proj = new ArrayList<>();
        }

        this.proj.add(proj);

    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
