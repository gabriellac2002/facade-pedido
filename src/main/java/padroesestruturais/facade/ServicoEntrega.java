package padroesestruturais.facade;

public class ServicoEntrega {

    public double calcularFrete(String cep) {
        return cep.startsWith("0") ? 15.0 : 25.0;
    }

    public String agendar(String produto, String cep) {
        return "Entrega de " + produto + " agendada para o CEP " + cep;
    }
}
