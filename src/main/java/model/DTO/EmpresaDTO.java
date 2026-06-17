package model.DTO;

import java.util.ArrayList;

public record EmpresaDTO (
    String cuit,
    String razonSocial,
    String contacto,
    String contactoRepresentante,
    Boolean radicada,
    LoteDTO lote,
    ArrayList<RepresentanteEmpresaDTO> representantes){

    public EmpresaDTO(String cuit, String razonSocial, String contacto, String contactoRepresentante,
                      Boolean radicada, LoteDTO lote, ArrayList<RepresentanteEmpresaDTO> representantes) {
        this.cuit = cuit;
        this.razonSocial = razonSocial;
        this.contacto = contacto;
        this.contactoRepresentante = contactoRepresentante;
        this.radicada = radicada;
        this.lote = lote;
        this.representantes = representantes;
    }

    public String getCuit() {
        return cuit;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public String getContacto() {
        return contacto;
    }

    public String getContactoRepresentante() {
        return contactoRepresentante;
    }

    public Boolean getRadicada() {
        return radicada;
    }

    public LoteDTO getLote() {
        return lote;
    }

    public ArrayList<RepresentanteEmpresaDTO> getRepresentantes() {
        return representantes;
    }

}