/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locarfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import locarfx.Model.Marca;
import locarfx.DAO.MarcaDAO;
import locarfx.Helper.Mensagem;

/**
 *
 * @author Administrador
 */
public class CadastraMarcaController {

    @FXML
    private TextField txtMarca;
    @FXML
    private Button btnVoltar;

    private Marca novaMarca;

    /**
     * @return the novaMarca
     */
    public Marca getNovaMarca() {
        return novaMarca;
    }

    /**
     * @param novaMarca the novaMarca to set
     */
    public void setNovaMarca(Marca novaMarca) {
        this.novaMarca = novaMarca;
    }

    public void cadastrarMarca(ActionEvent event) {
        try {
            String nomeDaMarca = txtMarca.getText();

            setNovaMarca(new Marca(nomeDaMarca));

            MarcaDAO marcaDAO = new MarcaDAO();
            marcaDAO.inserir(getNovaMarca());

            Mensagem.ExibeMensagemCadastroRealizado();
            
            txtMarca.setText("");

        } catch (Exception erro) {
            Mensagem.ExibeMensagemCadastroComErro();
        }
    }

    public void voltarParaTelaAnterior(ActionEvent event) {
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        stage.close();
    }

}
