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
import locarfx.DAO.ManutencaoDAO;
import locarfx.DAO.VeiculoDAO;
import locarfx.Helper.Mensagem;
import locarfx.Model.Manutencao;
import locarfx.Model.Veiculo;

/**
 *
 * @author Administrador
 */
public class CadastraManutencaoController implements Initializable{
    
    @FXML
    private Button btnVoltar;
    @FXML
    private ChoiceBox<String> cbVeiculo;
    @FXML
    private TextField txtDescricao;
    @FXML
    private TextField txtKmRodados;
    @FXML
    private TextField txtValorDaManutencao;
    
    private Manutencao novaManutencao;
    VeiculoDAO veiculoDAO = new VeiculoDAO();
    private Date dataAtual = new Date();
    
    public Manutencao getNovaManutencao() {
        return novaManutencao;
    }

    public void setNovaManutencao(Manutencao novaManutencao) {
        this.novaManutencao = novaManutencao;
    }
    
    public void voltarParaTelaAnterior(ActionEvent event) {
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        ArrayList<Veiculo> veiculos = new ArrayList<>();
        
        veiculoDAO.retornarTodos().forEach(object -> {
            veiculos.add((Veiculo) object);
        });
        
        veiculos.forEach(veiculo -> {
            cbVeiculo.getItems().add(veiculo.getNome());
        });
    }
    
    public void cadastrarManutencao(ActionEvent event) {
        try {
            String descricao = txtDescricao.getText();
            Double valorDaManutencao = Double.parseDouble(txtValorDaManutencao.getText());
            Double kmRodados = Double.parseDouble(txtKmRodados.getText());

            Veiculo veiculoEscolhido = veiculoDAO.buscarPorNome(cbVeiculo.getValue());

            setNovaManutencao(new Manutencao(veiculoEscolhido.getCodigo(), dataAtual, descricao, valorDaManutencao, kmRodados));

            ManutencaoDAO manutencaoDAO = new ManutencaoDAO();
            manutencaoDAO.inserir(getNovaManutencao());
            
            txtDescricao.setText("");
            txtKmRodados.setText("");
            txtValorDaManutencao.setText("");
            cbVeiculo.getSelectionModel().clearSelection();

            Mensagem.ExibeMensagemCadastroRealizado();

        } catch (Exception erro) {
            Mensagem.ExibeMensagemCadastroComErro();
        }
    }
    
}
