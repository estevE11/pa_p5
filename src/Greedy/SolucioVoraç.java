package Greedy;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import Producte.Producte;
import utils.Utils;

public class SolucioVoraç {
    private Producte[] productes;
    private boolean[] tracked;
    private int[] numReaccions;
    private LinkedList<LinkedList<Producte>> magatzem;

    public SolucioVoraç(Producte[] productes) {
        this.productes = productes;
        this.tracked = new boolean[this.productes.length];
        for (int i = 0; i < this.tracked.length; i++) {
            this.tracked[i] = false;
        }
        // Precalculem els nombres de reaccions de cada producte, ja que mai varien
        // i sino seria un cost extra innecessari.
        this.numReaccions = new int[this.productes.length];
        for (int i = 0; i < this.numReaccions.length; i++) {
            this.numReaccions[i] = this.productes[i].numReaccions();
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
        Producte best = null;
        int idx = -1;
        int nReac = 9999999;
        for (int i = 0; i < this.productes.length; i++) {
            if (this.tracked[i])
                continue;
            if(this.numReaccions[i] < nReac) {
                best = this.productes[i];
                idx = i;
                nReac = this.numReaccions[i];
            }
        }
        if(best == null) return null;
        this.tracked[idx] = true;
        return best;
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

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = Utils.readInt("Introdueix el numero de productes", sc);
        Producte[] productes = Utils.generateRandomInput(n);

        System.out.println("\nMatriu d'adjecència: ");
        Utils.printTable(productes);

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        SolucioVoraç solBack = new SolucioVoraç(productes);

        long t_sol1 = 0;
        long start = System.currentTimeMillis();
        solBack.solVor();
        long end = System.currentTimeMillis();
        t_sol1 = end - start;

        LinkedList<LinkedList<Producte>> solucio = solBack.getSolucio();
        System.out.println("VORAÇ:");
        Utils.printSolucio(solucio);
        System.out.println("\nTemps: " + t_sol1 + "ms");
    }
}
