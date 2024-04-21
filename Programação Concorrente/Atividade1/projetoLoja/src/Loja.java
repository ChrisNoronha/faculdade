import java.util.List;

public class Loja {
    private Conta contaLoja;
    private List<Funcionario> funcionarios;
    private Banco banco;

    public Loja(Conta contaLoja, List<Funcionario> funcionarios, Banco banco) {
        this.contaLoja = contaLoja;
        this.funcionarios = funcionarios;
        this.banco = banco;
    }

    public Conta getConta() {
        return contaLoja;
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    public void verificarEfetuarPagamento(double valor) {
        // Lógica para verificar se o pagamento pode ser efetuado
    }

    public void pagarFuncionarios() {
        double totalSalarios = 0;

        // Calcular total de salários dos funcionários
        for (Funcionario funcionario : funcionarios) {
            totalSalarios += funcionario.calcularSalario();
        }

        // Verificar se há fundos suficientes na loja para efetuar os pagamentos
        if (totalSalarios >= 1400) {
            // Transferir salários para contas dos funcionários
            for (Funcionario funcionario : funcionarios) {
                double salarioFuncionario = funcionario.calcularSalario();
                banco.transferencia(contaLoja, funcionario.getContaSalario(), salarioFuncionario);
                System.out.println(
                        "Pagamento de salário realizado para funcionário: " + funcionario.getContaSalario().getId() +
                                " da loja: " + contaLoja.getId());
                System.out.println("\n");
            }

            // Transferir 20% do saldo das contas salário para as contas investimento
            for (Funcionario funcionario : funcionarios) {
                double saldoSalario = funcionario.getContaSalario().getSaldo();
                double valorTransferencia = saldoSalario * 0.20; // 20% do saldo
                banco.transferencia(funcionario.getContaSalario(), funcionario.getContaInvestimento(),
                        valorTransferencia);
            }
            System.out.println("Pagamento de salários realizado pela loja: " + contaLoja.getId());
            System.out.println("\n");
        }
    }

}
