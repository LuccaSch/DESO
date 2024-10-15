/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package isi.deso.tp.model.DTO;
import java.util.*;
/**
 *
 * @author Franco Ocampo
 */
public class PedidoDTO {
    
    private double saldo;
    private String alias, CBU, cuit;
    private List<ItemPedidoDTO> listaItemPedidoDTO;

    public PedidoDTO(double saldo, String alias, String CBU, String cuit) {
        this.saldo = saldo;
        this.alias = alias;
        this.CBU = CBU;
        this.cuit = cuit;
        this.listaItemPedidoDTO=null;
    }

    public PedidoDTO() {
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCBU() {
        return CBU;
    }

    public void setCBU(String CBU) {
        this.CBU = CBU;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }
    
    
    
}
