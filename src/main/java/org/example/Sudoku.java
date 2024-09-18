package org.example;

import java.util.HashSet;

public class Sudoku {

    // Função principal para validar o tabuleiro de Sudoku
    public static boolean validarSudoku(int[][] board) {
        // Criaremos uma lista para armazenar as posições das células incorretas
        StringBuilder celulasIncorretas = new StringBuilder();

        // Verificar cada linha
        for (int i = 0; i < 9; i++) {
            if (!validarLinha(board, i, celulasIncorretas)) {
                System.out.println("Erro na linha " + i);
            }
        }

        // Verificar cada coluna
        for (int i = 0; i < 9; i++) {
            if (!validarColuna(board, i, celulasIncorretas)) {
                System.out.println("Erro na coluna " + i);
            }
        }

        // Verificar cada sub-grade 3x3
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                if (!validarSubGrade(board, i, j, celulasIncorretas)) {
                    System.out.println("Erro na sub-grade começando em (" + i + "," + j + ")");
                }
            }
        }

        // Exibir as células incorretas, se houver
        if (celulasIncorretas.length() > 0) {
            System.out.println("Células incorretas: " + celulasIncorretas);
            return false;
        }

        return true;
    }

    // Verifica se a linha é válida
    private static boolean validarLinha(int[][] board, int linha, StringBuilder celulasIncorretas) {
        HashSet<Integer> conjunto = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            int valor = board[linha][i];
            if (valor < 1 || valor > 9 || !conjunto.add(valor)) {
                celulasIncorretas.append("(").append(linha).append(",").append(i).append(") ");
                return false;
            }
        }
        return true;
    }

    // Verifica se a coluna é válida
    private static boolean validarColuna(int[][] board, int coluna, StringBuilder celulasIncorretas) {
        HashSet<Integer> conjunto = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            int valor = board[i][coluna];
            if (valor < 1 || valor > 9 || !conjunto.add(valor)) {
                celulasIncorretas.append("(").append(i).append(",").append(coluna).append(") ");
                return false;
            }
        }
        return true;
    }

    // Verifica se uma sub-grade 3x3 é válida
    private static boolean validarSubGrade(int[][] board, int startRow, int startCol, StringBuilder celulasIncorretas) {
        HashSet<Integer> conjunto = new HashSet<>();
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                int valor = board[i][j];
                if (valor < 1 || valor > 9 || !conjunto.add(valor)) {
                    celulasIncorretas.append("(").append(i).append(",").append(j).append(") ");
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] board = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        if (validarSudoku(board)) {
            System.out.println("O Sudoku é válido.");
        } else {
            System.out.println("O Sudoku tem valores incorretos.");
        }
    }
}


