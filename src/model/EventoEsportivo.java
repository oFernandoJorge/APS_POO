package model;

import java.util.ArrayList;
import java.util.List;
import util.Formatador;

public class EventoEsportivo {
    private String nome;
    private String data;
    private String local;
    private double valorInscricao;
    private double distancia;
    private int vagasDisponiveis;

    private final List<Patrocinador> patrocinadores = new ArrayList<>();

    public EventoEsportivo(String nome, String data, String local, double valorInscricao, double distancia,
            int vagasDisponiveis) {
        this.nome = nome;
        this.data = data;
        this.local = local;
        this.valorInscricao = valorInscricao;
        this.distancia = distancia;
        this.vagasDisponiveis = vagasDisponiveis;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public double getValorInscricao() {
        return valorInscricao;
    }

    public void setValorInscricao(double valorInscricao) {
        this.valorInscricao = valorInscricao;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public int getVagasDisponiveis() {
        return vagasDisponiveis;
    }

    public List<Patrocinador> getPatrocinadores() {
        return patrocinadores;
    }

    // Comportamentos
    public void adicionarPatrocinador(Patrocinador patrocinador) {
        patrocinadores.add(patrocinador);
        System.out.println("\u2705 Patrocinador \"" + patrocinador.getEmpresa()
                + "\" vinculado a \"" + nome + "\"!");
    }

    public String calcularTempoMedioEsperado(double pace) {
        return Formatador.minutos((int) Math.round(distancia * pace));
    }

    public double calcularTotalPatrocinios() {
        return patrocinadores.stream().mapToDouble(Patrocinador::getValorPatrocinio).sum();
    }

    public void exibirInformacoes() {
        System.out.println(
                "\n\u2554\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2557");
        System.out.println("\u2551          \uD83C\uDFC1 EVENTO ESPORTIVO            \u2551");
        System.out.println(
                "\u2560\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2563");
        System.out.printf("\u2551  Nome      : %-27s\u2551%n", nome);
        System.out.printf("\u2551  Data      : %-27s\u2551%n", data);
        System.out.printf("\u2551  Local     : %-27s\u2551%n", local);
        System.out.printf("\u2551  Distância : %-27s\u2551%n", distancia + " km");
        System.out.printf("\u2551  Inscrição : R$ %-24.2f\u2551%n", valorInscricao);
        System.out.printf("\u2551  Limite    : %-27s\u2551%n", vagasDisponiveis + " atletas");
        System.out.printf("\u2551  T.médio   : %-27s\u2551%n",
                calcularTempoMedioEsperado(5.5) + " (pace 5:30/km)");

        if (!patrocinadores.isEmpty()) {
            System.out.println(
                    "\u2560\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2563");
            System.out.println("\u2551  Patrocinadores:                        \u2551");
            for (Patrocinador p : patrocinadores) {
                System.out.printf("\u2551   \u2022 %-37s\u2551%n",
                        p.getEmpresa() + " [" + p.getTipoParceria() + "]");
            }
            System.out.printf("\u2551  Total patrocínios: R$ %-17.2f\u2551%n", calcularTotalPatrocinios());
        }

        System.out.println(
                "\u255A\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u255D");
    }
}
