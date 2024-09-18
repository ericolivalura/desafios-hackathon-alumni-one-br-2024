package org.example;

import java.util.HashMap;

public class ProblemaCollatz {

    // Mapa para armazenar os comprimentos das sequências já calculadas
    static HashMap<Long, Integer> cache = new HashMap<>();

    // Função que calcula o tamanho da sequência de Collatz para um dado número
    public static int calcularTamanhoSequencia(long n) {
        // Caso base: se a sequência já chegou a 1
        if (n == 1) {
            return 1;
        }

        // Se já calculamos o comprimento para este número, retornamos do cache
        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        // Variável para armazenar o comprimento da sequência
        int tamanho;

        // Regras de Collatz
        if (n % 2 == 0) {
            tamanho = 1 + calcularTamanhoSequencia(n / 2);
        } else {
            tamanho = 1 + calcularTamanhoSequencia(3 * n + 1);
        }

        // Armazenar o comprimento da sequência no cache
        cache.put(n, tamanho);

        return tamanho;
    }

    public static void main(String[] args) {
        int maiorTamanho = 0;
        int numeroComMaiorSequencia = 0;

        // Percorre todos os números de 1 a 1 milhão
        for (int i = 1; i <= 1000000; i++) {
            int tamanhoSequencia = calcularTamanhoSequencia(i);

            // Verifica se a sequência atual é a maior
            if (tamanhoSequencia > maiorTamanho) {
                maiorTamanho = tamanhoSequencia;
                numeroComMaiorSequencia = i;
            }
        }

        // Exibe o número que produz a maior sequência e o tamanho dessa sequência
        System.out.println("Número com a maior sequência: " + numeroComMaiorSequencia);
        System.out.println("Tamanho da maior sequência: " + maiorTamanho);
    }
}

