package locarfx.Controller;

import locarfx.Model.Endereco;
import locarfx.DAO.EnderecoDAO;

public class EnderecoController{
    
    EnderecoController(String cep, String rua, String bairro, Integer numero, String complemento){
        Endereco endereco = new Endereco();
        endereco.setCep(cep);
        endereco.setRua(rua);
        endereco.setBairro(bairro);
        endereco.setNumero(numero);
        endereco.setComplemento(complemento);
        
        cadastrarEndereco(endereco);              
    }
    
    public Integer obtemCodigoEndereco(String cep, String rua, String bairro, Integer numero, String complemento){
        
        EnderecoDAO edao = new EnderecoDAO();
        return edao.consultaCodigoEndereco(cep, rua, bairro, numero, complemento);
    }
    
    public void cadastrarEndereco(Endereco endereco){
            EnderecoDAO edao = new EnderecoDAO();
            edao.inserir(endereco);                                
    }   
   
}
