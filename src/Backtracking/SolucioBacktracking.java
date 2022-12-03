package Backtracking;

import java.util.LinkedList;

import javax.security.auth.callback.CallbackHandler;

import Producte.Producte;

public class SolucioBacktracking {
    private Producte[] productes;
    private LinkedList<LinkedList<Producte>> magatzem;

    public SolucioBacktracking(Producte[] productes) {
        this.productes = productes;
        this.magatzem = new LinkedList<LinkedList<Producte>>();
        this.magatzem.add(new LinkedList<Producte>());
    }

    public void solBack(int k) {
        //int valorAnt = 0;
        System.out.println("k = " + this.productes[k].getID());
        boolean entrado = false;
        int index_de_caja = -1;
        boolean caja_creada = false;
        for (int i = 0; i < magatzem.size(); i++) {
            LinkedList<Producte> m = magatzem.get(i);

            if (m.size() == 0) {
                m.add(this.productes[k]);
                index_de_caja = i;
                entrado = true;
            } else {
                if (this.acceptable(k, m)) {
                    m.add(this.productes[k]);
                    index_de_caja = i;
                    entrado = true;
                }
            }

            if (i == magatzem.size() - 1 && !entrado) {
                LinkedList<Producte> new_m = new LinkedList<Producte>();
                this.magatzem.add(new_m);
                new_m.add(this.productes[k]);
                index_de_caja = i+1;
                entrado = true;
                caja_creada = true;
            }

            if (entrado) {                
                if (k != this.productes.length - 1) {
                    solBack(k + 1);
                } else {
                    System.out.println("Solució trobada! " + magatzem.size());
                    return;
                }
            }
        }
        // deshacer
        // (borrar el producto q acabamos de meter, de donde lo hemos puesto)
        // - Quitar el producto de la caja en la que se ha metido
        // - Si se ha creado una caja, borrarla
        this.magatzem.get(index_de_caja).remove(this.productes[k]);
        if(caja_creada)
            this.magatzem.removeLast();

    }
    
    private boolean acceptable(int k, LinkedList<Producte> m) {
        boolean val = true;
        for (int j = 0; j < m.size() && val; j++) {
            if (productes[k].reacciona(m.get(j))) {
                val = false;
            }
        }
        return val;
    }
}
