package it.unitn.disi.webarch.sabinandone.utilities;

import java.io.Serializable;

public class FlagBean implements Serializable {

    private String nation;

    private String capital;

    public FlagBean(){
        this.nation = "";
        this.capital = "";
    }

    public FlagBean(String nation, String capital){
        this.nation = nation;
        this.capital = capital;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    @Override
    public String toString(){
        return "Nation: " + this.nation + "\nCapital: " + this.capital;
    }

}
