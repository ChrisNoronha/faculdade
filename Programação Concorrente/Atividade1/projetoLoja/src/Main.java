import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Criar banco
        Banco banco = new Banco();
        // Criar instâncias de FuncionarioImpl

        // Criar contas para as lojas
        Conta contaLoja1 = new Conta("Loja1", 0);
        Conta contaLoja2 = new Conta("Loja2", 0);

        // Criar lojas
        Loja loja1 = new Loja(contaLoja1, new ArrayList<>());
        Loja loja2 = new Loja(contaLoja2, new ArrayList<>());

        // Criar contas para os funcionários
        Conta contaSalario1 = new Conta("Salario1", 0);
        Conta contaInvestimento1 = new Conta("Investimento1", 0);
        Conta contaSalario2 = new Conta("Salario2", 0);
        Conta contaInvestimento2 = new Conta("Investimento2", 0);

        // Criar funcionários
        FuncionarioImpl funcionario1 = new FuncionarioImpl(contaSalario1, contaInvestimento1);
        FuncionarioImpl funcionario2 = new FuncionarioImpl(contaSalario2, contaInvestimento2);
        FuncionarioImpl funcionario3 = new FuncionarioImpl(contaSalario1, contaInvestimento1);
        FuncionarioImpl funcionario4 = new FuncionarioImpl(contaSalario2, contaInvestimento2);

        // Adicionar funcionários às lojas
        loja1.adicionarFuncionario(funcionario1);
        loja1.adicionarFuncionario(funcionario3);
        loja2.adicionarFuncionario(funcionario2);
        loja2.adicionarFuncionario(funcionario4);

        // Iniciar as threads dos funcionários
        funcionario1.start();
        funcionario2.start();
        funcionario3.start();
        funcionario4.start();

        // Criar contas para os clientes e iniciar as threads dos clientes
        for (int i = 1; i <= 5; i++) {
            Conta contaCliente = new Conta("Cliente" + i, 1000);
            Cliente cliente = new Cliente(contaCliente, banco, loja1, loja2);
            cliente.start();
        }

        // Iniciar as threads dos funcionários
        funcionario1.start();
        funcionario2.start();
        funcionario3.start();
        funcionario4.start();

        // Simular pagamento dos funcionários pelas lojas
        loja1.pagarFuncionarios();
        loja2.pagarFuncionarios();
    }
}
