import service.GerenciamentoEventos;
import ui.Menu;

public class Main {
    public static void main(String[] args) {
        GerenciamentoEventos sistema = new GerenciamentoEventos();
        Menu menu = new Menu(sistema);
        menu.iniciar();
    }
}
