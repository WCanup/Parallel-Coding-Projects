/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package symettricredblue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * @author willc
 */
public class SymettricRedBlue {

//Must Be An Even Value
    static final int NUMOFTHREADS = 8;
    
    
    public static void main(String[] args) {
        rbThread[] reds = new rbThread[NUMOFTHREADS/2];
        rbThread[] blues = new rbThread[NUMOFTHREADS/2];
        ThreadGroup redGroup = new ThreadGroup("red");
        ThreadGroup blueGroup = new ThreadGroup("blue");
        for(int i = 0; i < NUMOFTHREADS; i++){
            if(i < NUMOFTHREADS/2){
                Thread t = new Thread(redGroup, new rbThread(), "Thread: "+ i);
                t.start();
                
            }else{
              Thread t = new Thread(blueGroup, new rbThread(), "Thread: "+ i); 
              t.start();
            }
        }
        
    }
}

