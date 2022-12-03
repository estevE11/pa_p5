package Backtracking;

import java.util.Iterator;
import java.util.LinkedList;

import javax.security.auth.callback.CallbackHandler;

import Producte.Producte;
import sun.awt.image.ImageWatched;

public class SolucioBacktracking {
    private Producte[] productes;
    private LinkedList<LinkedList<Producte>> magatzem;
    
    private LinkedList<LinkedList<Producte>> magatzem_millor;
    private int size_millor = 30000;

    public SolucioBacktracking(Producte[] productes) {
        this.productes = productes;
        this.magatzem = new LinkedList<LinkedList<Producte>>();
        this.magatzem.add(new LinkedList<Producte>());
    }

    public void solBack(int k) {
        //System.out.println("> " + productes[k].getID());
        //int valorAnt = 0;
        boolean entrado = false;
        int index_de_caja = -1;
        boolean caja_creada = false;
        // No podem utilitzar un iterador ja que la dimensió de
        // la llista enllaçada varia, i al variar el iterador
        // retorna una exepció
        for (int i = 0; i < magatzem.size(); i++) {
            //System.out.println("iteracion: k=" + k + " i=" + i);
            LinkedList<Producte> m = magatzem.get(i);

            if (m.size() == 0) {
                m.add(this.productes[k]);
                //System.out.println("1metido P" + this.productes[k].getID() + " en caja" + (i+1));
                index_de_caja = i;
                entrado = true;
            } else {
                if (this.acceptable(k, m)/* && !m.contains()*/) {
                    m.add(this.productes[k]);
                    //System.out.println("2metido P" + this.productes[k].getID() + " en caja" + (i+1));
                    index_de_caja = i;
                    entrado = true;
                }
            }

            if (i == magatzem.size() - 1 && !entrado) {
                LinkedList<Producte> new_m = new LinkedList<Producte>();
                this.magatzem.add(new_m);
                //System.out.println("3metido P" + this.productes[k].getID() + " en caja" + (i+2));
                new_m.add(this.productes[k]);
                index_de_caja = i+1;
                entrado = true;
                caja_creada = true;
            }

            if (entrado) {
                //System.out.println("k=" + k + ", P" + this.productes[k].getID() + ", k=" + (this.productes.length-1) + "?");
                if (k != this.productes.length - 1) {
                    solBack(k + 1);
                } else {
                    int mag_size = magatzem.size();
                    if (mag_size < this.size_millor) {
                        this.size_millor = mag_size;
                        this.setMillorSolucio();
                    }
                }
                // deshacer
                // (borrar el producto q acabamos de meter, de donde lo hemos puesto)
                // - Quitar el producto de la caja en la que se ha metido
                // - Si se ha creado una caja, borrarlas
                //System.out.println("elim: borrando P" + productes[k].getID() + " de caja" + (index_de_caja+1));
                this.borrar(k, index_de_caja, caja_creada);
            }
            entrado = false;
        }
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

    private void borrar(int k, int i_caja, boolean creada) {
        LinkedList<Producte> caja = this.magatzem.get(i_caja);
        caja.remove(this.productes[k]);
        if (creada)
            this.magatzem.removeLast();
    }

    private void setMillorSolucio() {
        this.magatzem_millor = new LinkedList<LinkedList<Producte>>();
        Iterator<LinkedList<Producte>> it = this.magatzem.iterator();
        while (it.hasNext()) {
            LinkedList<Producte> p = it.next();
            this.magatzem_millor.add((LinkedList<Producte>) p.clone());
        }
    }

    public LinkedList<LinkedList<Producte>> getSolució() {
        return this.magatzem_millor;
    }
}
