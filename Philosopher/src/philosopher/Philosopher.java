
package philosopher;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Philosopher extends Thread {

    int ID;
    ChopStick leftChop;
    ChopStick rightChop;

    public Philosopher(int philosopherID, ChopStick left, ChopStick right) {
        this.ID = philosopherID;
        this.leftChop = left;
        this.rightChop = right;

    }
    
    public void eat() {
        boolean done = false;
        while (!done) {
            boolean left = this.leftChop.grab();
            boolean right = this.rightChop.grab();
            if (left && right) {
                try {

                    this.leftChop.startEating();
                    this.rightChop.startEating();

                    System.out.println("Philosopher " + ID + " is eating");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                } finally {
                    this.leftChop.putDown();
                    this.rightChop.putDown();
                    this.leftChop.putDown();
                    this.rightChop.putDown();
                    done = true;
                }

            } else {
                //System.out.println("Philosopher " + ID + " is waiting");
                if(left){
                    this.leftChop.putDown();
                }
                if(right){
                    this.rightChop.putDown();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }
            }
        }
    }

    public void think() {
        System.out.println("Philosopher " + ID + " is thinking");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {

        }
    }
    
    @Override
    public void run() {
        while (true) {
            int randomInt = (int) (Math.random() * 100) + 1;
            if (randomInt >= 50) {
                this.eat();
                
            } else {
                this.think();
                
            }
        }
    }
}