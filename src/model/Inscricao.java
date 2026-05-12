package model;

import util.Formatador;

public class Inscricao {
    private static int contadorId = 1;

    private final int id;
    private final Corredor corredor;
    private final EventoEsportivo evento;
    private final String dataInscricao;
    private final int numeroInscricao;
    private Integer tempoFinal;

    public Inscricao(Corredor corredor, EventoEsportivo evento, String dataInscricao) {
        this.id            = contadorId++;
        this.corredor      = corredor;
        this.evento        = evento;
        this.dataInscricao = dataInscricao;
        this.numeroInscricao  = 1000 + id;
    }

    // Getters
    public int getId() {
        return id;
    }
    public Corredor getCorredor() {
        return corredor;
    }
    public EventoEsportivo getEvento() {
        return evento;
    }
    public String getDataInscricao() {
        return dataInscricao;
    }
    public int getNumeroInscricao() {
        return numeroInscricao;
    }
    public Integer getTempoFinal() {
        return tempoFinal;
    }

    //Comportamentos
    public void registrarTempoFinal(int tempo) {
        this.tempoFinal = tempo;

        if(corredor.getRecordPessoal() == null || tempo < corredor.getRecordPessoal()){
            System.out.println("\uD83C\uDFC6 Novo recorde pessoal de " + corredor.getNome()
                    + "! → " + Formatador.minutos(tempo));
        }
    }

    public void exibirInformacoes() {
        String tempo = tempoFinal == null
                ? "Aguardando prova..."
                : Formatador.minutos(tempoFinal);

        System.out.println("┌─────────────────────────────────────────┐");
        System.out.println("│           \uD83D\uDCCB INSCRIÇÃO                  │");
        System.out.println("├─────────────────────────────────────────┤");
        System.out.printf("│  ID         : %-26s│%n", "#" + id);
        System.out.printf("│  Corredor   : %-26s│%n", corredor.getNome());
        System.out.printf("│  Categoria  : %-26s│%n", corredor.getCategoria());
        System.out.printf("│  Evento     : %-26s│%n", evento.getNome());
        System.out.printf("│  Data insc. : %-26s│%n", dataInscricao);
        System.out.printf("│  Dorsal     : %-26s│%n", "#" + numeroInscricao);
        System.out.printf("│  Taxa paga  : R$ %-23.2f│%n", evento.getValorInscricao());
        System.out.printf("│  Tempo final: %-26s│%n", tempo);
        System.out.println("└─────────────────────────────────────────┘");
    }
}
