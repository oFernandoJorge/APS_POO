package model;

import enums.TipoParceria;

public class Patrocinador extends Participante {
    private String empresa;
    private double valorPatrocinio;
    private TipoParceria tipoParceria;

    public Patrocinador(String responsavel, String email, String telefone, String empresa, double valorPatrocinio, TipoParceria tipoParceria) {
        super(responsavel, email, telefone);
        this.empresa = empresa;
        this.valorPatrocinio = valorPatrocinio;
        this.tipoParceria = tipoParceria;
    }

    //Getters e Setters
    public String getEmpresa() {
        return empresa;
    }
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public double getValorPatrocinio() {
        return valorPatrocinio;
    }
    public void setValorPatrocinio(double valorPatrocinio) {
        this.valorPatrocinio = valorPatrocinio;
    }

    public TipoParceria getTipoParceria() {
        return tipoParceria;
    }
    public void setTipoParceria(TipoParceria tipoParceria) {
        this.tipoParceria = tipoParceria;
    }

    //Poliformismo
    @Override
    public void exibirInformacoes() {
        System.out.println("┌─────────────────────────────────────────┐");
        System.out.println("│           \uD83C\uDFE2 PATROCINADOR               │");
        System.out.println("├─────────────────────────────────────────┤");
        System.out.printf("│  Empresa   : %-27s│%n", getEmpresa());
        System.out.printf("│  Responsav.: %-27s│%n", getNome());
        System.out.printf("│  Parceria  : %-27s│%n", getTipoParceria());
        System.out.printf("│  Valor     : R$ %-24.2f│%n", getValorPatrocinio());
        System.out.printf("│  Email     : %-27s│%n", getEmail());
        System.out.printf("│  Telefone  : %-27s│%n", getTelefone());
        System.out.println("└─────────────────────────────────────────┘");
    }
}
