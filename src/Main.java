import Backtracking.SolucioBack;
import Greedy.SolucioVoraç;
import Producte.Producte;

import java.util.LinkedList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Producte[] productes = generateRandomInput(20);

        System.out.println("\nMatriu d'adjecència: ");
        printTable(productes);

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        SolucioBack solBack = new SolucioBack(productes);
        SolucioVoraç solVor = new SolucioVoraç(productes);

        long t_sol1 = 0;
        long t_sol2 = 0;

        long start = System.currentTimeMillis();
        solBack.solBack(0);
        long end = System.currentTimeMillis();
        t_sol1 = end-start;

        start = System.currentTimeMillis();
        solVor.solVor();
        end = System.currentTimeMillis();
        t_sol2 = end-start;

        LinkedList<LinkedList<Producte>> solucio1 = solBack.getSolucio();
        LinkedList<LinkedList<Producte>> solucio2 = solVor.getSolucio();

        printSolucio(solucio1);
        System.out.println("Temps: " + t_sol1 + "ms");
        System.out.println("");
        System.out.println("");
        printSolucio(solucio2);
        System.out.println("Temps: " + t_sol2 + "ms");

    }

    private static Producte[] generateRandomInput(int n) {
        int[][] reaccions = new int[n][n];
        Producte[] productes = new Producte[n];

        // Generant taula reaccions aleatories
        Random r = new Random();
        int nReac = Math.floorDiv(n*n, 4); // numero de reaccions
        for(int i = 0; i < nReac; i++) {
            int col = r.nextInt(n);
            int row = r.nextInt(n);
            while(col == row || row < col || reaccions[col][row] == 1) {
                col = r.nextInt(n);
                row = r.nextInt(n);
            }
            reaccions[col][row] = 1;
        }

        for (int i = 0; i < productes.length; i++) {
            productes[i] = new Producte(i + 1);
        }

        for (int i = 0; i < productes.length; i++) {
            for (int j = i+1; j < reaccions[i].length; j++) {
                if(reaccions[i][j] == 1) {
                    productes[i].addReaccio(productes[j]);
                    productes[j].addReaccio(productes[i]);
                }
            }
            System.out.println(productes[i].toString());
        }

        return productes;
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

    private static void printSolucio(LinkedList<LinkedList<Producte>> solucio) {
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
}