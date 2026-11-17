package padroesestruturais.facade;

import java.util.Locale;

public class PedidoFacade {

    private final Estoque estoque;
    private final ServicoPagamento pagamento;
    private final ServicoEntrega entrega;
    private final ServicoNotificacao notificacao;

    public PedidoFacade(Estoque estoque) {
        this.estoque = estoque;
        this.pagamento = new ServicoPagamento();
        this.entrega = new ServicoEntrega();
        this.notificacao = new ServicoNotificacao();
    }

    public String finalizarPedido(String cliente, String produto, int quantidade, double precoUnitario, String cep) {
        if (!estoque.verificarDisponibilidade(produto, quantidade)) {
            return "Pedido recusado: estoque insuficiente de " + produto;
        }

        double total = precoUnitario * quantidade + entrega.calcularFrete(cep);

        if (!pagamento.processar(cliente, total)) {
            return "Pedido recusado: pagamento nao aprovado";
        }

        estoque.reservar(produto, quantidade);
        String agendamento = entrega.agendar(produto, cep);
        String aviso = notificacao.notificar(cliente,
                "pedido confirmado, total R$ " + String.format(Locale.US, "%.2f", total));

        return agendamento + "\n" + aviso;
    }
}
