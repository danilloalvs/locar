package locarfx.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import locarfx.Infra.FabricaConexao;
import locarfx.Model.Marca;

public class MarcaDAO implements IBasicoDAO<Marca> {

    @Override
    public void inserir(Marca marca) {
        try (Connection connection = FabricaConexao.getConexao()) {
            String sql = "INSERT INTO tbMarca (MRC_NOME) VALUES (?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, marca.getNome());

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void alterar(Marca marca) {
        try (Connection connection = FabricaConexao.getConexao()) {
            String sql = "UPDATE tbMarca SET MRC_NOME = ? "
                    + "WHERE MRC_CODIGO = ?";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setString(1, marca.getNome());
            preparedStatement.setInt(2, marca.getCodigo());
                                    
            preparedStatement.executeQuery();
            preparedStatement.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void remover(Marca marca) {
        try (Connection connection = FabricaConexao.getConexao()) {
            String sql = "DELETE FROM tbMarca WHERE MRC_CODIGO = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, marca.getCodigo());
            preparedStatement.executeQuery();
            preparedStatement.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Marca buscarPorNome(String nome) {
        try (Connection connection = FabricaConexao.getConexao()) {
            String sql = "SELECT MRC_CODIGO AS codigoMarca, MRC_NOME AS nomeMarca FROM tbMarca WHERE MRC_NOME = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, nome);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Marca marca = new Marca();
                marca.setCodigo(resultSet.getInt("codigoMarca"));
                marca.setNome(resultSet.getString("nomeMarca"));
                return marca;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Marca buscarPorCodigo(Integer codigo) {
        try (Connection connection = FabricaConexao.getConexao()) {
            String sql = "SELECT MRC_CODIGO AS codigoMarca, MRC_NOME AS nomeMarca FROM tbMarca WHERE MRC_CODIGO = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, codigo);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Marca marca = new Marca();
                marca.setCodigo(resultSet.getInt("codigoMarca"));
                marca.setNome(resultSet.getString("nomeMarca"));
                return marca;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Integer consultaCodigoMarca(String nome) {
        try (Connection connection = FabricaConexao.getConexao()) {
            String sql = "SELECT MRC_CODIGO AS codigoMarca FROM tbMarca WHERE MRC_NOME = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

            preparedStatement.setString(1, nome);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("codigoMarca");
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ArrayList retornarTodos() {
        try (Connection connection = FabricaConexao.getConexao()) {
            String sql = "SELECT MRC_CODIGO AS codigoMarca, MRC_NOME AS nomeMarca FROM tbMarca";

            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Marca> marcas = new ArrayList<>();

            while (resultSet.next()) {
                Marca marca = new Marca();
                marca.setCodigo(Integer.parseInt(resultSet.getString("codigoMarca")));
                marca.setNome(resultSet.getString("nomeMarca"));

                marcas.add(marca);
            }

            return marcas;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
