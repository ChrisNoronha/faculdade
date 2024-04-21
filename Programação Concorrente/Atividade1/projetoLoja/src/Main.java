import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        // Criar um objeto Lock
        Lock lock = new ReentrantLock();

        // Criar banco
        Banco banco = new Banco(lock);

        // Criar contas para as lojas
        Conta contaLoja1 = new Conta("Loja AAA", 0);
        Conta contaLoja2 = new Conta("Loja ZZZ", 0);

        // Criar contas para os funcionários da loja 1
        Conta contaSalario1 = new Conta("primeiro funcionário", 0);
        Conta contaInvestimento1 = new Conta("Investimento do primeiro funcionário", 0);
        Conta contaSalario2 = new Conta("segundo funcionário", 0);
        Conta contaInvestimento2 = new Conta("Investimento do segundo funcuinário", 0);

        // Criar funcionários da loja 1
        FuncionarioImpl funcionario1 = new FuncionarioImpl(contaSalario1, contaInvestimento1);
        FuncionarioImpl funcionario2 = new FuncionarioImpl(contaSalario2, contaInvestimento2);

        // Criar contas para os funcionários da loja 2
        Conta contaSalario3 = new Conta("primeiro funcionário", 0);
        Conta contaInvestimento3 = new Conta("Investimento do primeiro funcionário", 0);
        Conta contaSalario4 = new Conta("segundo funcionário", 0);
        Conta contaInvestimento4 = new Conta("Investimento do segundo funcionário", 0);

        // Criar funcionários da loja 2
        FuncionarioImpl funcionario3 = new FuncionarioImpl(contaSalario3, contaInvestimento3);
        FuncionarioImpl funcionario4 = new FuncionarioImpl(contaSalario4, contaInvestimento4);

        // Criar listas de funcionários para as lojas
        List<Funcionario> funcionariosLoja1 = new ArrayList<>();
        List<Funcionario> funcionariosLoja2 = new ArrayList<>();

        // Adicionar funcionários às lojas
        funcionariosLoja1.add(funcionario1);
        funcionariosLoja1.add(funcionario2);
        funcionariosLoja2.add(funcionario3);
        funcionariosLoja2.add(funcionario4);

        // Criar lojas
        Loja loja1 = new Loja(contaLoja1, funcionariosLoja1, banco);
        Loja loja2 = new Loja(contaLoja2, funcionariosLoja2, banco);

        // Criar e iniciar threads dos funcionários
        funcionario1.start();
        funcionario2.start();
        funcionario3.start();
        funcionario4.start();

        // Criar e iniciar threads dos clientes
        for (int i = 1; i <= 5; i++) {
            Conta contaCliente = new Conta("Cliente" + i, 1000);
            Cliente cliente = new Cliente(contaCliente, banco, loja1, loja2);
            cliente.start();
        }

        // Esperar que todas as threads dos clientes terminem
        try {
            Thread.sleep(20000); // Espera tempo suficiente para que todas as compras ocorram
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Simular pagamento dos funcionários pelas lojas
        loja1.pagarFuncionarios();
        System.out.println("\n"); // Adiciona duas linhas de espaço);
        loja2.pagarFuncionarios();
        System.out.println("\n"); // Adiciona duas linhas de espaço);

        System.out.println("Valor monetário restante nas lojas.");

        // Imprimir saldo das lojas
        System.out.println("\n"); // Adiciona duas linhas de espaço);
        System.out.println("Valor monetário na loja 1: " + loja1.getConta().getSaldo());
        System.out.println("Valor monetário na loja 2: " + loja2.getConta().getSaldo());
        System.out.println("\n"); // Adiciona duas linhas de espaço);
        // Imprimir saldo dos clientes
        System.out.println("Saldo restante dos clientes:\n");
        for (int i = 1; i <= 5; i++) {
            Conta contaCliente = new Conta("Cliente" + i, 000);
            System.out.println("Saldo do cliente " + contaCliente.getId() + ": " + contaCliente.getSaldo());
        }
        System.out.println("\n"); // Adiciona duas linhas de espaço);
        // Imprimir saldo e investimento de cada funcionário
        System.out.println("Saldo e investimento de cada funcionário:\n");

        for (Funcionario funcionario : funcionariosLoja1) {
            System.out.println("Funcionário na loja 1 - ID: " + funcionario.getContaSalario().getId() +
                    " - Saldo: " + funcionario.getContaSalario().getSaldo() +
                    " - Investimento: " + funcionario.getContaInvestimento().getSaldo());
        }

        for (Funcionario funcionario : funcionariosLoja2) {
            System.out.println("Funcionário na loja 2 - ID: " + funcionario.getContaSalario().getId() +
                    " - Saldo: " + funcionario.getContaSalario().getSaldo() +
                    " - Investimento: " + funcionario.getContaInvestimento().getSaldo());
        }

    }
}
