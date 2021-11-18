package locarfx.Model.Enums;

public enum FormaDePagamento {
    CRÉDITO,
    DÉBITO,
    BOLETO,
    DINHEIRO,
    PIX;
    
    public static FormaDePagamento getFormaDePagamento(String formaDePagamento) {
        for (FormaDePagamento forma : FormaDePagamento.values()) {
            if (forma.toString().equals(formaDePagamento.toUpperCase())) {
                return forma;
            }
        }
        return null;
    }
}
