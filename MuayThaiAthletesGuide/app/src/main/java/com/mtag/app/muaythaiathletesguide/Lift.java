package com.mtag.app.muaythaiathletesguide;

/**
 * Created by Rachel on 3/5/2018.
 */

public class Lift {
    public int id;
    public String liftName;
    public String liftDesc;

    public Lift(){}

    public Lift(String liftName, String liftDesc) {
        this.liftName = liftName;
        this.liftDesc = liftDesc;
    }

    public int getId() { return id; }
    public String getLiftName() { return liftName; }
    public String getLiftDesc() { return liftDesc; }

    public void setId(int id) { this.id = id; }
    public void setLiftName(String liftName) { this.liftName = liftName; }
    public void setLiftDesc(String liftDesc) { this.liftDesc = liftDesc; }

    @Override
    public String toString() {
        return "Lift [id=" + id + ", liftName=" + liftName + ", liftDesc=" + liftDesc + "]";
    }
}
