package padroesestruturais.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PedidoFacadeTest {

    private Estoque estoque;
    private PedidoFacade facade;

    @BeforeEach
    void preparar() {
        estoque = new Estoque();
        estoque.adicionarProduto("Notebook", 5);
        facade = new PedidoFacade(estoque);
    }

    @Test
    void deveFinalizarPedidoOrquestrandoSubsistemas() {
        String resultado = facade.finalizarPedido("Maria", "Notebook", 2, 100.0, "01310-100");
        assertTrue(resultado.contains("Entrega de Notebook agendada para o CEP 01310-100"));
        assertTrue(resultado.contains("Notificacao para Maria"));
        assertTrue(resultado.contains("R$ 215.00"));
    }

    @Test
    void deveBaixarEstoqueAposPedido() {
        facade.finalizarPedido("Maria", "Notebook", 2, 100.0, "01310-100");
        assertEquals(3, estoque.getQuantidade("Notebook"));
    }

    @Test
    void deveRecusarPedidoSemEstoque() {
        String resultado = facade.finalizarPedido("Joao", "Notebook", 10, 100.0, "01310-100");
        assertEquals("Pedido recusado: estoque insuficiente de Notebook", resultado);
        assertEquals(5, estoque.getQuantidade("Notebook"));
    }

    @Test
    void deveRecusarPedidoComPagamentoInvalido() {
        String resultado = facade.finalizarPedido("Ana", "Notebook", 1, -1000.0, "01310-100");
        assertEquals("Pedido recusado: pagamento nao aprovado", resultado);
        assertEquals(5, estoque.getQuantidade("Notebook"));
    }
}
