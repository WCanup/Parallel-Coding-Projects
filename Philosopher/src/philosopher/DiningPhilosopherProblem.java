/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package philosopher;

class DiningPhilosopherProblem{
    
final static int NUM = 5;
static ChopStick[] sticks = new ChopStick[NUM];
static Philosopher[] thinkers = new Philosopher[NUM];
    
    public static void main(String[] args){
        for(int i = 0; i < NUM; i++){
            sticks[i] = new ChopStick();
        }
        for(int i = 0; i < NUM; i++){
            thinkers[i] = new Philosopher(i,sticks[i],sticks[(i+1)%5]);
            thinkers[i].start();
        }
        
    }

}

