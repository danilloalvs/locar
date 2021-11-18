/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locarfx.Helper;

import javafx.scene.control.Alert;

/**
 *
 * @author Administrador
 */
public final class Mensagem {

    public static void ExibeMensagemCadastroRealizado() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cadastro");
        alert.setHeaderText("Cadastro realizado");
        String mensagem = "O cadastro foi realizado com sucesso!";
        alert.setContentText(mensagem);
        alert.show();
    }
    
    public static void ExibeMensagemCadastroComErro() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Falha ao cadastrar");
            alert.setHeaderText("Algo deu errado!");
            alert.setContentText("Verifique se os campos estão preenchidos corretamente!");
    }
    
    public static void ExibeMensagemUsuarioEncontrado() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Usuário");
        alert.setHeaderText("Usuário encontrado");
        String mensagem = "O usuário foi encontrado, prossiga com o cadastro!";
        alert.setContentText(mensagem);
        alert.show();
    }
    
    public static void ExibeMensagemUsuarioNaoEncontrado() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Usuário");
        alert.setHeaderText("Usuário não encontrado");
        String mensagem = "Desculpe, um usuário com este CPF não foi encontrado, tente novamente!";
        alert.setContentText(mensagem);
        alert.show();
    }
    
    public static void ExibeMensagemNaoFoiPossivelCalcular() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Erro");
        alert.setHeaderText("Erro no cálculo");
        String mensagem = "Desculpe, não foi possível calcular o valor total desta locação, tente novamente!";
        alert.setContentText(mensagem);
        alert.show();
    }

}
