/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locarfx.Model;

import java.util.Date;

/**
 *
 * @author Administrador
 */
public class Manutencao {
    
    private Integer codigoVeiculo;
    private Integer codigo;
    private Date dataManutencao;
    private String descricaoManutencao;
    private Double valorManutencao;
    private Double kmRodados;

    public Manutencao(Integer codigoVeiculo, Date dataManutencao, String descricaoManutencao, Double valorManutencao, Double kmRodados) {
        this.codigoVeiculo = codigoVeiculo;
        this.dataManutencao = dataManutencao;
        this.descricaoManutencao = descricaoManutencao;
        this.valorManutencao = valorManutencao;
        this.kmRodados = kmRodados;
    }
       
    public Manutencao(){
        
    }
       
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigoVeiculo() {
        return codigoVeiculo;
    }

    public void setCodigoVeiculo(Integer codigoVeiculo) {
        this.codigoVeiculo = codigoVeiculo;
    }

    public Date getDataManutencao() {
        return dataManutencao;
    }

    public void setDataManutencao(Date dataManutencao) {
        this.dataManutencao = dataManutencao;
    }

    public String getDescricaoManutencao() {
        return descricaoManutencao;
    }

    public void setDescricaoManutencao(String descricaoManutencao) {
        this.descricaoManutencao = descricaoManutencao;
    }

    public Double getValorManutencao() {
        return valorManutencao;
    }

    public void setValorManutencao(Double valorManutencao) {
        this.valorManutencao = valorManutencao;
    }

    public Double getKmRodados() {
        return kmRodados;
    }

    public void setKmRodados(Double kmRodados) {
        this.kmRodados = kmRodados;
    }
    
}
