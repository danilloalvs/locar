/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locarfx.Model.Enums;

/**
 *
 * @author Administrador
 */
public enum StatusLocacao {
    ABERTA,
    FECHADA;
    
    public static StatusLocacao getStatusLocacao(String statusLocacao) {
        for (StatusLocacao status : StatusLocacao.values()) {
            if (status.toString().equals(statusLocacao.toUpperCase())) {
                return status;
            }
        }
        return null;
    }
}
