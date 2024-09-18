package org.example;

import java.util.Scanner;

public class CaixaEletronico {

    // Notas disponíveis
    static int[] notas = {100, 50, 20, 10};

    // Função para calcular a quantidade mínima de notas para um saque
    public static void calcularNotas(int valorSaque) {
        if (valorSaque % 10 != 0) {
            System.out.println("Valor de saque inválido. Somente valores múltiplos de 10 são aceitos.");
            return;
        }

        System.out.println("Saque de R$ " + valorSaque + " entregue da seguinte forma:");

        for (int nota : notas) {
            int quantidadeNotas = valorSaque / nota; // Calcula quantas notas deste valor são necessárias
            if (quantidadeNotas > 0) {
                System.out.println(quantidadeNotas + " nota(s) de R$ " + nota);
                valorSaque -= quantidadeNotas * nota; // Atualiza o valor restante para o saque
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Entrada do valor do saque
        System.out.print("Informe o valor do saque: R$ ");
        int valorSaque = scanner.nextInt();

        // Chamada da função para calcular as notas
        calcularNotas(valorSaque);

        scanner.close();
    }
}

