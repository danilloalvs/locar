package locarfx.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import locarfx.DAO.MarcaDAO;
import locarfx.DAO.VeiculoDAO;
import locarfx.Helper.Mensagem;
import locarfx.Model.Marca;
import locarfx.Model.Veiculo;

public class CadastraVeiculoController implements Initializable {

    @FXML
    private Button btnVoltar;
    @FXML
    private ChoiceBox<String> cbMarca;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtAno;
    @FXML
    private TextField txtValorDeCompra;
    @FXML
    private TextField txtPercentualLocacao;

    private Veiculo novoVeiculo;
    private Date dataAtual = new Date();

    VeiculoDAO veiculoDAO = new VeiculoDAO();
    MarcaDAO marcaDAO = new MarcaDAO();

    public Veiculo getNovoVeiculo() {
        return novoVeiculo;
    }

    public void setNovoVeiculo(Veiculo novoVeiculo) {
        this.novoVeiculo = novoVeiculo;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        ArrayList<Marca> marcas = new ArrayList<>();
        
        marcaDAO.retornarTodos().forEach(object -> {
            marcas.add((Marca) object);
        });
        
        marcas.forEach(marca -> {
            cbMarca.getItems().add(marca.getNome());
        });
    }

    public void voltarParaTelaAnterior(ActionEvent event) {
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        stage.close();
    }

    public void cadastrarVeiculo(ActionEvent event) {
        try {
            String nome = txtNome.getText();
            Integer ano = Integer.parseInt(txtAno.getText());
            Double valorDeCompra = Double.parseDouble(txtValorDeCompra.getText());
            Double percentualLocacao = Double.parseDouble(txtPercentualLocacao.getText());

            Marca marcaEscolhida = marcaDAO.buscarPorNome(cbMarca.getValue());

            setNovoVeiculo(new Veiculo(marcaEscolhida.getCodigo(), nome, dataAtual, ano, valorDeCompra, percentualLocacao));

            VeiculoDAO veiculoDAO = new VeiculoDAO();
            veiculoDAO.inserir(getNovoVeiculo());
            
            txtNome.setText("");
            txtAno.setText("");
            txtValorDeCompra.setText("");
            txtPercentualLocacao.setText("");
            cbMarca.getSelectionModel().clearSelection();

            Mensagem.ExibeMensagemCadastroRealizado();

        } catch (Exception erro) {
            Mensagem.ExibeMensagemCadastroComErro();
        }
    }

}
