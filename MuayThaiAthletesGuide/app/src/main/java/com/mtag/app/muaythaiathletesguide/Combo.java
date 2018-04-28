package com.mtag.app.muaythaiathletesguide;

public class Combo {
    public int id;
    public String comboName;
    public String combo;

    // Constructor
    public Combo(){}

    public Combo(String comboName, String combo) {
        this.comboName = comboName;
        this.combo = combo;
    }

    public int getId() { return id; }
    public String getComboName() { return comboName; }
    public String getCombo() { return combo; }

    public void setId(int id) {
        this.id = id;
    }
    public void setComboName(String comboName) {
        this.comboName = comboName;
    }
    public void setCombo(String combo) {
        this.combo = combo;
    }

    @Override
    public String toString() {
        return "Combo [id=" + id + ", comboName=" + comboName + ", combo=" + combo + "]";
    }
}