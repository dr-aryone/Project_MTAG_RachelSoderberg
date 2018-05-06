package com.mtag.app.muaythaiathletesguide;

public class Portal {
    public int id;
    public String inName;
    public String inDesc;

    public Portal(){}

    public Portal(String inName, String inDesc) {
        this.id = id;
        this.inName = inName;
        this.inDesc = inDesc;
    }

    public int getId() { return id; }
    public String getInName() { return inName; }
    public String getInDesc() { return inDesc; }

    public void setId(int id) {
        this.id = id;
    }
    public void setInName(String inName) {
        this.inName = inName;
    }
    public void setInDesc(String inDesc) {
        this.inDesc = inDesc;
    }

    @Override
    public String toString() {
        return "Input [id=" + id + "Name=" + inName + ", Description=" + inDesc + "]";
    }
}
