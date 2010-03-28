package net.binarymuse.embjettest;

//import java.util.concurrent.locks.Lock;

public class State {

    private String state = "";
    //public Lock stateLock = new ();
    
    public synchronized String getState() {
        return state;
    }
    
    public synchronized void setState(String state) {
        this.state = state;
    }
    
}
