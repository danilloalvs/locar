package locarfx.Model;

import locarfx.Model.Enums.Cargo;

public class Usuario {
    private Integer codigo;
    private String nome;
    private String cpf;
    private String email;
    private Cargo cargo;
    private String login;
    private String senha;

    public Usuario(String nome, String cpf, String email, Cargo cargo, String login, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.cargo = cargo;
        this.login = login;
        this.senha = senha;
    }

    public Usuario() {
        
    }


    public Integer getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }
    
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}


