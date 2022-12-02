import Producte.Producte;

public class Main {
    public static void main(String[] args) {
        Producte[] productes = new Producte[5];
        int[][] reaccions = {
                new int[] { 3, 4, 5 },
                new int[] { 3, 4 },
                new int[] { 1, 2 },
                new int[] { 1, 5, 2 },
                new int[] { 1, 4 }
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
    }
}