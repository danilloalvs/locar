/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locarfx.Model;

/**
 *
 * @author Administrador
 */
public class Marca {

    public Marca(Integer codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }
    private Integer codigo;    
    private String nome;
    
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Marca(String nome) {
        this.nome = nome;
    }   
    
    public Marca() {
        
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
