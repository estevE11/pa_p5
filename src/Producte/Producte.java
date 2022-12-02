package Producte;

import java.util.ArrayList;
import java.util.Iterator;

public class Producte {
    private int id;
    private ArrayList<Producte> prodReacciona;

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
        for (int i = 0; i < this.prodReacciona.size(); i++) {
            if (p.getID() == this.prodReacciona.get(i).getID())
                return true;
        }
        return false;
    }

    public Producte getProducteReacciona(int i) {
        Iterator<Producte> it = this.prodReacciona.iterator();
        while (it.hasNext()){
            if (it.next().getID()==i) return it.next();
        }
        return null;
    }

    public int getNProductesReacciona(int i) {
        return this.prodReacciona.size();
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
