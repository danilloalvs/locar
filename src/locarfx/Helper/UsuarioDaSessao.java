package locarfx.Helper;

import locarfx.Model.Enums.Cargo;
import locarfx.Model.Usuario;

public final class UsuarioDaSessao {

    public static UsuarioDaSessao instancia;

    private final Usuario usuario;

    private UsuarioDaSessao(Usuario usuario) {
        this.usuario = usuario;
    }

    public static void setUsuarioDaSessao(Usuario usuario) {
        instancia = new UsuarioDaSessao(usuario);
    }

    public Cargo getCargoUsuarioDaSessao() {
        return instancia.usuario.getCargo();
    }

    public String getNomeUsuarioDaSessao() {
        return instancia.usuario.getNome();
    }
    
    public Integer getCodigoUsuarioDaSessao(){
        return instancia.usuario.getCodigo();
    }

    public void limparUsuarioDaSessao() {
        instancia = null;
    }

}
