package casasBahia;

import java.util.ArrayList;
import java.util.Scanner;

import excecoes.CodigoDuplicadoException;
import excecoes.AplicarDescontoException;
import excecoes.DescontoInvalidoException;
import excecoes.PrecoInvalidoException;
import excecoes.ProdutoNaoEncontradoException;


public class Sistema {

	private ArrayList<Produto> produtos;

	public Sistema() {
		this.produtos = new ArrayList<Produto>();
	}

	public void adicionarProduto(Scanner scanner) throws PrecoInvalidoException, CodigoDuplicadoException {
		System.out.println("Digite o nome do produto: ");
		String nome = scanner.nextLine();
		System.out.println("Digite o código: ");
		String codigo = scanner.nextLine();

		for (Produto produto : produtos) {
			if (produto.getCodigo().equals(codigo)) {
				throw new CodigoDuplicadoException("Já existe um produto com o código " + codigo);
			}
		}

		System.out.println("Digite o preço do produto: ");
		Double preco = scanner.nextDouble();
		scanner.nextLine();

		if (preco <= 0) {
			throw new PrecoInvalidoException("O preço precisa ser maior que 0");
		}

		System.out.println("Qual o tipo do produto: ");
		System.out.println("1 - Móvel ");
		System.out.println("2 - Eletro ");

		int opcao = scanner.nextInt();
		scanner.nextLine();

		if (opcao == 1) {
			adicionarMovel(scanner, nome, codigo, preco);
		} else if (opcao == 2) {
			adicionarEletro(scanner, nome, codigo, preco);
		}
	}

	private void adicionarMovel(Scanner scanner, String nome, String codigo, double preco) {
		System.out.println("Digite o material:");
		String material = scanner.nextLine();
		
		System.out.println("Digite a categoria:");
		String categoria = scanner.nextLine();
		
		Movel movel = new Movel(codigo, nome, preco, categoria, material);
		produtos.add(movel);
		
		System.out.println("Produto adicionado");
		
	}
	
	private void adicionarEletro(Scanner scanner, String nome, String codigo, double preco) {
		CategoriaEletro categoriaEletro = null;
		System.out.println("Qual a categoria do eletrodomestico cadastrado?");
		System.out.println("1 - Cozinha");
		System.out.println("2 - Quarto");
		System.out.println("3 - Lavanderia");

		int opcaoCategoria = scanner.nextInt();
		scanner.nextLine();
		if (opcaoCategoria == 1) {
			categoriaEletro = CategoriaEletro.COZINHA;
		} else if (opcaoCategoria == 2) {
			categoriaEletro = CategoriaEletro.QUARTO;
		} else if (opcaoCategoria == 3) {
			categoriaEletro = CategoriaEletro.LAVANDERIA;
		}
		System.out.println("Digite a voltagem");
		int voltagem = scanner.nextInt();
		scanner.nextLine();
		Eletrodomestico eletro = new Eletrodomestico(codigo, nome, preco, categoriaEletro, voltagem);
		produtos.add(eletro);
		System.out.println("Produto adicionado");

	}

	public void listarProdutos() {
		if (produtos.size() == 0) {
			System.out.println("Nenhum produto cadastrado!");
		} else {
			for (Produto produto : produtos) {
				System.out.println(produto);
			}
		}

	}
	
	
	public Produto buscarProduto(Scanner scanner) {
		System.out.println("Digite o código procurado:");
		String codigoProcurado = scanner.nextLine();
		
		for (Produto produto : produtos) {
			if(produto.getCodigo().equals(codigoProcurado)) {
				System.out.println("Produto encontrado!");
				System.out.println(produto);
				return produto;
			}
		}
		System.out.println("Produto não encontrado!");
		return null;
	}

	public void removerProduto(Scanner scanner) throws ProdutoNaoEncontradoException {
		System.out.println("Digite o código do produto: ");
		String codigoProcurado = scanner.nextLine();

		for (int i = 0; i < produtos.size(); i++) {
			if (produtos.get(i).getCodigo().equals(codigoProcurado)) {
				produtos.remove(i);
				System.out.println("Produto removido!");
				return;
			}
		}
		throw new ProdutoNaoEncontradoException("Produto com código " + codigoProcurado + " não encontrado!");
	}
	public void AplicarDesconto(Produto produto, double desconto) throws DescontoInvalidoException {
		if (desconto < 0 || desconto > 0.5) {
			throw new DescontoInvalidoException("Desconto inválido: " + (desconto * 100) + "%");
		}

		double novoPreco = produto.getPreco() - (produto.getPreco() * desconto);
		produto.setPreco(novoPreco);

		System.out.println("Desconto de " + (desconto * 100) + "% aplicado com sucesso!");
		System.out.println("Novo preço: R$ " + novoPreco);
	}


}



