package locarfx.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import locarfx.Infra.FabricaConexao;
import locarfx.Model.Enums.Cargo;
import locarfx.Model.Usuario;

public class UsuarioDAO implements IBasicoDAO<Usuario> {

    @Override
    public void inserir(Usuario usuario) {

        try (Connection connection = FabricaConexao.getConexao()) {
            String sql = "INSERT INTO tbUsuario (USR_NOME, USR_CPF, USR_EMAIL, USR_CARGO, USR_LOGIN, USR_SENHA) VALUES (?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getCpf());
            preparedStatement.setString(3, usuario.getEmail());
            preparedStatement.setString(4, usuario.getCargo().toString());
            preparedStatement.setString(5, usuario.getLogin());
            preparedStatement.setString(6, usuario.getSenha());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void alterar(Usuario usuario) {

    }

    @Override
    public void remover(Usuario usuario) {
        try (Connection connection = FabricaConexao.getConexao()) {
            String sql = "DELETE FROM tbUsuario WHERE USR_CODIGO = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, usuario.getCodigo());
            preparedStatement.executeQuery();
            preparedStatement.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Usuario buscarPorNome(String login) {
        try (Connection connection = FabricaConexao.getConexao()) {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT USR_CODIGO AS codigoUsuario, USR_NOME AS nomeUsuario, USR_CPF AS cpfUsuario,");
            sql.append(" USR_EMAIL AS emailUsuario, USR_CARGO AS cargoUsuario, USR_LOGIN AS loginUsuario, USR_SENHA AS senhaUsuario");
            sql.append(" FROM tbUsuario");
            sql.append(" WHERE USR_LOGIN = ?");

            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setCodigo(resultSet.getInt("codigoUsuario"));
                usuario.setNome(resultSet.getString("nomeUsuario"));
                usuario.setCpf(resultSet.getString("cpfUsuario"));
                usuario.setEmail(resultSet.getString("emailUsuario"));
                usuario.setCargo(Cargo.getCargo(resultSet.getString("cargoUsuario")));
                usuario.setLogin(resultSet.getString("loginUsuario"));
                usuario.setSenha(resultSet.getString("senhaUsuario"));
                return usuario;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public Usuario buscarPorCodigo(Integer codigo) {
        try (Connection connection = FabricaConexao.getConexao()) {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT USR_CODIGO AS codigoUsuario, USR_NOME AS nomeUsuario, USR_CPF AS cpfUsuario,");
            sql.append(" USR_EMAIL AS emailUsuario, USR_CARGO AS cargoUsuario, USR_LOGIN AS loginUsuario, USR_SENHA AS senhaUsuario");
            sql.append(" FROM tbUsuario");
            sql.append(" WHERE USR_CODIGO = ?");

            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

            preparedStatement.setInt(1, codigo);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setCodigo(resultSet.getInt("codigoUsuario"));
                usuario.setNome(resultSet.getString("nomeUsuario"));
                usuario.setCpf(resultSet.getString("cpfUsuario"));
                usuario.setEmail(resultSet.getString("emailUsuario"));
                usuario.setCargo(Cargo.getCargo(resultSet.getString("cargoUsuario")));
                usuario.setLogin(resultSet.getString("loginUsuario"));
                usuario.setSenha(resultSet.getString("senhaUsuario"));
                return usuario;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ArrayList<Usuario> retornarTodos() {
        try (Connection connection = FabricaConexao.getConexao()) {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT USR_CODIGO AS codigoUsuario, USR_NOME AS nomeUsuario, USR_CPF AS cpfUsuario,");
            sql.append(" USR_EMAIL AS emailUsuario, USR_CARGO AS cargoUsuario, USR_LOGIN AS loginUsuario, USR_SENHA AS senhaUsuario");
            sql.append(" FROM tbUsuario");

            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Usuario> usuarios = new ArrayList<>();

            if (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setCodigo(resultSet.getInt("codigoUsuario"));
                usuario.setNome(resultSet.getString("nomeUsuario"));
                usuario.setCpf(resultSet.getString("cpfUsuario"));
                usuario.setEmail(resultSet.getString("emailUsuario"));
                usuario.setCargo(Cargo.getCargo(resultSet.getString("cargoUsuario")));
                usuario.setLogin(resultSet.getString("loginUsuario"));
                usuario.setSenha(resultSet.getString("senhaUsuario"));

                usuarios.add(usuario);
            }
            resultSet.close();
            preparedStatement.close();

            return usuarios;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean validaLogin(String login, String senha) throws SQLException {

        Connection connection = FabricaConexao.getConexao();

        String sql = "SELECT * FROM tbUsuario WHERE USR_LOGIN = ? AND USR_SENHA = ?";

        PreparedStatement prepStmt = connection.prepareStatement(sql);

        prepStmt.setString(1, login);
        prepStmt.setString(2, senha);

        ResultSet resultset = prepStmt.executeQuery();

        return resultset.next();
    }

}
