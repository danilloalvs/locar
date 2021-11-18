package locarfx.Controller;

import java.io.File;
import locarfx.DAO.UsuarioDAO;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import locarfx.Helper.UsuarioDaSessao;
import locarfx.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    @FXML
    private TextField txtLogin;
    @FXML
    private TextField txtSenha;
    @FXML
    private Button btnEntrar;
    @FXML
    private ImageView imageView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("src/locarfx/View/Resources/image-removebg-preview.png");
        Image image = new Image(file.toURI().toString());
        imageView.setFitHeight(158.0);
        imageView.setFitWidth(309.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(image);
    }

    public void voltarParaTelaAnterior(ActionEvent event) {
        Stage stageAtual = (Stage) btnEntrar.getScene().getWindow();
        stageAtual.close();
    }

    public void fazLogin(ActionEvent event) throws IOException {

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        try {
            if (!usuarioDAO.validaLogin(txtLogin.getText(), txtSenha.getText())){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Login");
                alert.setHeaderText("Erro no login");
                alert.setContentText("Usuário inexiste ou senha incorreta.");
                alert.showAndWait().ifPresent(rs -> {
                    if (rs == ButtonType.OK) {
                        alert.close();
                    }
                });
            }

            if (usuarioDAO.buscarPorNome(txtLogin.getText()) != null) {

                UsuarioDaSessao.setUsuarioDaSessao(usuarioDAO.buscarPorNome(txtLogin.getText()));

                Parent root = FXMLLoader.load(Main.class.getResource("/locarfx/View/frTelaPrincipal.fxml"));

                Scene scene = new Scene(root, 1170, 680);

                Stage stage = new Stage();
                stage.setResizable(false);
                stage.setTitle("Tela principal");
                stage.setScene(scene);
                stage.show();

                Stage stageAtual = (Stage) btnEntrar.getScene().getWindow();
                stageAtual.close();

                txtLogin.clear();
                txtSenha.clear();
            }

        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Login");
            alert.setHeaderText("Erro no login");
            alert.setContentText("Provavelmente o usuário não foi encontrado com esse login, o usuário inexiste ou a senha está errada.");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    alert.close();
                }
            });
        }
    }
}

