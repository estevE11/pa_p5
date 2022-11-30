package Producte;

import java.util.ArrayList;

public class Producte {
    private int nom;
    private ArrayList<Producte> prod_reaciona;

    public Producte(int nom){
        this.nom = nom;
    }

    public void addReaccio(Producte prod){
        this.prod_reaciona.add(prod);
    }

    public int getNom() {
        return nom;
    }

    public String getProd_reaciona() {
        String cad="";
        for (int i=0;i<prod_reaciona.size();i++){
            cad+=prod_reaciona.get(i).getNom()+", ";
        }
        return cad;
    }

    @Override
    public String toString() {
        return "Producte "+getNom()+"(P"+getNom()+"  amb incompatibilitats amb els productes:"+getProd_reaciona();
    }
}
