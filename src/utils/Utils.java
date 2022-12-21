package utils;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import Producte.Producte;

public class Utils {
    public static Producte[] generateRandomInput(int n) {
        int[][] reaccions = new int[n][n];
        Producte[] productes = new Producte[n];

        // Generant taula reaccions aleatories
        Random r = new Random();
        int nReac = Math.floorDiv(n * n, 4); // numero de reaccions
        for (int i = 0; i < nReac; i++) {
            int col = r.nextInt(n);
            int row = r.nextInt(n);
            while (col == row || row < col || reaccions[col][row] == 1) {
                col = r.nextInt(n);
                row = r.nextInt(n);
            }
            reaccions[col][row] = 1;
        }

        for (int i = 0; i < productes.length; i++) {
            productes[i] = new Producte(i + 1);
        }

        for (int i = 0; i < productes.length; i++) {
            for (int j = i + 1; j < reaccions[i].length; j++) {
                if (reaccions[i][j] == 1) {
                    productes[i].addReaccio(productes[j]);
                    productes[j].addReaccio(productes[i]);
                }
            }
            System.out.println(productes[i].toString());
        }

        return productes;
    }

    public static void printTable(Producte[] productes) {
        int spacing = digits(productes.length);
        for (int ii = 0; ii < spacing; ii++)
            System.out.print(" ");
        for (int i = 0; i < productes.length; i++) { // Titols
            for (int ii = 0; ii < spacing + 1; ii++)
                System.out.print(" ");
            System.out.print("P" + productes[i].getID());
        }
        System.out.println();

        for (int i = 0; i < productes.length; i++) { // Titols
            System.out.print("P" + productes[i].getID());
            Producte p1 = productes[i];
            for (int ii = 0; ii < (digits(i + 1) * -1) + spacing; ii++)
                System.out.print(" ");
            for (int j = 0; j < productes.length; j++) { // Titols
                Producte p2 = productes[j];
                for (int ii = 0; ii < spacing + digits(j + 1); ii++)
                    System.out.print(" ");
                System.out.print(((p1.reacciona(p2) || p2.reacciona(p1)) ? "T" : "F") + " ");
            }
            System.out.println();
        }
    }

    public static void printSolucio(LinkedList<LinkedList<Producte>> solucio) {
        int size = solucio.size();
        System.out.println("La solució trobada necessita " + size + " compartiments.");
        System.out.println("L'assignació es la seguent: \n");
        for (int j = 0; j < size; j++) {
            LinkedList<Producte> mmm = solucio.get(j);
            for (int j2 = 0; j2 < mmm.size(); j2++) {
                System.out.println("Producte " + mmm.get(j2).getID() + " al compartiment " + (j + 1));
            }
        }
    }

    // retorna el numero de digits de un nombre
    public static int digits(int n) {
        return String.valueOf(n).length();
    }

    public static int readInt(String q, Scanner sc) {
        System.out.print(q + ": ");
        return sc.nextInt();
    }
}
