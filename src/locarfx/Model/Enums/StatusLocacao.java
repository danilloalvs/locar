package locarfx.Model.Enums;

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
