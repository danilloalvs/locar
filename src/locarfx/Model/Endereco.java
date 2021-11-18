package locarfx.Model;

public class Endereco {
    private Integer codigo;   
    private String cep;
    private String rua;
    private String bairro;
    private Integer numero;
    private String complemento;
    
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    
    public String getCep(){
        return cep;
    }
    
    public void setCep(String valor){
        cep = valor;
    }
    
    public String getRua(){
        return rua;
    }
    
    public void setRua(String valor){
        rua = valor;
    }
    
    public String getBairro(){
        return bairro;
    }
    
    public void setBairro(String valor){
        bairro = valor;
    }
    
    public Integer getNumero(){
        return numero;
    }
    
    public void setNumero(int valor){
        numero = valor;
    }
    
    public String getComplemento(){
        return complemento;
        
    }
    
    public void setComplemento(String valor){
        complemento = valor;
    }
}
