package locarfx.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import locarfx.Infra.FabricaConexao;
import locarfx.Model.Veiculo;

public class VeiculoDAO implements IBasicoDAO<Veiculo> {

    @Override
    public void inserir(Veiculo veiculo) {
         try (Connection connection = FabricaConexao.getConexao()) {
            String sql = "INSERT INTO tbVeiculo (MRC_CODIGO, VCL_NOME, VCL_DATACADASTRO, VCL_ANO, VCL_VALORCOMPRA, VCL_PERCENTLOCACAO) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, (veiculo.getCodigoMarca()).toString());
            preparedStatement.setString(2, veiculo.getNome());        
            java.sql.Date sqlDate = new java.sql.Date(veiculo.getDataCadastro().getTime());
            preparedStatement.setDate(3, sqlDate);
            preparedStatement.setString(4, (veiculo.getAno()).toString());
            preparedStatement.setString(5, (veiculo.getValorCompra()).toString());
            preparedStatement.setString(6, (veiculo.getPercentualPorDia()).toString());
            
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public int buscaMarca(String nome){
        int codigoMarca = 0;
         try (Connection connection = FabricaConexao.getConexao()) {
            String sql ="SELECT MRC_CODIGO FROM tbMarca WHERE MRC_NOME = ?";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nome);
            ResultSet resultSet = preparedStatement.executeQuery();
           
                if (resultSet.next()){
                codigoMarca = resultSet.getInt("MRC_CODIGO");
            }
            preparedStatement.close();           
           
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
         return codigoMarca;
    }

    @Override
    public void alterar(Veiculo veiculo) {
         try (Connection connection = FabricaConexao.getConexao()) {
            String sql = "UPDATE tbVeiculo SET MRC_CODIGO = ?, VCL_NOME = ?, VCL_CADASTRO = ?, VCL_ANO = ?, VCL_VALORCOMPRA = ?, VCL_PERCENTLOCACAO = ? WHERE VCL_CODIGO = ?";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setInt(1, veiculo.getCodigoMarca());
            preparedStatement.setString(2, veiculo.getNome());
            
            java.sql.Date sqlDate = new java.sql.Date(veiculo.getDataCadastro().getTime());
                       
            preparedStatement.setDate(3, sqlDate);
            preparedStatement.setInt(4, veiculo.getAno());
            preparedStatement.setDouble(5, veiculo.getValorCompra());
            preparedStatement.setDouble(6, veiculo.getPercentualPorDia());
          
            preparedStatement.setInt(7, veiculo.getCodigo());         
                                    
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void remover(Veiculo veiculo) {
        try (Connection connection = FabricaConexao.getConexao()) {
            String sql = "DELETE FROM tbVeiculo WHERE VCL_CODIGO = ?";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, veiculo.getCodigo());
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Veiculo buscarPorCodigo(Integer id) {
        Veiculo veiculo = new Veiculo();
         try (Connection connection = FabricaConexao.getConexao()) {
            String sql ="SELECT MRC_CODIGO, VCL_CODIGO, VCL_NOME, VCL_CADASTRO, VCL_ANO, VCL_VALORCOMPRA, VCL_PERCENTLOCACAO FROM tbVeiculo WHERE VCL_CODIGO = ?";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, veiculo.getCodigo());
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()){
                veiculo.setCodigo(resultSet.getInt("VCL_CODIGO"));
                veiculo.setCodigoMarca((resultSet.getInt("MRC_CODIGO")));
                veiculo.setNome(resultSet.getString("VCL_NOME"));
                veiculo.setAno(resultSet.getInt("VCL_ANO"));
                veiculo.setDataCadastro(resultSet.getDate("VCL_CADASTRO"));
                veiculo.setValorCompra(resultSet.getDouble("VCL_VALORCOMPRA"));
                veiculo.setPercentualPorDia(resultSet.getDouble("VCL_PERCENTLOCACAO"));
            }
            preparedStatement.close();
           
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
       return veiculo;
    }

    @Override
    public ArrayList<Veiculo> retornarTodos() {
        try (Connection connection = FabricaConexao.getConexao()) {
            String sql ="SELECT MRC_CODIGO AS codigoMarca, "
                    + "VCL_CODIGO AS codigoVeiculo, "
                    + "VCL_NOME AS nomeVeiculo, "
                    + "VCL_DATACADASTRO AS dataCadastroVeiculo, "
                    + "VCL_ANO AS anoVeiculo, "
                    + "VCL_VALORCOMPRA AS valorCompraVeiculo, "
                    + "VCL_PERCENTLOCACAO AS percentualLocacaoVeiculo "
                    + "FROM tbVeiculo";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();
            
            while(resultSet.next()){
                Veiculo veiculo = new Veiculo();
                
                veiculo.setCodigo(resultSet.getInt("codigoVeiculo"));
                veiculo.setCodigoMarca((resultSet.getInt("codigoMarca")));
                veiculo.setNome(resultSet.getString("nomeVeiculo"));
                veiculo.setAno(resultSet.getInt("anoVeiculo"));
                veiculo.setDataCadastro(resultSet.getDate("dataCadastroVeiculo"));
                veiculo.setValorCompra(resultSet.getDouble("valorCompraVeiculo"));
                veiculo.setPercentualPorDia(resultSet.getDouble("percentualLocacaoVeiculo"));
                
                veiculos.add(veiculo);
            }
            
            return veiculos;         
                  
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
    }

    @Override
    public Veiculo buscarPorNome(String nome) {
        try (Connection connection = FabricaConexao.getConexao()) {
            String sql = "SELECT MRC_CODIGO AS codigoMarca, VCL_CODIGO AS codigoVeiculo, VCL_NOME AS nomeVeiculo, "
                    + "VCL_DATACADASTRO AS dataCadastroVeiculo, VCL_ANO AS anoVeiculo, VCL_VALORCOMPRA AS valorCompraVeiculo, "
                    + "VCL_PERCENTLOCACAO AS percentualLocacaoVeiculo FROM tbVeiculo WHERE VCL_NOME = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, nome);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setCodigoMarca(resultSet.getInt("codigoMarca"));
                veiculo.setCodigo(resultSet.getInt("codigoVeiculo"));
                veiculo.setNome(resultSet.getString("nomeVeiculo"));
                veiculo.setDataCadastro(resultSet.getDate("dataCadastroVeiculo"));
                veiculo.setAno(resultSet.getInt("anoVeiculo"));
                veiculo.setValorCompra(resultSet.getDouble("valorCompraVeiculo"));
                veiculo.setPercentualPorDia(resultSet.getDouble("percentualLocacaoVeiculo"));

                return veiculo;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
