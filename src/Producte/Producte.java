package Producte;

import java.util.ArrayList;

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
        return cad.substring(0, cad.length()-2); // Substring per retirar la ultima coma
    }

    @Override
    public String toString() {
        return "Producte " + getID() + " amb incompatibilitats amb els productes: " + productesReaccionaString();
    }
}
