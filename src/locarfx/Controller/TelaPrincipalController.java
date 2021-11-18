package locarfx.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import locarfx.Helper.UsuarioDaSessao;
import locarfx.Infra.FabricaConexao;
import locarfx.Main;
import locarfx.Model.Enums.Cargo;
import java.sql.Connection;
import java.sql.ResultSet;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;

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
    private ObservableList<ObservableList> data;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populaTabela();
        lblUsuarioAtivo.setText("Bem-vindo, " + UsuarioDaSessao.instancia.getNomeUsuarioDaSessao());

        if (UsuarioDaSessao.instancia.getCargoUsuarioDaSessao() == Cargo.VENDEDOR) {
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

    public void populaTabela() {
        //https://www.ti-enxame.com/pt/java/como-preencher-um-tableview-com-dados-de-banco-de-dados/1042073033/
        data = FXCollections.observableArrayList();

        try {
            Connection connection = FabricaConexao.getConexao();
            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "SELECT "
                    + "USR_NOME AS Usuario, "
                    + "CLN_NOME AS Cliente, "
                    + "VCL_NOME AS Veiculo, "
                    + "LCA_DATAINICIO AS DataInicio, "
                    + "LCA_DATAFIM AS DataFim, "
                    + "LCA_QTDDIAS AS QuantidadeDias, "
                    + "LCA_VALORTOTAL AS ValorTotal, "
                    + "LCA_PAGAMENTO AS Pagamento, "
                    + "LCA_STATUS AS Status "
                    + "FROM tbLocacao AS locacao "
                    + "INNER JOIN tbCliente AS cliente "
                    + "ON locacao.USR_CODIGO = cliente.CLN_CODIGO "
                    + "INNER JOIN tbVeiculo AS veiculo "
                    + "ON locacao.VCL_CODIGO = veiculo.VCL_CODIGO "
                    + "INNER JOIN tbUsuario AS usuario "
                    + "ON locacao.USR_CODIGO = usuario.USR_CODIGO";
            //ResultSet
            ResultSet resultSet = connection.createStatement().executeQuery(SQL);

            /**
             * ********************************
             * TABLE COLUMN ADDED DYNAMICALLY * ********************************
             */
            for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(resultSet.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tbviewLocacoes.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }

            /**
             * ******************************
             * Data added to ObservableList * ******************************
             */
            while (resultSet.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(resultSet.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            tbviewLocacoes.setItems(data);
        } catch (Exception e) {

        }
    }
}
