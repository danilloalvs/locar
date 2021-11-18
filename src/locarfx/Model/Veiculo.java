package locarfx.Model;

import java.util.Date;

public class Veiculo {
    public Integer codigoMarca;
    public Integer codigo;
    public String nome;
    public Date dataCadastro;
    public Integer ano;
    public Double valorCompra;
    public Double percentualPorDia;
    
    public Veiculo() {
    }
    
    public Veiculo(Integer codigoMarca, String nome, Date dataCadastro, Integer ano, Double valorCompra, Double percentualPorDia) {
        this.codigoMarca = codigoMarca;
        this.nome = nome;
        this.dataCadastro = dataCadastro;
        this.ano = ano;
        this.valorCompra = valorCompra;
        this.percentualPorDia = percentualPorDia;
    }

    public Integer getCodigoMarca() {
        return codigoMarca;
    }

    public void setCodigoMarca(Integer codigoMarca) {
        this.codigoMarca = codigoMarca;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(Double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public Double getPercentualPorDia() {
        return percentualPorDia;
    }

    public void setPercentualPorDia(Double percentualPorDia) {
        this.percentualPorDia = percentualPorDia;
    }
    
}
