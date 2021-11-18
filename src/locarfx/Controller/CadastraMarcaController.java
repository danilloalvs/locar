package locarfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import locarfx.Model.Marca;
import locarfx.DAO.MarcaDAO;
import locarfx.Helper.Mensagem;

public class CadastraMarcaController {

    @FXML
    private TextField txtMarca;
    @FXML
    private Button btnVoltar;

    private Marca novaMarca;

    public Marca getNovaMarca() {
        return novaMarca;
    }

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
