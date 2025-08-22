import casasBahia.Produto;
import casasBahia.Sistema;
import excecoes.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        Scanner scan = new Scanner(System.in);

        int opcao;

        do {
            System.out.println("---Menu----");
            System.out.println("1- Adicionar");
            System.out.println("2- Listar");
            System.out.println("3- Buscar");
            System.out.println("4- Remover");
            System.out.println("5- Aplicar desconto");
            System.out.print("Digite sua opção: ");
            opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao) {
                case 1:
                    try {
                        sistema.adicionarProduto(scan);
                    } catch (PrecoInvalidoException | CodigoDuplicadoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    sistema.listarProdutos();
                    break;
                case 3:
                    sistema.buscarProduto(scan);
                    break;
                case 4:
                    try {
                        sistema.removerProduto(scan);
                    } catch (ProdutoNaoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        System.out.println("Digite o código do produto para aplicar desconto:");
                        Produto produto = sistema.buscarProduto(scan);

                        System.out.println("Produto encontrado: " + produto);
                        System.out.println("Preço atual: R$ " + produto.getPreco());

                        System.out.print("Digite o desconto (0 a 0.5): ");
                        double desconto = scan.nextDouble();
                        scan.nextLine();
                        sistema.AplicarDesconto(produto, desconto);

                    } catch (DescontoInvalidoException e) {
                        System.out.println(e.getMessage());
                    }
                default:
                    break;
            }

        } while (opcao != 6);
        System.out.println("Saindo do Sistema!");
    }
}