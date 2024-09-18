package org.example;

import java.util.Random;

public class SolucionaCrimePorEliminacao {

    // Suspeitos, locais e armas
    private static final String[] suspeitos = {
            "Charles B. Abbage", "Donald Duck Knuth", "Ada L. Ovelace",
            "Alan T. Uring", "Ivar J. Acobson", "Ras Mus Ler Dorf"
    };

    private static final String[] locais = {
            "Redmond", "Palo Alto", "San Francisco", "Tokio",
            "Restaurante no Fim do Universo", "São Paulo", "Cupertino",
            "Helsinki", "Maida Vale", "Toronto"
    };

    private static final String[] armas = {
            "Peixeira", "DynaTAC 8000X", "Trezoitão", "Trebuchet",
            "Maça", "Gládio"
    };

    // Solução correta (pode ser configurada manualmente para testar)
    private static final int assassinoCorreto = 1; // Donald Duck Knuth
    private static final int localCorreto = 3;     // Tokio
    private static final int armaCorreta = 2;      // Trezoitão

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

        // Arrays booleanos para manter o rastreamento de quais suspeitos, locais e armas já foram eliminados
        boolean[] suspeitosEliminados = new boolean[suspeitos.length];
        boolean[] locaisEliminados = new boolean[locais.length];
        boolean[] armasEliminadas = new boolean[armas.length];

        int suspeitoAtual = -1, localAtual = -1, armaAtual = -1;

        while (!solucionado) {
            tentativas++;

            // Escolher um suspeito que ainda não foi eliminado
            for (int i = 0; i < suspeitos.length; i++) {
                if (!suspeitosEliminados[i]) {
                    suspeitoAtual = i;
                    break;
                }
            }

            // Escolher um local que ainda não foi eliminado
            for (int i = 0; i < locais.length; i++) {
                if (!locaisEliminados[i]) {
                    localAtual = i;
                    break;
                }
            }

            // Escolher uma arma que ainda não foi eliminada
            for (int i = 0; i < armas.length; i++) {
                if (!armasEliminadas[i]) {
                    armaAtual = i;
                    break;
                }
            }

            System.out.println("Tentativa #" + tentativas + ": " +
                    "Suspeito: " + suspeitos[suspeitoAtual] + ", Local: " + locais[localAtual] + ", Arma: " + armas[armaAtual]);

            int resposta = verificarTeoria(suspeitoAtual, localAtual, armaAtual);

            if (resposta == 0) {
                System.out.println("Caso resolvido! O assassino é " + suspeitos[suspeitoAtual] +
                        " com a arma " + armas[armaAtual] + " no local " + locais[localAtual] + ".");
                solucionado = true;
            } else {
                System.out.println("Teoria incorreta, retorno: " + resposta);

                // Eliminar a opção incorreta com base no feedback da testemunha
                if (resposta == 1) {
                    // Assassino incorreto
                    suspeitosEliminados[suspeitoAtual] = true;
                } else if (resposta == 2) {
                    // Local incorreto
                    locaisEliminados[localAtual] = true;
                } else if (resposta == 3) {
                    // Arma incorreta
                    armasEliminadas[armaAtual] = true;
                }
            }
        }

        System.out.println("Número total de tentativas: " + tentativas);
    }
}

