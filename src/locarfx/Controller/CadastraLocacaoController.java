package locarfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import locarfx.DAO.ClienteDAO;
import locarfx.DAO.LocacaoDAO;
import locarfx.DAO.VeiculoDAO;
import locarfx.Helper.Mensagem;
import locarfx.Helper.UsuarioDaSessao;
import locarfx.Model.Cliente;
import locarfx.Model.Enums.FormaDePagamento;
import locarfx.Model.Enums.StatusLocacao;
import locarfx.Model.Locacao;
import locarfx.Model.Veiculo;
import java.time.LocalDate;

public class CadastraLocacaoController implements Initializable {

    @FXML
    private Button btnVoltar;
    @FXML
    private DatePicker dpInicio;
    @FXML
    private DatePicker dpFim;
    @FXML
    private TextField txtValorTotal;
    @FXML
    private TextField txtNomeCliente;
    @FXML
    private TextField txtEmailCliente;
    @FXML
    private TextField txtTelefoneCliente;
    @FXML
    private TextField txtCPF;
    @FXML
    private TextField txtQtdDias;
    @FXML
    private ChoiceBox<FormaDePagamento> cbFormaPagamento;
    @FXML
    private ChoiceBox<StatusLocacao> cbStatus;
    @FXML
    private ChoiceBox<String> cbVeiculos;

    ClienteDAO clienteDAO = new ClienteDAO();
    LocacaoDAO locacaoDAO = new LocacaoDAO();
    VeiculoDAO veiculoDAO = new VeiculoDAO();
    Double valorTotalDaLocacao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbFormaPagamento.getItems().setAll(FormaDePagamento.values());
        cbStatus.getItems().setAll(StatusLocacao.values());

        ArrayList<Veiculo> veiculos = new ArrayList<>();

        veiculoDAO.retornarTodos().forEach(object -> {
            veiculos.add((Veiculo) object);
        });

        veiculos.forEach(veiculo -> {
            cbVeiculos.getItems().add(veiculo.getNome());
        });
    }

    public void buscarCliente(ActionEvent event) {
        Cliente cliente = new Cliente();

        if (clienteDAO.buscarPorCPF(txtCPF.getText()) != null) {
            cliente = clienteDAO.buscarPorCPF(txtCPF.getText());
            Mensagem.ExibeMensagemUsuarioEncontrado();
            txtNomeCliente.setText(cliente.getNome());
            txtEmailCliente.setText(cliente.getTelefone());
            txtTelefoneCliente.setText(cliente.getTelefone());
        } else {
            Mensagem.ExibeMensagemUsuarioNaoEncontrado();
        }
    }

    public void cadastrarLocacao(ActionEvent event) throws ParseException {
        try {
            Veiculo veiculoEscolhido = veiculoDAO.buscarPorNome(cbVeiculos.getValue());

            Cliente cliente = clienteDAO.buscarPorCPF(txtCPF.getText());

            LocalDate myDate = dpInicio.getValue();
            // Crio uma string para formatar a data para ano-mês-dia para se encaixar no banco de dados
            String myFormattedDate = myDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            // https://www.alura.com.br/artigos/como-converter-string-para-date-em-java?gclid=CjwKCAjwz5iMBhAEEiwAMEAwGEyBDw43ZOPadHTp-9wAm9XPvmdIdmKL4cTnyhNTzAOsIOEsdUss0xoC5lEQAvD_BwE
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            var data = sdf.parse(myFormattedDate);

            LocalDate myDate2 = dpFim.getValue();
            // Crio uma string para formatar a data para ano-mês-dia para se encaixar no banco de dados
            String myFormattedDate2 = myDate2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            // https://www.alura.com.br/artigos/como-converter-string-para-date-em-java?gclid=CjwKCAjwz5iMBhAEEiwAMEAwGEyBDw43ZOPadHTp-9wAm9XPvmdIdmKL4cTnyhNTzAOsIOEsdUss0xoC5lEQAvD_BwE
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            var data2 = sdf2.parse(myFormattedDate2);

            Locacao locacao = new Locacao();
            locacao.setCodigoUsuario(UsuarioDaSessao.instancia.getCodigoUsuarioDaSessao());
            locacao.setCodigoCliente(cliente.getCodigo());
            locacao.setCodigoVeiculo(veiculoEscolhido.getCodigo());
            locacao.setDataInicio(data);
            locacao.setDataFim(data2);
            locacao.setQtdDias(Integer.parseInt(txtQtdDias.getText()));
            locacao.setValorTotal(Double.parseDouble(txtValorTotal.getText()));
            locacao.setFormaDePagamento((FormaDePagamento) cbFormaPagamento.getValue());
            locacao.setStatusLocacao((StatusLocacao) cbStatus.getValue());

            locacaoDAO.inserir(locacao);

            dpInicio.getEditor().clear();
            dpFim.getEditor().clear();
            txtValorTotal.setText("");
            txtNomeCliente.setText("");
            txtCPF.setText("");
            txtTelefoneCliente.setText("");
            txtEmailCliente.setText("");
            txtQtdDias.setText("");
            cbVeiculos.getSelectionModel().clearSelection();
            cbStatus.getSelectionModel().clearSelection();
            cbFormaPagamento.getSelectionModel().clearSelection();

            Mensagem.ExibeMensagemCadastroRealizado();

        } catch (Exception erro) {
            Mensagem.ExibeMensagemCadastroComErro();
        }
    }

    public void calcularValorTotal(ActionEvent event) {
        try {
            Veiculo veiculoEscolhido = veiculoDAO.buscarPorNome(cbVeiculos.getValue());

            Integer quantidadeDias = Integer.parseInt(txtQtdDias.getText());

            valorTotalDaLocacao = quantidadeDias * (veiculoEscolhido.getPercentualPorDia() * veiculoEscolhido.getValorCompra());

            txtValorTotal.setText(valorTotalDaLocacao.toString());

        } catch (Exception erro) {
            Mensagem.ExibeMensagemNaoFoiPossivelCalcular();
        }
    }  

    public void voltarParaTelaAnterior(ActionEvent event) {
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        stage.close();
    }
}
