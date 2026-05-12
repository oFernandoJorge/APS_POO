package ui;

import enums.Categoria;
import enums.TipoParceria;
import java.util.List;
import java.util.Scanner;
import model.Corredor;
import model.EventoEsportivo;
import model.Inscricao;
import model.Patrocinador;
import service.GerenciamentoEventos;
import util.Formatador;

public class Menu {

    private final GerenciamentoEventos sistema;
    private final Scanner scanner;

    public Menu(GerenciamentoEventos sistema) {
        this.sistema = sistema;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        Formatador.cabecalho();

        boolean executando = true;
        while (executando) {
            exibirMenuPrincipal();
            int opcao = lerInteiro("Digite a opção desejada: ");

            switch (opcao) {
                case 1 -> cadastrarEvento();
                case 2 -> cadastrarCorredor();
                case 3 -> cadastrarPatrocinador();
                case 4 -> vincularPatrocinadorEvento();
                case 5 -> inscreverCorredor();
                case 6 -> registrarTempo();
                case 7 -> sistema.listarEventos();
                case 8 -> sistema.listarCorredores();
                case 9 -> sistema.listarPatrocinadores();
                case 10 -> sistema.listarInscricoes();
                case 11 -> sistema.exibirRanking();
                case 12 -> sistema.exibirRelatorioFinanceiro();
                case 0 -> {
                    Formatador.separador("👋 Até logo! Boas corridas!");
                    executando = false;
                }
            }
        }

        scanner.close();
    }

    // Menu
    private void exibirMenuPrincipal() {
        System.out.println("\n╔═════════════════════════════════════════╗");
        System.out.println("║            MENU PRINCIPAL               ║");
        System.out.println("╠═════════════════════════════════════════╣");
        System.out.println("║  CADASTROS                              ║");
        System.out.println("║   1 · Cadastrar Evento                  ║");
        System.out.println("║   2 · Cadastrar Corredor                ║");
        System.out.println("║   3 · Cadastrar Patrocinador            ║");
        System.out.println("║   4 · Vincular Patrocinador a Evento    ║");
        System.out.println("╠═════════════════════════════════════════╣");
        System.out.println("║  OPERAÇÕES                              ║");
        System.out.println("║   5 · Inscrever Corredor em Evento      ║");
        System.out.println("║   6 · Registrar Tempo Final             ║");
        System.out.println("╠═════════════════════════════════════════╣");
        System.out.println("║  CONSULTAS                              ║");
        System.out.println("║   7 · Listar Eventos                    ║");
        System.out.println("║   8 · Listar Corredores                 ║");
        System.out.println("║   9 · Listar Patrocinadores             ║");
        System.out.println("║  10 · Listar Inscrições                 ║");
        System.out.println("╠═════════════════════════════════════════╣");
        System.out.println("║  RELATÓRIOS (BÔNUS)                     ║");
        System.out.println("║  11 · Ranking por Categoria             ║");
        System.out.println("║  12 · Relatório de Faturamento          ║");
        System.out.println("╠═════════════════════════════════════════╣");
        System.out.println("║   0 · Sair                              ║");
        System.out.println("╚═════════════════════════════════════════╝");
    }

    // Fluxo de Cadastro
    private void cadastrarEvento() {
        Formatador.separador("🏁 NOVO EVENTO");

        String nome = lerTexto("Nome do evento");
        String data = lerTexto("Data (DD/MM/AAAA)");
        String local = lerTexto("Local de largada");
        double dist = lerDouble("Distância (km)");
        double taxa = lerDouble("Taxa de inscrição (R$)");
        int limite = lerInteiro("Limite de participantes");

        EventoEsportivo evento = new EventoEsportivo(nome, data, local, dist, taxa, limite);
        sistema.cadastrarEvento(evento);
    }

    private void cadastrarCorredor() {
        Formatador.separador("🏃 NOVO CORREDOR");

        String nome = lerTexto("Nome completo");
        int idade = lerInteiro("Idade");
        String sexo = lerTexto("Sexo (M/F)");
        String email = lerTexto("Email");
        String telefone = lerTexto("Telefone");
        Categoria cat = lerCategoria();

        Corredor corredor = new Corredor(nome, email, telefone, idade, cat);
        sistema.cadastrarCorredor(corredor);
    }

    private void cadastrarPatrocinador() {
        Formatador.separador("🔗 VINCULAR PATROCINADOR A EVENTO");

        List<Patrocinador> patrocinadores = sistema.getPatrocinadores();
        List<EventoEsportivo> eventos = sistema.getEventos();

        if (patrocinadores.isEmpty()) {
            System.out.println("\n  ⚠️  Nenhum patrocinador cadastrado ainda.");
            return;
        }
        if (eventos.isEmpty()) {
            System.out.println("\n  ⚠️  Nenhum evento cadastrado ainda.");
            return;
        }

        Patrocinador p = selecionarPatrocinador(patrocinadores);
        EventoEsportivo e = selecionarEvento(eventos);

        if (p != null && e != null) {
            e.adicionarPatrocinador(p);
        }
    }

    private void vincularPatrocinadorEvento() {
        Formatador.separador("🔗 VINCULAR PATROCINADOR A EVENTO");

        List<Patrocinador> patrocinadores = sistema.getPatrocinadores();
        List<EventoEsportivo> eventos = sistema.getEventos();

        if (patrocinadores.isEmpty()) {
            System.out.println("\n  ⚠️  Nenhum patrocinador cadastrado ainda.");
            return;
        }
        if (eventos.isEmpty()) {
            System.out.println("\n  ⚠️  Nenhum evento cadastrado ainda.");
            return;
        }

        Patrocinador p = selecionarPatrocinador(patrocinadores);
        EventoEsportivo e = selecionarEvento(eventos);

        if (p != null && e != null) {
            e.adicionarPatrocinador(p);
        }
    }

    private void inscreverCorredor() {
        Formatador.separador("📋 INSCREVER CORREDOR EM EVENTO");

        List<Corredor> corredores = sistema.getCorredores();
        List<EventoEsportivo> eventos = sistema.getEventos();

        if (corredores.isEmpty()) {
            System.out.println("\n  ⚠️  Nenhum corredor cadastrado ainda.");
            return;
        }
        if (eventos.isEmpty()) {
            System.out.println("\n  ⚠️  Nenhum evento cadastrado ainda.");
            return;
        }

        Corredor c = selecionarCorredor(corredores);
        EventoEsportivo e = selecionarEvento(eventos);
        String data = lerTexto("Data da inscrição (DD/MM/AAAA)");

        if (c != null && e != null) {
            sistema.inscreverCorredor(c, e, data);
        }
    }

    private void registrarTempo() {
        Formatador.separador("⏱️  REGISTRAR TEMPO FINAL");

        List<Inscricao> inscricoes = sistema.getInscricoes();

        if (inscricoes.isEmpty()) {
            System.out.println("\n  ⚠️  Nenhuma inscrição encontrada.");
            return;
        }

        // Exibe inscrições sem tempo ainda
        System.out.println("\n  Inscrições sem tempo registrado:\n");
        boolean algumaSemTempo = false;
        for (int i = 0; i < inscricoes.size(); i++) {
            Inscricao ins = inscricoes.get(i);
            if (ins.getTempoFinal() == null) {
                System.out.printf("  [%d] %-22s → %s%n",
                        i + 1,
                        ins.getCorredor().getNome(),
                        ins.getEvento().getNome());
                algumaSemTempo = true;
            }
        }

        if (!algumaSemTempo) {
            System.out.println("  ✅ Todos os corredores já têm tempo registrado!");
            return;
        }

        int idx = lerInteiro("Número da inscrição") - 1;
        if (idx < 0 || idx >= inscricoes.size()) {
            System.out.println("  ⚠️  Número inválido.");
            return;
        }

        Inscricao ins = inscricoes.get(idx);
        System.out.println("\n  Corredor : " + ins.getCorredor().getNome());
        System.out.println("  Evento   : " + ins.getEvento().getNome()
                + " (" + ins.getEvento().getDistancia() + " km)");

        int horas = lerInteiro("Horas");
        int minutos = lerInteiro("Minutos");
        int total = horas * 60 + minutos;

        ins.registrarTempoFinal(total);
    }

    // Seletores de Lista
    private Corredor selecionarCorredor(List<Corredor> corredores) {
        System.out.println("\n  Corredores disponíveis:\n");
        for (int i = 0; i < corredores.size(); i++) {
            System.out.printf("  [%d] %s [%s]%n",
                    i + 1, corredores.get(i).getNome(), corredores.get(i).getCategoria());
        }
        int idx = lerInteiro("Número do corredor") - 1;
        if (idx < 0 || idx >= corredores.size()) {
            System.out.println("  ⚠️  Número inválido.");
            return null;
        }
        return corredores.get(idx);
    }

    private EventoEsportivo selecionarEvento(List<EventoEsportivo> eventos) {
        System.out.println("\n  Eventos disponíveis:\n");
        for (int i = 0; i < eventos.size(); i++) {
            System.out.printf("  [%d] %s (%s)%n",
                    i + 1, eventos.get(i).getNome(), eventos.get(i).getData());
        }
        int idx = lerInteiro("Número do evento") - 1;
        if (idx < 0 || idx >= eventos.size()) {
            System.out.println("  ⚠️  Número inválido.");
            return null;
        }
        return eventos.get(idx);
    }

    private Patrocinador selecionarPatrocinador(List<Patrocinador> patrocinadores) {
        System.out.println("\n  Patrocinadores disponíveis:\n");
        for (int i = 0; i < patrocinadores.size(); i++) {
            System.out.printf("  [%d] %s [%s]%n",
                    i + 1, patrocinadores.get(i).getEmpresa(), patrocinadores.get(i).getTipoParceria());
        }
        int idx = lerInteiro("Número do patrocinador") - 1;
        if (idx < 0 || idx >= patrocinadores.size()) {
            System.out.println("  ⚠️  Número inválido.");
            return null;
        }
        return patrocinadores.get(idx);
    }

    // Leitura de Enums
    private Categoria lerCategoria() {
        System.out.println("\n  Categorias disponíveis:");
        System.out.println("  [1] INICIANTE");
        System.out.println("  [2] AMADOR");
        System.out.println("  [3] PROFISSIONAL");

        while (true) {
            int op = lerInteiro("Categoria");
            switch (op) {
                case 1 -> {
                    return Categoria.INICIANTE;
                }
                case 2 -> {
                    return Categoria.AMADOR;
                }
                case 3 -> {
                    return Categoria.PROFISSIONAL;
                }
                default -> System.out.println("  ⚠️  Opção inválida. Digite 1, 2 ou 3.");
            }
        }
    }

    private TipoParceria lerTipoParceria() {
        System.out.println("\n  Tipos de parceria:");
        System.out.println("  [1] BRONZE");
        System.out.println("  [2] PRATA");
        System.out.println("  [3] OURO");
        System.out.println("  [4] DIAMANTE");

        while (true) {
            int op = lerInteiro("Tipo de parceria");
            switch (op) {
                case 1 -> {
                    return TipoParceria.BRONZE;
                }
                case 2 -> {
                    return TipoParceria.PRATA;
                }
                case 3 -> {
                    return TipoParceria.OURO;
                }
                case 4 -> {
                    return TipoParceria.DIAMANTE;
                }
                default -> System.out.println("  ⚠️  Opção inválida. Digite de 1 a 4.");
            }
        }
    }

    // Leitores de Input
    private String lerTexto(String prompt) {
        System.out.print("  " + prompt + ": ");
        String valor = scanner.nextLine().trim();
        while (valor.isEmpty()) {
            System.out.print("  ⚠️  Campo obrigatório. " + prompt + ": ");
            valor = scanner.nextLine().trim();
        }
        return valor;
    }

    private int lerInteiro(String prompt) {
        while (true) {
            System.out.print("  " + prompt + ": ");
            try {
                int valor = Integer.parseInt(scanner.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("  ⚠️  Digite um número inteiro válido.");
            }
        }
    }

    private double lerDouble(String prompt) {
        while (true) {
            System.out.print("  " + prompt + ": ");
            try {
                double valor = Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
                if (valor < 0) {
                    System.out.println("  ⚠️  O valor não pode ser negativo.");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("  ⚠️  Digite um número válido (ex: 10 ou 10,5).");
            }
        }
    }
}
