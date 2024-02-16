/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package symettricredblue;

/**
 *
 * @author willc
 */
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

public class rbThread extends Thread {

    static Lock lock = new ReentrantLock();
    static Condition redTurn = lock.newCondition();
    static Condition blueTurn = lock.newCondition();
    private static int redCount = 0;
    private static int redWaiting = 0;
    private static int blueCount = 0;
    private static int blueWaiting = 0;
    
    /*
    The following code simulates a symettric lock allowing two different groups
    to enter into the critical section. Mulitple threads of the same group can
    enter at once but not threads of different groups.
    
    The output will generate something like:
    
    RED 
    RED RED
    RED RED RED
    RED RED
    RED
    
    BLUE
    BLUE BLUE
    BLUE
    
    Each instance of RED/BLUE shows how many RED or BLUE threads are in the 
    critical section at the same time. 
    */
    
    
    
    
    //red will enter the critical section if there are no blues in the critical section
    //red will not enter if a certain amount of blues are waiting to enter
    public void redEnter() throws InterruptedException {
        lock.lock();
        try {

            redWaiting++;

            while ((blueCount > 0 || blueWaiting == (SymettricRedBlue.NUMOFTHREADS/2))) {
                
                redTurn.await();

            }
            redWaiting--;

            redCount++;
            for (int i = 0; i < redCount; i++) {
                System.out.print("RED ");
            }
            System.out.println();
        } finally {
            lock.unlock();
        }
    }
    
    //blue will enter the critical section if there are no reds in the critical section
    //blue will not enter if a certain amount of reds are waiting to enter
    public void blueEnter() throws InterruptedException {
        lock.lock();
        try {
            blueWaiting++;

            while ((redCount > 0 || redWaiting == (SymettricRedBlue.NUMOFTHREADS/2))) {
                blueTurn.await();
            }
            blueWaiting--;

            blueCount++;
            for (int i = 0; i < blueCount; i++) {
                System.out.print("BLUE ");
            }
            System.out.println();
        } finally {
            lock.unlock();
        }
    }
    
    //red will exit and wake blue threads if there are no more reds in the critical section
    public void redExit() {
        lock.lock();
        try {
            redCount--;
            for (int i = 0; i < redCount; i++) {
                System.out.print("RED ");
            }
            System.out.println();
            if (redCount == 0) {

                blueTurn.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }
    
    //blue will exit and wake red threads if there are no more blue in the critical section
    public void blueExit() {
        lock.lock();
        try {

            blueCount--;
            for (int i = 0; i < blueCount; i++) {
                System.out.print("BLUE ");
            }
            System.out.println();
            if (blueCount == 0) {

                redTurn.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                //simulate a random amount of work before the critical section
                Thread.sleep((long) (Math.random() * 5000 + 1)); 
                //
                if (Thread.currentThread().getThreadGroup().getName().matches("red")) {
                    this.redEnter();
                    //simulate work in the critical section
                    Thread.sleep(1000);
                    //
                    this.redExit();

                } else {
                    this.blueEnter();
                    //simulate work in the critical section
                    Thread.sleep(1000);
                    //
                    this.blueExit();
                }
            } catch (InterruptedException ex) {
                // Handle InterruptedException
            }
        }
    }
}
