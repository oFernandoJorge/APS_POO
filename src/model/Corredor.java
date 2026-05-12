package model;

import enums.Categoria;

public class Corredor extends Participante {
    private int idade;
    private Categoria categoria;
    private Integer recordPessoal;

    public Corredor(String nome, String email, String telefone, int idade, Categoria categoria) {
        super(nome, email, telefone);
        this.idade    = idade;
        this.categoria = categoria;
        this.recordPessoal = null;
    }

    //Getters e Setters 
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Integer getRecordPessoal() {
        return recordPessoal;
    }
    public void setRecordPessoal(Integer recordPessoal) {
        this.recordPessoal = recordPessoal;
    }

    public String getRecordFormatado(){
        return recordPessoal == null ? "Sem recorde pessoal" : Formatador.minutos(recordPessoal);
    }

    //Poliformismo

    @Override
    public void exibirInformacoes() {
         System.out.println("┌─────────────────────────────────────────┐");
        System.out.println("│            \uD83C\uDFC3 CORREDOR                  │");
        System.out.println("├─────────────────────────────────────────┤");
        System.out.printf("│  Nome      : %-27s│%n", getNome());
        System.out.printf("│  Idade     : %-27s│%n", idade + " anos");
        System.out.printf("│  Categoria : %-27s│%n", categoria);
        System.out.printf("│  Email     : %-27s│%n", getEmail());
        System.out.printf("│  Telefone  : %-27s│%n", getTelefone());
        System.out.printf("│  Recorde   : %-27s│%n", getRecordFormatado());
        System.out.println("└─────────────────────────────────────────┘");
    }
}
