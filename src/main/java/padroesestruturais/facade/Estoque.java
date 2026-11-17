package padroesestruturais.facade;

import java.util.HashMap;
import java.util.Map;

public class Estoque {

    private final Map<String, Integer> produtos = new HashMap<>();

    public void adicionarProduto(String produto, int quantidade) {
        produtos.merge(produto, quantidade, Integer::sum);
    }

    public boolean verificarDisponibilidade(String produto, int quantidade) {
        return produtos.getOrDefault(produto, 0) >= quantidade;
    }

    public void reservar(String produto, int quantidade) {
        produtos.put(produto, produtos.getOrDefault(produto, 0) - quantidade);
    }

    public int getQuantidade(String produto) {
        return produtos.getOrDefault(produto, 0);
    }
}
