package com.mtag.app.muaythaiathletesguide;

public class Wod {
    public int id;
    public String wodName;
    public String wodDesc;
    public String wodTimeLimit;

    public Wod(){}

    public Wod(String wodName, String wodDesc, String wodTimeLimit) {
        this.wodName = wodName;
        this.wodDesc =  wodDesc;
        this.wodTimeLimit = wodTimeLimit;
    }

    public int getId() { return id; }
    public String getWodName() { return wodName; }
    public String getWodDesc() { return wodDesc; }
    public String getWodTimeLimit() { return wodTimeLimit; }

    public void setId(int id) { this.id = id; }
    public void setWodName(String wodName) { this.wodName = wodName; }
    public void setWodDesc(String wodDesc) { this.wodDesc = wodDesc; }
    public void setWodTimeLimit(String wodTimeLimit) { this.wodTimeLimit = wodTimeLimit; }

    @Override
    public String toString() {
        return "Wod [id=" + id + ", wodName=" + wodName + ", wodDesc=" + wodDesc + ", wodTimeLimit=" + wodTimeLimit + "]";
    }
}
