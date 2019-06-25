package jeff.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
 
@Entity
public class Articulador {
   
    @Id
    @GeneratedValue
    private Integer id;
    @Column(columnDefinition="text")
    private String nome;
    @Column(columnDefinition="text")
    private String matricula;
    @Column(columnDefinition="text")
    private String celula;
    @Column(columnDefinition="text")
    private String senha;
   
    public Articulador() {
       
    }
       
    public Articulador(Integer id, String nome, String matricula, String celula, String senha) {
        super();
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.celula = celula;
        this.senha = senha;
    }
   
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public String getCelula() {
        return celula;
    }
    public void setCelula(String celula) {
        this.celula = celula;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
 
    @Override
    public String toString() {
        return "Curso [id=" + id + ", nome=" + nome + ", matricula=" + matricula +  ", celula=" + celula +"]";
    }
}
