/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author adoniran
 */
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/*
 * Todos os campos de Endereco serão armazenados na mesma tabela 
 * que armazena os dados de Usuario.
 */
@Embeddable
public class Endereco implements Serializable {

    @NotBlank
    @Size(max = 150)
    @Column(name = "logadouro")
    private String logradouro;
    @NotBlank
    @Size(max = 150)
    @Column(name = "bairro")
    private String bairro;
    @NotNull
    @Min(1)
    @Max(99999)
    @Column(name = "numero")
    private Integer numero;
    
    @Size(max = 30)
    @Column(name = "complemento")
    private String complemento;
    @Pattern(regexp = "[0-9]{5}-[0-9]{3}", message = "O cep deve conter 5 digitos seguidos de (-) e mais 3 digitos, como o seguinte modelo: 12345-678")
    @Column(name = "CEP")
    private String cep;
    @NotBlank
    @Size(max = 150)
    @Column(name = "cidade")
    private String cidade;
    @Size(min=2,max=2, message="O campo deve conter {min} caracteres")
    @Pattern(regexp ="\\p{Upper}{2}",message ="O campo apenas pode conter letras maiusculas")
    @Column(name = "estado")
    private String estado;

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
