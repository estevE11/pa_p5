import Backtracking.SolucioBacktracking;
import Producte.Producte;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Producte[] productes = new Producte[5];
        int[][] reaccions = {
                new int[] { 2, 3 },
                new int[] { 1, 5 },
                new int[] { 1, 4 },
                new int[] { 5},
                new int[] { 2 }
        };

        for (int i = 0; i < productes.length; i++) {
            productes[i] = new Producte(i + 1);
        }

        for (int i = 0; i < productes.length; i++) {
            for (int j = 0; j < reaccions[i].length; j++) {
                productes[i].addReaccio(productes[reaccions[i][j] - 1]);
            }
            System.out.println(productes[i].toString());
        }

        System.out.println("\nMatriu d'adjecència: ");
        printTable(productes);

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        SolucioBacktracking sol = new SolucioBacktracking(productes);
        sol.solBack(0);

        LinkedList<LinkedList<Producte>> solucio = sol.getSolucio();

        int size = solucio.size();
        System.out.println("La solució trobada necessita " +  size + " compartiments.");
        System.out.println("L'assignació es la seguent: \n");
        for (int j = 0; j < size; j++) {
            LinkedList<Producte> mmm = solucio.get(j);
            for (int j2 = 0; j2 < mmm.size(); j2++) {
                System.out.println("Producte " + mmm.get(j2).getID() + " al compartiment " + (j+1));
            }
        }

    }

    private static void printTable(Producte[] productes) {
        System.out.print("   ");
        for (int i = 0; i < productes.length; i++) { // Titols
            System.out.print("P" + productes[i].getID() + " ");
        }
        System.out.println();
        for (int i = 0; i < productes.length; i++) { // Titols
            System.out.print("P" + productes[i].getID() + " ");
            Producte p1 = productes[i];
            for (int j = 0; j < productes.length; j++) { // Titols
                Producte p2 = productes[j];
                System.out.print(" " + ((p1.reacciona(p2) || p2.reacciona(p1)) ? "T" : "F") + " ");
            }
            System.out.println();
        }
    }
}