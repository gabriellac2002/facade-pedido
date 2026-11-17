package padroesestruturais.facade;

public class ServicoPagamento {

    public boolean processar(String cliente, double valor) {
        return valor > 0;
    }
}
