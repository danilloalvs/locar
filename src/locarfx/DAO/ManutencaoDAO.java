package locarfx.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import locarfx.Infra.FabricaConexao;
import locarfx.Model.Manutencao;

/**
 *
 * @author Administrador
 */
public class ManutencaoDAO implements IBasicoDAO<Manutencao> {

    @Override
    public void inserir(Manutencao manutencao) {
        try (Connection connection = FabricaConexao.getConexao()) {
            String sql = "INSERT INTO tbManutencao (VCL_CODIGO, MTN_DATACADASTRO, MTN_DESCRICAO, MTN_VALOR, MTN_KMRODADOS) "
                    + "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, (manutencao.getCodigoVeiculo().toString()));
            java.sql.Date sqlDate = new java.sql.Date(manutencao.getDataManutencao().getTime());
            preparedStatement.setDate(2, sqlDate);
            preparedStatement.setString(3, (manutencao.getDescricaoManutencao()));
            preparedStatement.setString(4, (manutencao.getValorManutencao().toString()));
            preparedStatement.setString(5, (manutencao.getKmRodados().toString()));

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void alterar(Manutencao manutencao) {
        try (Connection connection = FabricaConexao.getConexao()) {
            String sql = "UPDATE tbManutencao SET MTN_DATACADASTRO = ?, "
                    + "MTN_DESCRICAO = ?, "
                    + "MTN_VALOR = ?, "
                    + "MTN_KMRODADOS = ? "
                    + "WHERE MTN_CODIGO = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            java.sql.Date sqlDate = new java.sql.Date(manutencao.getDataManutencao().getTime());
            preparedStatement.setDate(1, sqlDate);
            preparedStatement.setString(2, manutencao.getDescricaoManutencao());
            preparedStatement.setDouble(3, manutencao.getValorManutencao());
            preparedStatement.setDouble(4, manutencao.getKmRodados());
            preparedStatement.setInt(5, manutencao.getCodigo());

            preparedStatement.executeQuery();
            preparedStatement.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void remover(Manutencao manutencao) {
        try (Connection connection = FabricaConexao.getConexao()) {
            String sql = "DELETE FROM tbManutencao WHERE MTN_CODIGO = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, manutencao.getCodigo());
            preparedStatement.executeQuery();
            preparedStatement.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Manutencao buscarPorNome(String nome) {
        try (Connection connection = FabricaConexao.getConexao()) {
            String sql;
            sql = "SELECT VCL_CODIGO AS codigoVeiculo, "
                    + "MTN_CODIGO AS codigoManutencao, "
                    + "MTN_DATACADASTRO AS dataManutencao, "
                    + "MTN_DESCRICAO AS descricaoManutencao, "
                    + "MTN_VALOR AS valorManutencao, "
                    + "MTN_KMRODADOS AS kmRodadosManutencao "
                    + "FROM tbManutencao "
                    + "WHERE MTN_DESCRICAO = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

            preparedStatement.setString(1, nome);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Manutencao manutencao = new Manutencao();

                manutencao.setCodigoVeiculo(resultSet.getInt("codigoVeiculo"));
                manutencao.setCodigo(resultSet.getInt("codigoManutencao"));
                manutencao.setDataManutencao(resultSet.getDate("dataManutencao"));
                manutencao.setDescricaoManutencao(resultSet.getString("descricaoManutencao"));
                manutencao.setValorManutencao(resultSet.getDouble("valorManutencao"));
                manutencao.setKmRodados(resultSet.getDouble("kmRodadosManutencao"));

                return manutencao;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Manutencao buscarPorCodigo(Integer codigo) {
        try (Connection connection = FabricaConexao.getConexao()) {
            String sql;
            sql = "SELECT VCL_CODIGO AS codigoVeiculo, "
                    + "MTN_CODIGO AS codigoManutencao, "
                    + "MTN_DATACADASTRO AS dataManutencao, "
                    + "MTN_DESCRICAO AS descricaoManutencao, "
                    + "MTN_VALOR AS valorManutencao, "
                    + "MTN_KMRODADOS AS kmRodadosManutencao "
                    + "FROM tbManutencao "
                    + "WHERE MTN_CODIGO = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

            preparedStatement.setInt(1, codigo);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Manutencao manutencao = new Manutencao();

                manutencao.setCodigoVeiculo(resultSet.getInt("codigoVeiculo"));
                manutencao.setCodigo(resultSet.getInt("codigoManutencao"));
                manutencao.setDataManutencao(resultSet.getDate("dataManutencao"));
                manutencao.setDescricaoManutencao(resultSet.getString("descricaoManutencao"));
                manutencao.setValorManutencao(resultSet.getDouble("valorManutencao"));
                manutencao.setKmRodados(resultSet.getDouble("kmRodadosManutencao"));

                return manutencao;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ArrayList<Manutencao> retornarTodos() {
        try (Connection connection = FabricaConexao.getConexao()) {
            String sql = "SELECT VCL_CODIGO AS codigoVeiculo, "
                    + "MTN_CODIGO AS codigoManutencao, "
                    + "MTN_DATACADASTRO AS dataManutencao, "
                    + "MTN_DESCRICAO AS descricaoManutencao, "
                    + "MTN_VALOR AS valorManutencao, "
                    + "MTN_KMRODADOS AS kmRodadosManutencao "
                    + "FROM tbManutencao";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Manutencao> manutencoes = new ArrayList<>();

            if (resultSet.next()) {
                Manutencao manutencao = new Manutencao();

                manutencao.setCodigoVeiculo(resultSet.getInt("codigoVeiculo"));
                manutencao.setCodigo(resultSet.getInt("codigoManutencao"));
                manutencao.setDataManutencao(resultSet.getDate("dataManutencao"));
                manutencao.setDescricaoManutencao(resultSet.getString("descricaoManutencao"));
                manutencao.setValorManutencao(resultSet.getDouble("valorManutencao"));
                manutencao.setKmRodados(resultSet.getDouble("kmRodadosManutencao"));

                manutencoes.add(manutencao);
            }

            preparedStatement.close();

            return manutencoes;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
