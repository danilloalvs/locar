package locarfx.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import locarfx.Infra.FabricaConexao;
import locarfx.Model.Cliente;
import java.sql.ResultSet;

public class ClienteDAO implements IBasicoDAO<Cliente> {

    @Override
    public void inserir(Cliente cliente) {
        try (Connection connection = FabricaConexao.getConexao()) {
            String sql = "INSERT INTO tbCliente (END_CODIGO, CLN_NOME, CLN_CPF, CLN_EMAIL, CLN_TELEFONE, CLN_DATACADASTRO) VALUES (?,?,?,?,?,?)";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, cliente.getCodigoEndereco());
            preparedStatement.setString(2, cliente.getNome());
            preparedStatement.setString(3, cliente.getCPF());
            preparedStatement.setString(4, cliente.getEmail());
            preparedStatement.setString(5, cliente.getTelefone());                             
            java.sql.Date sqlDate = new java.sql.Date(cliente.getDataCadastro().getTime());
            preparedStatement.setDate(6, sqlDate);
            
            preparedStatement.executeUpdate();
            
            preparedStatement.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void alterar(Cliente cliente) {
        try (Connection connection = FabricaConexao.getConexao()) {
            String sql = "UPDATE tbCliente SET CLN_NOME = ?, "
                    + "CLN_CPF = ?, "
                    + "CLN_EMAIL = ?, "
                    + "CLN_TELEFONE = ?, "
                    + "CLN_DATACADASTRO = ? "
                    + "WHERE CLN_CODIGO = ?";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getCPF());
            preparedStatement.setString(3, cliente.getEmail());
            preparedStatement.setString(4, cliente.getTelefone());
            
            java.sql.Date sqlDate = new java.sql.Date(cliente.getDataCadastro().getTime());
            
            preparedStatement.setDate(5, sqlDate);
            preparedStatement.setInt(6, cliente.getCodigo());       
                                    
            preparedStatement.executeQuery();
            preparedStatement.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void remover(Cliente cliente) {
        try (Connection connection = FabricaConexao.getConexao()) {
            String sql = "DELETE FROM tbCliente WHERE CLN_CODIGO = ?";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, cliente.getCodigo());
            preparedStatement.executeQuery();
            preparedStatement.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


    @Override
    public ArrayList<Cliente> retornarTodos() {
        try (Connection connection = FabricaConexao.getConexao()) {
            String sql ="SELECT END_CODIGO AS codigoEndereco, "
                    + "CLN_CODIGO AS codigoCliente, "
                    + "CLN_NOME AS nomeCliente, "
                    + "CLN_CPF AS cpfCliente, "
                    + "CLN_EMAIL AS emailCliente, "
                    + "CLN_TELEFONE AS telefoneCliente, "
                    + "CLN_DATACADASTRO AS dataCadastroCliente "
                    + "FROM tbCliente";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            ArrayList<Cliente> clientes = new ArrayList<Cliente>();
            
            while(resultSet.next()){
                Cliente cliente = new Cliente();
                
                cliente.setCodigoEndereco(resultSet.getInt("codigoEndereco"));
                cliente.setCodigo(resultSet.getInt("codigoCliente"));
                cliente.setNome(resultSet.getString("nomeCliente"));
                cliente.setCPF(resultSet.getString("cpfCliente"));
                cliente.setEmail(resultSet.getString("emailCliente"));
                cliente.setTelefone(resultSet.getString("telefoneCliente"));
                cliente.setDataCadastro(resultSet.getDate("dataCadastroCliente"));

                clientes.add(cliente);
            }
            
            return clientes;         
                  
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


    @Override
    public Cliente buscarPorNome(String nome) {
        try (Connection connection = FabricaConexao.getConexao()) {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT END_CODIGO AS codigoEndereco, CLN_CODIGO AS codigoCliente, CLN_NOME AS nomeCliente, CLN_CPF AS cpfCliente,");
            sql.append(" CLN_EMAIL AS emailCliente, CLN_TELEFONE AS telefoneCliente, CLN_DATACADASTRO AS dataCadastro");
            sql.append(" FROM tbCliente");
            sql.append(" WHERE CLN_NOME = ?");

            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

            preparedStatement.setString(1, nome);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setCodigoEndereco(resultSet.getInt("codigoEndereco"));
                cliente.setCodigo(resultSet.getInt("codigoCliente"));
                cliente.setNome(resultSet.getString("nomeCliente"));
                cliente.setCPF(resultSet.getString("cpfCliente"));
                cliente.setEmail(resultSet.getString("emailCliente"));
                cliente.setTelefone(resultSet.getString("telefoneCliente"));
                cliente.setDataCadastro(resultSet.getDate("dataCadastro"));
            
                return cliente;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Cliente buscarPorCodigo(Integer codigo) {
        try (Connection connection = FabricaConexao.getConexao()) {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT END_CODIGO AS codigoEndereco, CLN_CODIGO AS codigoCliente, CLN_NOME AS nomeCliente, CLN_CPF AS cpfCliente,");
            sql.append(" CLN_EMAIL AS emailCliente, CLN_TELEFONE AS telefoneCliente, CLN_DATACADASTRO AS dataCadastro");
            sql.append(" FROM tbCliente");
            sql.append(" WHERE CLN_CODIGO = ?");

            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

            preparedStatement.setInt(1, codigo);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setCodigoEndereco(resultSet.getInt("codigoEndereco"));
                cliente.setCodigo(resultSet.getInt("codigoCliente"));
                cliente.setNome(resultSet.getString("nomeCliente"));
                cliente.setCPF(resultSet.getString("cpfCliente"));
                cliente.setEmail(resultSet.getString("emailCliente"));
                cliente.setTelefone(resultSet.getString("telefoneCliente"));
                cliente.setDataCadastro(resultSet.getDate("dataCadastro"));
            
                return cliente;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public Cliente buscarPorCPF(String cpf){
        try (Connection connection = FabricaConexao.getConexao()) {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT END_CODIGO AS codigoEndereco, CLN_CODIGO AS codigoCliente, CLN_NOME AS nomeCliente, CLN_CPF AS cpfCliente,");
            sql.append(" CLN_EMAIL AS emailCliente, CLN_TELEFONE AS telefoneCliente, CLN_DATACADASTRO AS dataCadastro");
            sql.append(" FROM tbCliente");
            sql.append(" WHERE CLN_CPF = ?");

            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

            preparedStatement.setString(1, cpf);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setCodigoEndereco(resultSet.getInt("codigoEndereco"));
                cliente.setCodigo(resultSet.getInt("codigoCliente"));
                cliente.setNome(resultSet.getString("nomeCliente"));
                cliente.setCPF(resultSet.getString("cpfCliente"));
                cliente.setEmail(resultSet.getString("emailCliente"));
                cliente.setTelefone(resultSet.getString("telefoneCliente"));
                cliente.setDataCadastro(resultSet.getDate("dataCadastro"));
            
                return cliente;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
