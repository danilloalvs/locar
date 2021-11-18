package locarfx.Model;

import java.util.Date;
import locarfx.Model.Enums.FormaDePagamento;
import locarfx.Model.Enums.StatusLocacao;

/**
 *
 * @author Administrador
 */
public class Locacao {
    
    private Integer codigoUsuario;
    private Integer codigoCliente;
    private Integer codigoVeiculo;
    private Integer codigo;
    private Date dataInicio;
    private Date dataFim;
    private Integer qtdDias;
    private Double valorTotal;
    private FormaDePagamento formaDePagamento;
    private StatusLocacao statusLocacao;
    
    public Locacao(){
        
    }
    
    public Locacao(Integer codigoUsuario, Integer codigoCliente, Integer codigoVeiculo, Date dataInicio, Date dataFim, Integer qtdDias, Double valorTotal, FormaDePagamento formaDePagamento, StatusLocacao statusLocacao) {
        this.codigoUsuario = codigoUsuario;
        this.codigoCliente = codigoCliente;
        this.codigoVeiculo = codigoVeiculo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.qtdDias = qtdDias;
        this.valorTotal = valorTotal;
        this.formaDePagamento = formaDePagamento;
        this.statusLocacao = statusLocacao;
    }

    public Integer getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Integer codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }
    
    public Integer getCodigoVeiculo() {
        return codigoVeiculo;
    }

    public void setCodigoVeiculo(Integer codigoVeiculo) {
        this.codigoVeiculo = codigoVeiculo;
    }

    public Integer getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Integer codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Integer getQtdDias() {
        return qtdDias;
    }

    public void setQtdDias(Integer qtdDias) {
        this.qtdDias = qtdDias;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public FormaDePagamento getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public StatusLocacao getStatusLocacao() {
        return statusLocacao;
    }

    public void setStatusLocacao(StatusLocacao statusLocacao) {
        this.statusLocacao = statusLocacao;
    }

    
       
}
