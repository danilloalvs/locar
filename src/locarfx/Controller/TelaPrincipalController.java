package locarfx.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import locarfx.Helper.UsuarioDaSessao;
import locarfx.Main;
import locarfx.Model.Enums.Cargo;

public class TelaPrincipalController implements Initializable {

    @FXML
    public BorderPane bpPrincipal;
    @FXML
    public MenuButton mbCadastros;
    @FXML
    public MenuItem mitemCadUsuario;
    @FXML
    public MenuItem mitemCadMarca;
    @FXML
    public MenuItem mitemCadVeiculo;
    @FXML
    public MenuItem mitemCadCliente;
    @FXML
    public MenuItem mitemCadManutencao;
    @FXML
    public Label lbLogo;
    @FXML
    public Button btnSobre;
    @FXML
    public MenuButton mbConsultas;
    @FXML
    public MenuItem mitemConUsuario;
    @FXML
    public MenuItem mitemConMarca;
    @FXML
    public MenuItem mitemConVeiculo;
    @FXML
    public MenuItem mitemConCliente;
    @FXML
    public MenuItem mitemConManutencao;
    @FXML
    public Button btnLogoff;
    @FXML
    public Button btnLocacoes;
    @FXML
    public Button btnRecebimento;
    @FXML
    public MenuButton mbGastos;
    @FXML
    public MenuItem mitemGastosCompra;
    @FXML
    public MenuItem mitemGastosManut;
    @FXML
    public Label lblUltimasLocacoes;
    @FXML
    public TableView tbviewLocacoes;
    @FXML
    public TableColumn tbcolVendedor;
    @FXML
    public TableColumn tbcolCliente;
    @FXML
    public TableColumn tbcolDataLocacao;
    @FXML
    public TableColumn tbcolDataDevolucao;
    @FXML
    public TableColumn tbcolValorTotal;
    @FXML
    public TableColumn tbcolPagamento;
    @FXML
    public TableColumn tbcolStatus;
    @FXML
    public Button btnNovaLocacao;
    @FXML
    private Button btnVoltar;
    @FXML
    private Label lblUsuarioAtivo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblUsuarioAtivo.setText("Bem-vindo, " + UsuarioDaSessao.instancia.getNomeUsuarioDaSessao());
        
        if(UsuarioDaSessao.instancia.getCargoUsuarioDaSessao() == Cargo.VENDEDOR){
            mitemCadUsuario.setDisable(true);
            mitemCadMarca.setDisable(true);
            mitemCadVeiculo.setDisable(true);
            mitemCadManutencao.setDisable(true);
            mitemConUsuario.setDisable(true);
            mbGastos.setDisable(true);
            btnRecebimento.setDisable(true);
        }
        
        
    }

    public void abreCadastroUsuario(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("/locarfx/View/frCadastraUsuario.fxml"));

        Scene scene = new Scene(root, 1000, 680);

        Stage stage = new Stage();
        stage.setTitle("Cadastrar usuário");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void abreCadastroCliente(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("/locarfx/View/frCadastraCliente.fxml"));
        
        Scene scene = new Scene(root, 1000, 680);

        Stage stage = new Stage();
        stage.setTitle("Cadastrar cliente");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
    
    public void abreCadastroMarca(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("/locarfx/View/frCadastraMarca.fxml"));
        
        Scene scene = new Scene(root, 800, 600);

        Stage stage = new Stage();
        stage.setTitle("Cadastrar marca");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
    
    public void abreCadastroVeiculo(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("/locarfx/View/frCadastraVeiculo.fxml"));
        
        Scene scene = new Scene(root, 1000, 680);

        Stage stage = new Stage();
        stage.setTitle("Cadastrar veículo");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
    
    public void abreCadastroManutencao(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("/locarfx/View/frCadastraManutencao.fxml"));
        
        Scene scene = new Scene(root, 1000, 680);

        Stage stage = new Stage();
        stage.setTitle("Cadastrar manutenção");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
    
    public void abreCadastroLocacao(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("/locarfx/View/frCadastraLocacao.fxml"));
        
        Scene scene = new Scene(root, 1000, 680);

        Stage stage = new Stage();
        stage.setTitle("Cadastrar locação");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void voltarParaTelaAnterior(ActionEvent event) throws IOException {
        Stage stageAtual = (Stage) btnVoltar.getScene().getWindow();
        stageAtual.close();

        Parent root = FXMLLoader.load(Main.class.getResource("/locarfx/View/frLogin.fxml"));

        Scene scene = new Scene(root, 800, 600);

        Stage stageNovo = new Stage();
        stageNovo.setResizable(false);
        stageNovo.setTitle("Login");
        stageNovo.setScene(scene);
        stageNovo.show();

        UsuarioDaSessao.instancia.limparUsuarioDaSessao();
    }
}
