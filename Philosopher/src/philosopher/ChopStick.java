/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package philosopher;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 *
 * @author willc
 */
public class ChopStick {
    
    Lock chop = new ReentrantLock();
    
    public boolean grab(){
        return this.chop.tryLock();
    }
    
    public void startEating(){
        this.chop.lock();
    }
    
    public void putDown(){
        this.chop.unlock();
    }
    
}
