package Backtracking;

import java.util.Iterator;
import java.util.LinkedList;

import Producte.Producte;

public class SolucioBack {
    private Producte[] productes;
    private LinkedList<LinkedList<Producte>> magatzem;
    
    private LinkedList<LinkedList<Producte>> magatzem_millor;
    private int size_millor = 30000;

    public SolucioBack(Producte[] productes) {
        this.productes = productes;
        this.magatzem = new LinkedList<LinkedList<Producte>>();
        this.magatzem.add(new LinkedList<Producte>());
    }

    public void solBack(int k) {
        boolean added = false;
        int i_compartment = -1;
        boolean compatment_created = false;
        // No podem utilitzar un iterador ja que la dimensió de
        // la llista enllaçada varia, i al variar el iterador
        // retorna una exepció
        for (int i = 0; i < magatzem.size(); i++) {
            LinkedList<Producte> m = magatzem.get(i);

            if (m.size() == 0) {
                m.add(this.productes[k]);
                i_compartment = i;
                added = true;
            } else {
                if (this.acceptable(k, m)) {
                    m.add(this.productes[k]);
                    i_compartment = i;
                    added = true;
                }
            }

            if (i == magatzem.size() - 1 && !added) {
                this.addCompartment(k);
                i_compartment = i+1;
                added = true;
                compatment_created = true;
            }

            if (added) {
                if (k != this.productes.length - 1) {
                    solBack(k + 1);
                } else {
                    int mag_size = magatzem.size();
                    if (mag_size < this.size_millor) {
                        this.size_millor = mag_size;
                        this.setMillorSolucio();
                    }
                }
                this.borrar(k, i_compartment, compatment_created);
            }
            added = false;
        }
    }
    
    private void addCompartment(int k) {
        LinkedList<Producte> new_m = new LinkedList<Producte>();
        this.magatzem.add(new_m);
        new_m.add(this.productes[k]);
    }

    private boolean acceptable(int k, LinkedList<Producte> m) {
        boolean val = true;
        Iterator<Producte> it = m.listIterator();
        while (it.hasNext() && val) {
            if (productes[k].reacciona(it.next())) {
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

    public LinkedList<LinkedList<Producte>> getSolucio() {
        return this.magatzem_millor;
    }
}
