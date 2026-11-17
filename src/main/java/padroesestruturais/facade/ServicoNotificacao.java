package padroesestruturais.facade;

public class ServicoNotificacao {

    public String notificar(String cliente, String mensagem) {
        return "Notificacao para " + cliente + ": " + mensagem;
    }
}
