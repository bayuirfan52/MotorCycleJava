/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author bayurf
 */
public class MotorCycle {
    private boolean EngineStatus;
    private int speedUI;
    private int rpmUI;
    private String gearUI;
    
    public MotorCycle(){
        EngineStatus = false;
        speedUI = 0;
        rpmUI = 50;
        gearUI = "N";
    }
        
    public void setEngineStatus(boolean Engine){
        this.EngineStatus = Engine;
    }
    
    public void setSpeed(int speed){
        this.speedUI = speed;
    }
    
    public void setRPM(int RPM){
        this.rpmUI = RPM;
    }
    
    public void setGear(String gear){
        this.gearUI = gear;
    }
    
    public boolean getEngineStatus(){
        return EngineStatus;
    }
    
    public int getSpeed(){
        return speedUI;
    }
    
    public int getRPM(){
        return rpmUI;
    }
    
    public String getGear(){
        return gearUI;
    }
}
