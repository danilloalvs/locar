package locarfx.Model;

import java.util.Date;

/**
 *
 * @author Gabriel
 */
public class Cliente {
    private Integer codigoEndereco;
    private Integer codigo;   
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private Date dataCadastro;
    
    public Cliente(Integer codigoEndereco, String nome, String cpf, String telefone, String email, Date dataCadastro){
        this.codigoEndereco = codigoEndereco;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.dataCadastro = dataCadastro;
    }
    
    public Cliente(){
        
    }
    
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public Integer getCodigoEndereco(){
        return codigoEndereco;
    }
    
    public void setCodigoEndereco(int codigo){
        codigoEndereco = codigo;
    }    
    
    public String getNome(){
        return nome;
    }
    
    public void setNome(String valor){
        nome = valor;
    }
    
    public String getCPF(){
        return cpf;
    }
    
    public void setCPF(String valor){
        cpf = valor;
    }
    
    public String getTelefone(){
        return telefone;
    }
    
    public void setTelefone(String valor){
        telefone = valor;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String valor){
        email = valor;
    }
    
    public Date getDataCadastro(){
        return dataCadastro;
    }
    
    public void setDataCadastro(Date valor){
        dataCadastro = valor;
    }
    
}
