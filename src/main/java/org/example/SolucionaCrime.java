package org.example;

import java.util.Random;

public class SolucionaCrime {

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

        for (int suspeito = 0; suspeito < suspeitos.length; suspeito++) {
            for (int local = 0; local < locais.length; local++) {
                for (int arma = 0; arma < armas.length; arma++) {
                    tentativas++;
                    System.out.println("Tentativa #" + tentativas + ": " +
                            "Suspeito: " + suspeitos[suspeito] + ", Local: " + locais[local] + ", Arma: " + armas[arma]);

                    int resposta = verificarTeoria(suspeito, local, arma);

                    if (resposta == 0) {
                        System.out.println("Caso resolvido! O assassino é " + suspeitos[suspeito] +
                                " com a arma " + armas[arma] + " no local " + locais[local] + ".");
                        solucionado = true;
                        break;
                    } else {
                        System.out.println("Teoria incorreta, retorno: " + resposta);
                    }
                }
                if (solucionado) break;
            }
            if (solucionado) break;
        }

        System.out.println("Número total de tentativas: " + tentativas);
    }
}

