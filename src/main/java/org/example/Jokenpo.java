package org.example;

import java.util.Scanner;

public class Jokenpo {

    // Função que determina o resultado do jogo
    public static String determinarResultado(String jogador1, String jogador2) {
        if (jogador1.equalsIgnoreCase(jogador2)) {
            return "Empate!";
        }

        switch (jogador1.toLowerCase()) {
            case "pedra":
                if (jogador2.equalsIgnoreCase("tesoura")) {
                    return "Jogador 1 vence! Pedra quebra Tesoura.";
                } else if (jogador2.equalsIgnoreCase("papel")) {
                    return "Jogador 2 vence! Papel cobre Pedra.";
                }
                break;
            case "tesoura":
                if (jogador2.equalsIgnoreCase("papel")) {
                    return "Jogador 1 vence! Tesoura corta Papel.";
                } else if (jogador2.equalsIgnoreCase("pedra")) {
                    return "Jogador 2 vence! Pedra quebra Tesoura.";
                }
                break;
            case "papel":
                if (jogador2.equalsIgnoreCase("pedra")) {
                    return "Jogador 1 vence! Papel cobre Pedra.";
                } else if (jogador2.equalsIgnoreCase("tesoura")) {
                    return "Jogador 2 vence! Tesoura corta Papel.";
                }
                break;
        }

        return "Jogada inválida!";
    }

    // Função para obter uma jogada válida do jogador
    public static String obterJogadaValida(Scanner scanner, String jogador) {
        String jogada;
        while (true) {
            System.out.print(jogador + ", escolha (Pedra, Papel ou Tesoura): ");
            jogada = scanner.nextLine().trim();

            // Verifica se a jogada é válida
            if (jogada.equalsIgnoreCase("pedra") || jogada.equalsIgnoreCase("papel") || jogada.equalsIgnoreCase("tesoura")) {
                break;
            } else {
                System.out.println("Jogada inválida! Por favor, escolha entre Pedra, Papel ou Tesoura.");
            }
        }
        return jogada;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Obter jogada válida dos jogadores
        String jogada1 = obterJogadaValida(scanner, "Jogador 1");
        String jogada2 = obterJogadaValida(scanner, "Jogador 2");

        // Determinar e exibir o resultado
        String resultado = determinarResultado(jogada1, jogada2);
        System.out.println(resultado);

        scanner.close();
    }
}

