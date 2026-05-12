package util;

public class Formatador {
    private Formatador() {
        // Construtor privado para evitar instância
    }

    //Converte minutos para um formato legível de horas e minutos.
    public static String minutos (int totalMinutos) {
        int horas = totalMinutos / 60;
        int minutos = totalMinutos % 60;
        if (horas > 0) {
            return horas + "h" + String.format("%02d", minutos) + "min";
        }
        return minutos + " min";
    }

    //Formata um valor monetário para o formato brasileiro, com símbolo de real e separadores adequados.
    public static String moeda (double valor) {
        return String.format("R$ %,.2f", valor).replace(",", "X")
                .replace(".", ",").replace("X", ".");
    }

    public static void separador (String titulo){
        System.out.println("\n" + "=".repeat(55));
        System.out.println("  " + titulo);
        System.out.println("=".repeat(55));
    }

    public static void cabecalho (){
        System.out.println("\n");
        System.out.println("  ╔═══════════════════════════════════════════╗");
        System.out.println("  ║   🏁  CORRIDAS DE RUA – FORTALEZA / CE   ║");
        System.out.println("  ║       Sistema de Gerenciamento POO        ║");
        System.out.println("  ╚═══════════════════════════════════════════╝");
        System.out.println();
    }
}
