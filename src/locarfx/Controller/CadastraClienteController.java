package locarfx.Controller;

import java.io.IOException;
import java.util.Date;
import locarfx.DAO.ClienteDAO;
import locarfx.Model.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import locarfx.Helper.Mensagem;
import locarfx.Helper.ViaCEP.ViaCEP;
import locarfx.Helper.ViaCEP.ViaCEPEvents;
import locarfx.Helper.ViaCEP.ViaCEPException;

public class CadastraClienteController implements Initializable, ViaCEPEvents {

    @FXML
    private TextField txtCEP;
    @FXML
    private TextField txtRua;
    @FXML
    private TextField txtBairro;
    @FXML
    private TextField txtNumero;
    @FXML
    private TextField txtComplemento;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCPF;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTelefone;
    @FXML
    private Button btnVoltar;

    String cep;
    private Date dataAtual = new Date();
    private Cliente novoCliente;

    ViaCEP viaCEP = new ViaCEP(this);

    public Cliente getNovoCliente() {
        return novoCliente;
    }

    public void setNovoCliente(Cliente novoCliente) {
        this.novoCliente = novoCliente;
    }

    public void voltarParaTelaAnterior(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void buscarCEP(ActionEvent event) {
        cep = txtCEP.getText();
        try {
            viaCEP.buscar(cep);
        } catch (ViaCEPException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Falha ao cadastrar cliente");
            alert.setHeaderText("O CEP está errado!");
            alert.setContentText("Verifique se o campo do CEP está preenchido corretamente!");
        }
    }

    public void cadastrarCliente(ActionEvent event) {
        try {
            String rua = txtRua.getText();
            String bairro = txtBairro.getText();
            Integer numero = Integer.parseInt(txtNumero.getText());
            String complemento = txtComplemento.getText();

            EnderecoController enderecoController = new EnderecoController(cep, rua, bairro, numero, complemento);

            String nome = txtNome.getText();
            String CPF = txtCPF.getText();
            String email = txtEmail.getText();
            String telefone = txtTelefone.getText();

            Integer codigoEndereco = enderecoController.obtemCodigoEndereco(cep, rua, bairro, numero, complemento);

            setNovoCliente(new Cliente(codigoEndereco, nome, CPF, telefone, email, dataAtual));

            ClienteDAO clienteDAO = new ClienteDAO();
            clienteDAO.inserir(getNovoCliente());

            Mensagem.ExibeMensagemCadastroRealizado();
            
            txtCEP.setText("");
            txtRua.setText("");
            txtBairro.setText("");
            txtNumero.setText("");
            txtComplemento.setText("");
            txtNome.setText("");
            txtCPF.setText("");
            txtEmail.setText("");
            txtTelefone.setText("");
            
        } catch (NumberFormatException erro) {
            Mensagem.ExibeMensagemCadastroComErro();
        }
    }

    @Override
    public void onCEPSuccess(ViaCEP cep) {
        txtRua.setText(cep.getLogradouro());
        txtBairro.setText(cep.getBairro());
    }

    @Override
    public void onCEPError(String cep) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("CEP não encontrado!");
        alert.setHeaderText("Algo deu errado!");
        alert.setContentText("Verifique se o CEP está preenchido corretamente!");
    }
}
