package locarfx.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import locarfx.Infra.FabricaConexao;
import locarfx.Model.Endereco;

/**
 *
 * @author Gabriel
 */
public class EnderecoDAO implements IBasicoDAO<Endereco> {

    @Override
    public void inserir(Endereco endereco) {
        try (Connection connection = FabricaConexao.getConexao()) {
            String sql = "INSERT INTO tbEndereco (END_CEP, END_RUA, END_BAIRRO, END_NUMERO, END_COMPLEMENTO) VALUES (?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, endereco.getCep());
            preparedStatement.setString(2, endereco.getRua());
            preparedStatement.setString(3, endereco.getBairro());
            preparedStatement.setInt(4, endereco.getNumero());
            preparedStatement.setString(5, endereco.getComplemento());

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void alterar(Endereco endereco) {
        try (Connection connection = FabricaConexao.getConexao()) {
            String sql = "UPDATE tbEndereco SET END_CEP = ?, "
                    + "END_RUA = ?, "
                    + "END_BAIRRO = ?, "
                    + "END_NUMERO = ?, "
                    + "END_COMPLEMENTO = ?, "
                    + "WHERE END_CODIGO = ?";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setString(1, endereco.getCep());
            preparedStatement.setString(3, endereco.getRua());
            preparedStatement.setString(4, endereco.getBairro());
            preparedStatement.setInt(5, endereco.getNumero());
            preparedStatement.setString(6, endereco.getComplemento());
            preparedStatement.setInt(8, endereco.getCodigo());       
                                    
            preparedStatement.executeQuery();
            preparedStatement.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void remover(Endereco endereco) {
        try (Connection connection = FabricaConexao.getConexao()) {
            String sql = "DELETE FROM tbEndereco WHERE END_CODIGO = ?";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, endereco.getCodigo());
            preparedStatement.executeQuery();
            preparedStatement.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ArrayList<Endereco> retornarTodos() {
        try (Connection connection = FabricaConexao.getConexao()) {
            String sql ="SELECT END_CODIGO AS codigoEndereco, "
                    + "END_CEP AS cepEndereco, "
                    + "END_RUA AS ruaEndereco, "
                    + "END_BAIRRO AS bairroEndereco, "
                    + "END_NUMERO AS numeroEndereco, "
                    + "END_COMPLEMENTO AS complementoEndereco "
                    + "FROM tbEndereco";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            ArrayList<Endereco> enderecos = new ArrayList<Endereco>();
            
            while(resultSet.next()){
                Endereco endereco = new Endereco();
                
                endereco.setCodigo(resultSet.getInt("codigoEndereco"));
                endereco.setCep(resultSet.getString("cepEndereco"));
                endereco.setRua(resultSet.getString("ruaEndereco"));
                endereco.setBairro(resultSet.getString("bairroEndereco"));
                endereco.setNumero(resultSet.getInt("numeroEndereco"));
                endereco.setComplemento(resultSet.getString("complementoEndereco"));

                enderecos.add(endereco);
            }
            
            return enderecos;         
                  
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    @Override
    public Endereco buscarPorCodigo(Integer codigo) {
        
        try (Connection connection = FabricaConexao.getConexao()) {
            String sql;
            sql = "SELECT END_CODIGO AS codigoEndereco, "
                    + "END_CEP AS cepEndereco, "
                    + "END_RUA AS ruaEndereco, "
                    + "END_BAIRRO AS bairroEndereco, "
                    + "END_NUMERO AS numeroEndereco, "
                    + "END_COMPLEMENTO AS complementoEndereco "
                    + "FROM tbEndereco "
                    + "WHERE END_CODIGO = ?";
           

            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

            preparedStatement.setInt(1, codigo);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Endereco endereco = new Endereco();
                
                endereco.setCodigo(resultSet.getInt("codigoEndereco"));
                endereco.setCep(resultSet.getString("cepEndereco"));
                endereco.setRua(resultSet.getString("ruaEndereco"));
                endereco.setBairro(resultSet.getString("bairroEndereco"));
                endereco.setNumero(resultSet.getInt("numeroEndereco"));
                endereco.setComplemento(resultSet.getString("complementoEndereco"));
            
                return endereco;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Integer consultaCodigoEndereco(String cep, String rua, String bairro, Integer numero, String complemento) {
        try (Connection connection = FabricaConexao.getConexao()) {
            String sql = "SELECT END_CODIGO AS codigoEndereco\n"
                    + "FROM tbEndereco\n"
                    + "WHERE END_CEP = ? AND\n"
                    + "END_RUA = ? AND\n"
                    + "END_BAIRRO = ? AND\n"
                    + "END_NUMERO = ? AND\n"
                    + "END_COMPLEMENTO = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

            preparedStatement.setString(1, cep);
            preparedStatement.setString(2, rua);
            preparedStatement.setString(3, bairro);
            preparedStatement.setInt(4, numero);
            preparedStatement.setString(5, complemento);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("codigoEndereco");               
            } else {
                return null;
            }
            

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    @Override
    public Endereco buscarPorNome(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
