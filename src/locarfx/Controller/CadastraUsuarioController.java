package locarfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import locarfx.Model.Usuario;
import locarfx.DAO.UsuarioDAO;
import locarfx.Helper.Mensagem;
import locarfx.Model.Enums.Cargo;

public class CadastraUsuarioController implements Initializable {

    @FXML
    private ChoiceBox<Cargo> cbCargo;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCPF;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtLogin;
    @FXML
    private TextField txtSenha;
    @FXML
    private Button btnVoltar;

    private Usuario novoUsuario;
    private Stage dialogStage;

    public Usuario getNovoUsuario() {
        return novoUsuario;
    }

    public void setNovoUsuario(Usuario novoUsuario) {
        this.novoUsuario = novoUsuario;
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void voltarParaTelaAnterior(ActionEvent event) {
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbCargo.getItems().setAll(Cargo.values());
    }

    public void cadastrarUsuario(ActionEvent event) {
        try {
            String nome = txtNome.getText();
            String CPF = txtCPF.getText();
            String email = txtEmail.getText();
            Cargo cargo = cbCargo.getValue();
            String login = txtLogin.getText();
            String senha = txtSenha.getText();

            setNovoUsuario(new Usuario(nome, CPF, email, cargo, login, senha));

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.inserir(getNovoUsuario());

            txtNome.setText("");
            txtCPF.setText("");
            txtEmail.setText("");
            txtLogin.setText("");
            txtSenha.setText("");
            cbCargo.getSelectionModel().clearSelection();

            Mensagem.ExibeMensagemCadastroRealizado();

        } catch (Exception erro) {
            Mensagem.ExibeMensagemCadastroComErro();
        }
    }
}
