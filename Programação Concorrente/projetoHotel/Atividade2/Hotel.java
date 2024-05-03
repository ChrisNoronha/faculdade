import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Hotel {
    private List<Quarto> quartos;
    private BlockingQueue<Quarto> filaEspera;

    public Hotel(int numQuartos) {
        quartos = new ArrayList<>();
        filaEspera = new ArrayBlockingQueue<>(10);
        for (int i = 0; i < numQuartos; i++) {
            quartos.add(new Quarto(i));
        }
    }

    public synchronized Quarto alocaQuarto() throws InterruptedException {
        Quarto quarto = filaEspera.poll();
        if (quarto == null) {
            for (Quarto q : quartos) {
                if (q.estaVago()) {
                    return q;
                }
            }
            wait();
            return alocaQuarto();
        } else {
            return quarto;
        }
    }

    public synchronized void adicionaFilaEspera(Quarto quarto) {
        filaEspera.add(quarto);
        notifyAll();
    }
}
