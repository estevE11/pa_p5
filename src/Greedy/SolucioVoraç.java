package Greedy;

import java.util.Iterator;
import java.util.LinkedList;

import Producte.Producte;

public class SolucioVoraç {
    private Producte[] productes;
    private boolean[] tracked;
    private LinkedList<LinkedList<Producte>> magatzem;

    public SolucioVoraç(Producte[] productes) {
        this.productes = productes;
        this.tracked = new boolean[this.productes.length];
        for (int i = 0; i < this.tracked.length; i++) {
            this.tracked[i] = false;
        }
        this.magatzem = new LinkedList<LinkedList<Producte>>();
        this.magatzem.add(new LinkedList<Producte>());
    }
    
    public void solVor() {
        // la idea igual seria ordenar los productos
        // de los q tienen menos reacciones a los q mas

        Producte candidat;
        while ((candidat = this.getNextBest()) != null) {
            boolean added = false;
            for (int i = 0; i < magatzem.size() && !added; i++) {
                LinkedList<Producte> m = magatzem.get(i);

                if (m.size() == 0) {
                    m.add(candidat);
                    added = true;
                } else {
                    if (this.acceptable(candidat, m)) {
                        m.add(candidat);
                        added = true;
                    }
                }

                if (i == magatzem.size() - 1 && !added) {
                    this.addCompartment(candidat);
                    added = true;
                }
            }

        }
    }

    // Retorna el millor candidat
    // Retorna null si no queden mes candidats
    // Aixi ens serveix per saber si s'ha trobat la solucio
    private Producte getNextBest() {
        for (int i = 0; i < this.productes.length; i++) {
            if (this.tracked[i])
                continue;
            this.tracked[i] = true;
            return this.productes[i];
        }
        return null;
    }

    private boolean acceptable(Producte p, LinkedList<Producte> m) {
        boolean val = true;
        Iterator<Producte> it = m.listIterator();
        while (it.hasNext() && val) {
            if (p.reacciona(it.next())) {
                val = false;
            }
        }
        return val;
    }

    private void addCompartment(Producte p) {
        LinkedList<Producte> new_m = new LinkedList<Producte>();
        this.magatzem.add(new_m);
        new_m.add(p);
    }

    public LinkedList<LinkedList<Producte>> getSolucio() {
        return this.magatzem;
    }
}
