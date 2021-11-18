package locarfx.Model.Enums;

public enum Cargo {
    GERENTE,
    VENDEDOR;
    
    public static Cargo getCargo(String stringCargo) {
        for (Cargo cargo : Cargo.values()) {
            if (cargo.toString().equals(stringCargo.toUpperCase())) {
                return cargo;
            }
        }
        return null;
    }
}
