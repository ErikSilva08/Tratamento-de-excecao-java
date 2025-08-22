import casasBahia.Sistema;
import excecoes.CodigoDuplicadoException;
import excecoes.PrecoInvalidoException;
import excecoes.ProdutoNaoEncontradoException;

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
                default:
                    break;
            }

        } while (opcao != 5);
        System.out.println("Saindo do Sistema!");
    }
}