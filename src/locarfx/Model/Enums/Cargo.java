/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
