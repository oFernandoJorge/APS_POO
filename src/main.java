

public class Main {
    public static void main(String[] args) {
        GerencionamentoEventos sistema = new GerencionamentoEventos();
        Menu menu = new Menu(sistema);
        menu.iniciar();
    }
}
