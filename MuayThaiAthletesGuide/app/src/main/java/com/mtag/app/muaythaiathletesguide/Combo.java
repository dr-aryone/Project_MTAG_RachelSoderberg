package com.mtag.app.muaythaiathletesguide;

/**
 * Created by Rachel on 2/20/2018.
 */

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public String getCombo() {
        return combo;
    }

    public void setCombo(String combo) {
        this.combo = combo;
    }

    @Override
    public String toString() {
        return "Combo [id=" + id + ", comboName=" + comboName + ", combo=" + combo + "]";
    }
}