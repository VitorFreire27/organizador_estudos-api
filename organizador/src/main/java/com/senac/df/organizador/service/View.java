package com.senac.df.organizador.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senac.df.organizador.domain.Usuario;
import com.senac.df.organizador.repositories.UsuarioRepository;

@Service
public class View {

	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private UsuarioService service;

	Scanner scanner = new Scanner(System.in);

	public void iniciar() {
		while (true) {
			exibirMenu();
			int opcao = scanner.nextInt();

			switch (opcao) {
			case 1:
				telaLogin();
				System.out.print("Email: ");
				scanner.nextLine();
				String email = scanner.nextLine();
				boolean emailExists = service.emailAlreadyExists(email);
				if (emailExists) {
					Integer id = service.getUserIdByEmail(email);
					Usuario user = service.findById(id);
					System.out.print("Senha: ");
					String senha = scanner.nextLine();
					if (senha.equals(user.getSenha())) {
						System.out.println("Você está logado!");
					} else {
						System.out.println("Senha incorreta.");
					}
				} else {
					System.out.println("Email não encontrado.");
				}
				break;
			case 2:
				telaCadastro();
				System.out.print("Email: ");
				scanner.nextLine();
				String novoEmail = scanner.nextLine();
				System.out.print("Senha: ");
				String novaSenha = scanner.nextLine();
				repository.save(new Usuario(null, novoEmail, novaSenha));
				System.out.println("Usuário cadastrado com sucesso!");
				break;
			case 3:
				System.out.println("Saindo da aplicação. Adeus!");
				scanner.close();
				return;
			default:
				System.out.println("Opção inválida. Tente novamente.");
			}
		}
	}

	private void exibirMenu() {
		System.out.println("===============");
		System.out.println("  MENU PRINCIPAL");
		System.out.println("===============");
		System.out.println("Selecione uma opção:");
		System.out.println("1 - Logar");
		System.out.println("2 - Registrar");
		System.out.println("3 - Sair");
		System.out.print("Digite o número da opção desejada: ");
	}

	private void telaLogin() {
		System.out.println("===============");
		System.out.println("  TELA DE LOGIN");
		System.out.println("===============");
	}

	private void telaCadastro() {
		System.out.println("=================");
		System.out.println("  TELA DE CADASTRO");
		System.out.println("=================");
	}
}
