package com.mtag.app.muaythaiathletesguide;

/**
 * Created by Rachel on 3/5/2018.
 */

public class Martial {
    public int id;
    public String martialName;
    public String martialDesc;
    public String martialTimeLimit;

    public Martial(){}

    public Martial(String martialName, String martialDesc, String martialTimeLimit) {
        this.martialName = martialName;
        this.martialDesc = martialDesc;
        this.martialTimeLimit =  martialTimeLimit;
    }

    public int getId() { return id; }
    public String getMartialName() { return martialName; }
    public String getMartialDesc() { return martialDesc; }
    public String getMartialTimeLimit() { return martialTimeLimit; }

    public void setId(int id) { this.id = id; }
    public void setMartialName(String martialName) { this.martialName = martialName; }
    public void setMartialDesc(String martialDesc) { this.martialDesc = martialDesc; }
    public void setMartialTimeLimit(String martialTimeLimit) { this.martialTimeLimit = martialTimeLimit; }

    @Override
    public String toString() {
        return "Martial [id=" + id + ", martialName=" + martialName + ", martialDesc=" + martialDesc + ", martialTimeLimit=" + martialTimeLimit + "]";
    }
}
