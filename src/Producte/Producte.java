package Producte;

import java.util.ArrayList;
import java.util.Iterator;

public class Producte {
    private int id;
    private ArrayList<Producte> prodReacciona;

    private Iterator<Producte> it;

    public Producte(int id){
        this.id = id;
        this.prodReacciona = new ArrayList<Producte>();
    }

    public void addReaccio(Producte prod){
        this.prodReacciona.add(prod);
    }

    public int getID() {
        return id;
    }

    public boolean reacciona(Producte p) {
        if(p.getID() == this.id) return false;
        Producte prod = null;
        this.startIteration();
        while(this.itHasNext()) {
            prod = this.getNext();
            if (p.getID() == prod.getID())
                return true;
        }
        return false;
    }

    // Reinicia el iterator
    public void startIteration() {
        this.it = this.prodReacciona.listIterator();
    }

    public Producte getNext() {
        return this.it.next();
    }

    public boolean itHasNext() {
        return this.it.hasNext();
    }

    public String productesReaccionaString() {
        String cad="";
        for (int i = 0; i < prodReacciona.size(); i++){
            cad += prodReacciona.get(i).getID()+", ";
        }
        return cad.substring(0, cad.length()-2); // Substring per retirar la ultima coma
    }

    @Override
    public String toString() {
        return "Producte " + getID() + " amb incompatibilitats amb els productes: " + productesReaccionaString();
    }
}
