package Producte;

import java.util.ArrayList;

public class Producte {
    private int id;
    private ArrayList<Producte> prodReacciona;

    public Producte(int id){
        this.id = id;
    }

    public void addReaccio(Producte prod){
        this.prodReacciona.add(prod);
    }

    public int getID() {
        return id;
    }

    public Producte getProducteReacciona(int i) {
        return this.prodReacciona.get(i);
    }

    public int getNProductesReacciona(int i) {
        return this.prodReacciona.size();
    }

    public String productesReaccionaString() {
        String cad="";
        for (int i = 0; i < prodReacciona.size(); i++){
            cad += prodReacciona.get(i).getID()+", ";
        }
        return cad;
    }

    @Override
    public String toString() {
        return "Producte " + getID() + " amb incompatibilitats amb els productes: " + productesReaccionaString();
    }
}
