/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListVsHashSpeedTest;

public class SpeedTest {
    
    private boolean isOn;
    private long startTime;
    private long stopTime;
    
    public SpeedTest(){
        isOn = false;
    }
    
    public void start(){
        startTime = System.currentTimeMillis();       
        isOn = true;
    }
    
    public int stop(){
        if (!isOn)
            throw new IllegalStateException("You cannot stop unless you start first. Obviously.");
        stopTime = System.currentTimeMillis();
        isOn = false;
        return (int) (stopTime - startTime);
    }
    
}
