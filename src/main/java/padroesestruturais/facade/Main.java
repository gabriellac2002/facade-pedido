package padroesestruturais.facade;

public class Main {

    public static void main(String[] args) {
        Estoque estoque = new Estoque();
        estoque.adicionarProduto("Notebook", 5);

        PedidoFacade facade = new PedidoFacade(estoque);

        System.out.println(facade.finalizarPedido("Maria", "Notebook", 2, 3500.0, "01310-100"));
        System.out.println(facade.finalizarPedido("Joao", "Notebook", 10, 3500.0, "20000-000"));
    }
}
