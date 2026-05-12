package service;

import enums.Categoria;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import model.Corredor;
import model.EventoEsportivo;
import model.Inscricao;
import model.Patrocinador;
import util.Formatador;

public class GerenciamentoEventos {
    private final List<EventoEsportivo> eventos = new ArrayList<>();
    private final List<Corredor> corredores = new ArrayList<>();
    private final List<Patrocinador> patrocinadores = new ArrayList<>();
    private final List<Inscricao> inscricoes = new ArrayList<>();

    // Getters das listas
    public List<EventoEsportivo> getEventos() {
        return eventos;
    }

    public List<Corredor> getCorredores() {
        return corredores;
    }

    public List<Patrocinador> getPatrocinadores() {
        return patrocinadores;
    }

    public List<Inscricao> getInscricoes() {
        return inscricoes;
    }

    // Cadastros
    public void cadastrarEvento(EventoEsportivo evento) {
        eventos.add(evento);
        System.out.println("\u2705 Evento cadastrado: " + evento.getNome());
    }

    public void cadastrarCorredor(Corredor corredor) {
        corredores.add(corredor);
        System.out.println("\u2705 Corredor cadastrado: " + corredor.getNome() + " [" + corredor.getCategoria() + "]");
    }

    public void cadastrarPatrocinador(Patrocinador patrocinador) {
        patrocinadores.add(patrocinador);
        System.out.println("\u2705 Patrocinador cadastrado: " + patrocinador.getEmpresa());
    }

    // Inscrição de corredores em eventos
    public Inscricao inscreverCorredor(Corredor corredor, EventoEsportivo evento, String dataInscricao) {
        long totalInscritos = inscricoes.stream()
                .filter(i -> i.getEvento() == evento)
                .count();

        if (totalInscritos >= evento.getVagasDisponiveis()) {
            System.out.println("\u274C Vagas esgotadas para \"" + evento.getNome() + "\"!");
            return null;
        }

        boolean jaInscrito = inscricoes.stream()
                .anyMatch(i -> i.getCorredor() == corredor && i.getEvento() == evento);

        if (jaInscrito) {
            System.out.println("\u26A0\uFE0F  " + corredor.getNome()
                    + " já está inscrito em \"" + evento.getNome() + "\"!");
            return null;
        }

        Inscricao inscricao = new Inscricao(corredor, evento, dataInscricao);
        inscricoes.add(inscricao);

        System.out.println("\uD83C\uDFBD Inscrição realizada! " + corredor.getNome()
                + " → \"" + evento.getNome()
                + "\" | Dorsal: #" + inscricao.getNumeroInscricao());

        return inscricao;
    }

    // Listar eventos
    public void listarEventos() {
        Formatador.separador("\uD83D\uDCC5 EVENTOS CADASTRADOS");
        if (eventos.isEmpty()) {
            System.out.println("  Nenhum evento encontrado.");
            return;
        }
        eventos.forEach(EventoEsportivo::exibirInformacoes);
    }

    public void listarCorredores() {
        Formatador.separador("\uD83C\uDFC3 CORREDORES CADASTRADOS");
        if (corredores.isEmpty()) {
            System.out.println("  Nenhum corredor encontrado.");
            return;
        }
        // Polimorfismo: cada participante sabe como se exibir
        corredores.forEach(Corredor::exibirInformacoes);
    }

    public void listarPatrocinadores() {
        Formatador.separador("\uD83C\uDFE2 PATROCINADORES");
        if (patrocinadores.isEmpty()) {
            System.out.println("  Nenhum patrocinador encontrado.");
            return;
        }
        patrocinadores.forEach(Patrocinador::exibirInformacoes);
    }

    public void listarInscricoes() {
        Formatador.separador("\uD83D\uDCCB INSCRIÇÕES REALIZADAS");
        if (inscricoes.isEmpty()) {
            System.out.println("  Nenhuma inscrição encontrada.");
            return;
        }
        inscricoes.forEach(Inscricao::exibirInformacoes);
    }

    // Relatorios Avançados
    public void exibirRanking() {
        Formatador.separador("\uD83C\uDFC6 RANKING POR CATEGORIA");

        for (Categoria categoria : Categoria.values()) {
            List<Inscricao> lista = inscricoes.stream()
                    .filter(i -> i.getCorredor().getCategoria() == categoria && i.getTempoFinal() != null)
                    .sorted(Comparator.comparingInt(Inscricao::getTempoFinal))
                    .collect(Collectors.toList());
            if (lista.isEmpty())
                continue;

            System.out.println("\n  \uD83D\uDCCC Categoria: " + categoria);
            System.out.println("  " + "─".repeat(50));

            int pos = 1;
            for (Inscricao i : lista) {
                String medalha = pos == 1 ? "\uD83E\uDD47"
                        : pos == 2 ? "\uD83E\uDD48" : pos == 3 ? "\uD83E\uDD49" : "  ";
                System.out.printf("  %s %dº %-22s → %-10s [%s]%n",
                        medalha, pos,
                        i.getCorredor().getNome(),
                        Formatador.minutos(i.getTempoFinal()),
                        i.getEvento().getNome());
                pos++;
            }

        }
    }

    // Relatorio Financeiro
    public void exibirRelatorioFinanceiro() {
        Formatador.separador("\uD83D\uDCB0 RELATÓRIO FINANCEIRO");

        double totalGeral = 0;

        for (EventoEsportivo evento : eventos) {
            long qtd = inscricoes.stream().filter(i -> i.getEvento() == evento).count();

            double receitaInscricoes = qtd * evento.getValorInscricao();
            double receitaPatrocinios = evento.calcularTotalPatrocinios();
            double receitaTotal = receitaInscricoes + receitaPatrocinios;
            totalGeral += receitaTotal;

            System.out.println("\n  \uD83C\uDFC1 " + evento.getNome());
            System.out.println("  " + "─".repeat(50));
            System.out.printf("     Inscrições   : %d × R$%.2f = %s%n",
                    qtd, evento.getValorInscricao(), Formatador.moeda(receitaInscricoes));
            System.out.printf("     Patrocínios  : %s%n", Formatador.moeda(receitaPatrocinios));
            System.out.printf("     Total evento : %s%n", Formatador.moeda(receitaTotal));
        }

        System.out.println("\n  " + "═".repeat(50));
        System.out.printf("  \uD83D\uDCB5 FATURAMENTO TOTAL GERAL: %s%n", Formatador.moeda(totalGeral));
        System.out.println("  " + "═".repeat(50));
    }
}
