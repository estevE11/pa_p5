package Backtracking;

import Producte.Producte;

public class SolucioBacktracking {
    private Producte[] productes;
    private Producte[][] magatzem;

    public void solBack(int k){
        int valorAnt = 0;
        for (int i = 0; i< productes.length;i++){
           for (int j = 0;j<magatzem[i].length;++){
               if (!productes[k].reacciona(magatzem[i][j])){
                   magatzem[i][j+1]=productes[k];
                   solBack(k+1);
               }
           }
        }
    }
}
