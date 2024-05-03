import java.util.ArrayList;
import java.util.List;

public class Quarto {
    private int numero;
    private List<Hospede> hospedes;
    private boolean chaveNaRecepcao;

    public Quarto(int numero) {
        this.numero = numero;
        this.hospedes = new ArrayList<>();
        this.chaveNaRecepcao = true;
    }

    public synchronized boolean estaVago() {
        return hospedes.isEmpty() && chaveNaRecepcao;
    }

    // Métodos para adicionar/remover hóspedes e gerenciar a chave na recepção
}
