package org.example;

import java.util.Random;

public class SolucionaCrimeOtimizado {

    // Suspeitos, locais e armas
    private final static String[] suspeitos = {
            "Charles B. Abbage", "Donald Duck Knuth", "Ada L. Ovelace",
            "Alan T. Uring", "Ivar J. Acobson", "Ras Mus Ler Dorf"
    };

    private final static String[] locais = {
            "Redmond", "Palo Alto", "San Francisco", "Tokio",
            "Restaurante no Fim do Universo", "São Paulo", "Cupertino",
            "Helsinki", "Maida Vale", "Toronto"
    };

    private final static String[] armas = {
            "Peixeira", "DynaTAC 8000X", "Trezoitão", "Trebuchet",
            "Maça", "Gládio"
    };

    // Solução correta (pode ser configurada manualmente para testar)
    private final static int assassinoCorreto = 1; // Donald Duck Knuth
    private final static int localCorreto = 3;     // Tokio
    private final static int armaCorreta = 2;      // Trezoitão

    // Função que simula a resposta da testemunha
    private static int verificarTeoria(int suspeito, int local, int arma) {
        boolean acertouSuspeito = suspeito == assassinoCorreto;
        boolean acertouLocal = local == localCorreto;
        boolean acertouArma = arma == armaCorreta;
        if (acertouSuspeito && acertouLocal && acertouArma) {
            return 0; // Solução correta
        }

        Random aleatorio = new Random();
        if (!acertouSuspeito && !acertouLocal && !acertouArma) {
            return aleatorio.nextInt(3) + 1; // 1, 2 ou 3 (todos errados)
        }

        if (!acertouSuspeito && !acertouLocal) {
            return aleatorio.nextInt(2) + 1; // 1 ou 2 (assassino e local errados)
        }

        if (!acertouSuspeito && !acertouArma) {
            return aleatorio.nextInt(2) == 0 ? 1 : 3; // 1 ou 3 (assassino e arma errados)
        }

        if (!acertouLocal && !acertouArma) {
            return aleatorio.nextInt(2) + 2; // 2 ou 3 (local e arma errados)
        }

        if (!acertouSuspeito) return 1; // Assassino errado
        if (!acertouLocal) return 2;        // Local errado
        if (!acertouArma) return 3;          // Arma errada

        return -1; // Caso de erro inesperado
    }

    public static void main(String[] args) {
        boolean solucionado = false;
        int tentativas = 0;

        // Inicialmente, tentamos todas as combinações possíveis de suspeitos, locais e armas
        int[] suspeitosRestantes = new int[suspeitos.length];
        int[] locaisRestantes = new int[locais.length];
        int[] armasRestantes = new int[armas.length];

        // Preenchemos as listas de suspeitos, locais e armas possíveis
        for (int i = 0; i < suspeitos.length; i++) suspeitosRestantes[i] = i;
        for (int i = 0; i < locais.length; i++) locaisRestantes[i] = i;
        for (int i = 0; i < armas.length; i++) armasRestantes[i] = i;

        int suspeitoAtual, localAtual, armaAtual;

        while (!solucionado) {
            tentativas++;

            // Selecionamos a teoria atual com base nas opções restantes
            suspeitoAtual = suspeitosRestantes[0];
            localAtual = locaisRestantes[0];
            armaAtual = armasRestantes[0];

            System.out.println("Tentativa #" + tentativas + ": " +
                    "Suspeito: " + suspeitos[suspeitoAtual] + ", Local: " + locais[localAtual] + ", Arma: " + armas[armaAtual]);

            int resposta = verificarTeoria(suspeitoAtual, localAtual, armaAtual);

            if (resposta == 0) {
                System.out.println("Caso resolvido! O assassino é " + suspeitos[suspeitoAtual] +
                        " com a arma " + armas[armaAtual] + " no local " + locais[localAtual] + ".");
                solucionado = true;
            } else {
                System.out.println("Teoria incorreta, retorno: " + resposta);

                // Ajuste de acordo com o feedback da testemunha
                if (resposta == 1) {
                    // Suspeito incorreto, removemos ele da lista de suspeitos restantes
                    suspeitosRestantes = removerElemento(suspeitosRestantes, suspeitoAtual);
                } else if (resposta == 2) {
                    // Local incorreto, removemos ele da lista de locais restantes
                    locaisRestantes = removerElemento(locaisRestantes, localAtual);
                } else if (resposta == 3) {
                    // Arma incorreta, removemos ela da lista de armas restantes
                    armasRestantes = removerElemento(armasRestantes, armaAtual);
                }
            }
        }

        System.out.println("Número total de tentativas: " + tentativas);
    }

    // Função para remover um elemento de um array de inteiros
    private static int[] removerElemento(int[] array, int elemento) {
        int[] novoArray = new int[array.length - 1];
        int index = 0;
        for (int j : array) {
            if (j != elemento) {
                novoArray[index++] = j;
            }
        }
        return novoArray;
    }
}

