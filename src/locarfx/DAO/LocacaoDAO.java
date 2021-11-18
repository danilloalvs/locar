package locarfx.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import locarfx.Infra.FabricaConexao;
import locarfx.Model.Enums.FormaDePagamento;
import locarfx.Model.Enums.StatusLocacao;
import locarfx.Model.Locacao;

/**
 *
 * @author Gabriel
 */
public class LocacaoDAO implements IBasicoDAO<Locacao> {

    @Override
    public void inserir(Locacao locacao) {
        try (Connection connection = FabricaConexao.getConexao()) {
            String sql = "INSERT INTO tbLocacao (USR_CODIGO, "
                    + "CLN_CODIGO, "
                    + "VCL_CODIGO, "
                    + "LCA_DATAINICIO, "
                    + "LCA_DATAFIM, "
                    + "LCA_QTDDIAS, "
                    + "LCA_VALORTOTAL, "
                    + "LCA_PAGAMENTO, "
                    + "LCA_STATUS) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, locacao.getCodigoUsuario().toString());
            preparedStatement.setString(2, locacao.getCodigoCliente().toString());
            preparedStatement.setString(3, locacao.getCodigoVeiculo().toString());
            java.sql.Date sqlDate = new java.sql.Date(locacao.getDataInicio().getTime());
            preparedStatement.setDate(4, sqlDate);

            java.sql.Date sqlDate2 = new java.sql.Date(locacao.getDataFim().getTime());
            preparedStatement.setDate(5, sqlDate2);

            preparedStatement.setString(6, locacao.getQtdDias().toString());
            preparedStatement.setString(7, locacao.getValorTotal().toString());
            preparedStatement.setString(8, locacao.getFormaDePagamento().toString());
            preparedStatement.setString(9, locacao.getStatusLocacao().toString());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void alterar(Locacao locacao) {
        try (Connection connection = FabricaConexao.getConexao()) {
            String sql = "UPDATE tbLocacao SET "
                    + "LCA_DATAINICIO = ?, "
                    + "LCA_DATAFIM = ?, "
                    + "LCA_QTDDIAS = ?, "
                    + "LCA_VALORTOTAL = ?, "
                    + "LCA_PAGAMENTO = ?, "
                    + "LCA_STATUS = ? "
                    + "WHERE LCA_CODIGO = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            java.sql.Date sqlDate = new java.sql.Date(locacao.getDataInicio().getTime());
            preparedStatement.setDate(1, sqlDate);

            java.sql.Date sqlDate2 = new java.sql.Date(locacao.getDataFim().getTime());
            preparedStatement.setDate(2, sqlDate2);

            preparedStatement.setString(3, locacao.getQtdDias().toString());
            preparedStatement.setString(4, locacao.getValorTotal().toString());
            preparedStatement.setString(5, locacao.getFormaDePagamento().toString());
            preparedStatement.setString(6, locacao.getStatusLocacao().toString());
            preparedStatement.setInt(7, locacao.getCodigo());

            preparedStatement.executeQuery();
            preparedStatement.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void remover(Locacao locacao) {
        try (Connection connection = FabricaConexao.getConexao()) {
            String sql = "DELETE FROM tbLocacao WHERE LCA_CODIGO = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, locacao.getCodigo());
            preparedStatement.executeQuery();
            preparedStatement.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Locacao buscarPorCodigo(Integer codigo) {
        
        try (Connection connection = FabricaConexao.getConexao()) {
            String sql = "SELECT USR_CODIGO AS codigoUsuario, "
                    + "CLN_CODIGO AS codigoCliente, "
                    + "LCA_CODIGO AS codigoLocacao, "
                    + "VCL_CODIGO AS codigoVeiculo, "
                    + "LCA_DATAINICIO AS dataInicio, "
                    + "LCA_DATAFIM AS dataFim, "
                    + "LCA_QTDDIAS AS qtdDias, "
                    + "LCA_VALORTOTAL AS valorTotal, "
                    + "LCA_PAGAMENTO AS formaPagamento, "
                    + "LCA_STATUS AS status "
                    + "FROM tbLocacao "
                    + "WHERE LCA_CODIGO = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, codigo);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Locacao locacao = new Locacao();
                
                locacao.setCodigo(resultSet.getInt("codigoLocacao"));
                locacao.setCodigoUsuario(resultSet.getInt("codigoUsuario"));
                locacao.setCodigoCliente(resultSet.getInt("codigoCliente"));
                locacao.setCodigoVeiculo(resultSet.getInt("codigoVeiculo"));
                locacao.setDataInicio(resultSet.getDate("dataInicio"));
                locacao.setDataFim(resultSet.getDate("dataFim"));
                locacao.setQtdDias(resultSet.getInt("qtdDias"));
                locacao.setValorTotal(resultSet.getDouble("valorTotal"));
                locacao.setFormaDePagamento(FormaDePagamento.getFormaDePagamento(resultSet.getString("formaPagamento")));
                locacao.setStatusLocacao(StatusLocacao.getStatusLocacao(resultSet.getString("status")));
                
                preparedStatement.close();
                
                return locacao;
            }
            else {
                return null; 
            }            

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ArrayList<Locacao> retornarTodos() {
        try (Connection connection = FabricaConexao.getConexao()) {
            String sql = "SELECT USR_CODIGO AS codigoUsuario, "
                    + "CLN_CODIGO AS codigoCliente, "
                    + "LCA_CODIGO AS codigoLocacao, "
                    + "VCL_CODIGO AS codigoVeiculo, "
                    + "LCA_DATAINICIO AS dataInicio, "
                    + "LCA_DATAFIM AS dataFim, "
                    + "LCA_QTDDIAS AS qtdDias, "
                    + "LCA_VALORTOTAL AS valorTotal, "
                    + "LCA_PAGAMENTO AS formaPagamento, "
                    + "LCA_STATUS AS status "
                    + "FROM tbLocacao";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Locacao> lista = new ArrayList<>();

            if (resultSet.next()) {
                Locacao locacao = new Locacao();
                locacao.setCodigo(resultSet.getInt("codigoLocacao"));
                locacao.setCodigoUsuario(resultSet.getInt("codigoUsuario"));
                locacao.setCodigoCliente(resultSet.getInt("codigoCliente"));
                locacao.setCodigoVeiculo(resultSet.getInt("codigoVeiculo"));
                locacao.setDataInicio(resultSet.getDate("dataInicio"));
                locacao.setDataFim(resultSet.getDate("dataFim"));
                locacao.setQtdDias(resultSet.getInt("qtdDias"));
                locacao.setValorTotal(resultSet.getDouble("valorTotal"));
                locacao.setFormaDePagamento(FormaDePagamento.getFormaDePagamento(resultSet.getString("formaPagamento")));
                locacao.setStatusLocacao(StatusLocacao.getStatusLocacao(resultSet.getString("status")));

                lista.add(locacao);
            }
            
            preparedStatement.close();

            return lista;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public Locacao buscarPorNome(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
