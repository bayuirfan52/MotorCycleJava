/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author bayurf
 */
public class MotorCycleUI extends javax.swing.JFrame{
    /**
     * Creates new form MotorCycleUI
     */
    public MotorCycleUI() {
        initComponents();
        maxRPMval = 10800;
        isEnabled = false;
        tmpRPM = 0;
        tmpSpeed = 0;
    }
    
    MotorCycle motorCycle = new MotorCycle();
    private final int maxRPMval;
    private int maxSpeedval;
    private int tmpRPM;
    private int tmpSpeed;
    private boolean isEnabled;
    Thread speedUp;
    Thread speedRelease;
    Thread brakeSystem;
    
    class THROTTLEThread extends Thread{
        int maxSpeedval = 40;
        public void run(){
            while(true){                
                if(motorCycle.getGear() == "N" && motorCycle.getRPM() < 10800){
                    try {
                        tmpRPM = tmpRPM + 650;
                        Thread.sleep(50);
                        System.out.println("gas gigi N");
                    } catch (Exception ex) {
                        System.out.println("gas gigi N Exception");
                        this.stop();                        
                    }
                }
                if(motorCycle.getGear() == "1" && motorCycle.getRPM() < 10800 && motorCycle.getSpeed() <= 25){
                    try{
                        tmpSpeed = tmpSpeed + 3;
                        tmpRPM = tmpRPM + 750;
                        Thread.sleep(50);
                        System.out.println("gas gigi 1");
                    }catch(Exception e){
                        System.out.println("gas gigi 1 Exception");
                        this.stop();                        
                    }
                }
                if(motorCycle.getGear() == "2" && motorCycle.getRPM() < 10800 && motorCycle.getSpeed() <= 42){
                    try{
                        tmpSpeed = tmpSpeed + 2;
                        tmpRPM = tmpRPM + 550;
                        Thread.sleep(250);
                        System.out.println("gas gigi 2");
                    }catch(Exception e){
                        System.out.println("gas gigi 2 Exception");
                        this.stop();                        
                    }
                }
                if(motorCycle.getGear() == "3" && motorCycle.getRPM() < 10800 && motorCycle.getSpeed() <= 63){
                    try{
                        tmpSpeed = tmpSpeed + 1;
                        tmpRPM = tmpRPM + 150;
                        Thread.sleep(400);
                        System.out.println("gas gigi 3");
                    }catch(Exception e){
                        System.out.println("gas gigi 3 Exception");
                        this.stop();                        
                    }
                }
                else if(motorCycle.getGear() == "4" && motorCycle.getRPM() < 10800 && motorCycle.getSpeed() <= 85){
                    try{
                        tmpSpeed = tmpSpeed + 1;
                        tmpRPM = tmpRPM + 150;
                        Thread.sleep(400);
                        System.out.println("gas gigi 4");
                    }catch(Exception e){
                        System.out.println("gas gigi 4 Exception");
                        this.stop();                        
                    }
                }
                else if(motorCycle.getGear() == "5" && motorCycle.getRPM() < 10800 && motorCycle.getSpeed() <= 120){
                    try{
                        tmpSpeed = tmpSpeed + 1;
                        tmpRPM = tmpRPM + 150;
                        Thread.sleep(400);
                        System.out.println("gas gigi 5");
                    }catch(Exception e){
                        System.out.println("gas gigi 5 Exception");
                        this.stop();                        
                    }
                }
                
                motorCycle.setSpeed(tmpSpeed);
                motorCycle.setRPM(tmpRPM);
                SpeedLabel.setText(String.valueOf(motorCycle.getSpeed()));
                RPMLabel.setText(String.valueOf(motorCycle.getRPM()));                
            }
        }
    };
    
    class ReleasedThread extends Thread{
       public void run(){
           while(true){                
               if(tmpSpeed > 0 && tmpRPM > 50 && motorCycle.getGear() != "N"){
                   try{
                        tmpSpeed = tmpSpeed - 1;
                        tmpRPM = tmpRPM - 800;
                        Thread.sleep(500);
                        System.out.println("if release ke 1");
                    }catch(Exception e){
                        System.out.println("if release ke 1 Exception");
                        this.stop();                        
                    }
               }               
               else if(tmpRPM > 50 && motorCycle.getGear() == "N"){
                   try{
                        tmpRPM = tmpRPM - 800;
                        if(tmpRPM <= 50){
                            tmpRPM = 50;
                        }
                        Thread.sleep(50);
                        System.out.println("if release ke 2");
                    }catch(Exception e){
                        System.out.println("if release ke 2 Exception");
                        this.stop();                        
                    }
               }
               else{
                   if(tmpRPM <= 50 && tmpSpeed != 0){                       
                            tmpRPM = 50;
                       try{
                           Thread.sleep(500);
                           tmpSpeed--;
                           System.out.println("else release speed to 0");                           
                           motorCycle.setSpeed(tmpSpeed);
                           motorCycle.setRPM(tmpRPM);
                           SpeedLabel.setText(String.valueOf(motorCycle.getSpeed()));
                           RPMLabel.setText(String.valueOf(motorCycle.getRPM()));                           
                       }catch(Exception e){
                           this.stop();
                       }
                   }
                   
                   else{
                        try{
                             tmpRPM = 50;
                             tmpSpeed = 0;
                             System.out.println("else release");
                             motorCycle.setSpeed(tmpSpeed);
                             motorCycle.setRPM(tmpRPM);
                             SpeedLabel.setText(String.valueOf(motorCycle.getSpeed()));
                             RPMLabel.setText(String.valueOf(motorCycle.getRPM())); 
                             this.stop();
                        }catch(Exception e){
                            this.stop();
                        }
                   }
               }
                   
                motorCycle.setSpeed(tmpSpeed);
                motorCycle.setRPM(tmpRPM);
                SpeedLabel.setText(String.valueOf(motorCycle.getSpeed()));
                RPMLabel.setText(String.valueOf(motorCycle.getRPM()));                
           }
       }
    };
    
    class BrakePressed extends Thread{
        public void run(){
            while(true){
                if(tmpSpeed == 0){
                    System.out.println("Brake Stopped or Cannot Activate because speed is 0");
                    this.stop();
                }
                else if(tmpRPM <= 50 && tmpSpeed != 0){
                    tmpRPM = 50;
                    try{
                        tmpSpeed = tmpSpeed - 4;
                        Thread.sleep(200);
                        System.out.println("Brake Pressed Conditioning");
                    }catch(Exception e){
                        System.out.println("Brake Exception");
                        this.stop();                        
                    }
                }
                else{
                    try{
                        tmpRPM = tmpRPM - 400;
                        if(tmpRPM <= 50){
                            tmpRPM = 50;
                        }
                        tmpSpeed = tmpSpeed - 4;
                        Thread.sleep(200);
                        System.out.println("Brake Pressed Normalize");
                    }catch(Exception e){
                        System.out.println("Brake Exception");
                        this.stop();                        
                    }
                    motorCycle.setSpeed(tmpSpeed);
                    motorCycle.setRPM(tmpRPM);
                    SpeedLabel.setText(String.valueOf(motorCycle.getSpeed()));
                    RPMLabel.setText(String.valueOf(motorCycle.getRPM()));
                }
            }
        }
    };
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        STARTUP = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        SpeedLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        RPMLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        GearPosLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        GEARDOWN = new javax.swing.JButton();
        GEARUP = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        BRAKE = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        THROTTLE = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));

        STARTUP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Button_10-512.png"))); // NOI18N
        STARTUP.setBorder(null);
        STARTUP.setBorderPainted(false);
        STARTUP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                STARTUPActionPerformed(evt);
            }
        });

        jLabel1.setText("Speed");

        SpeedLabel.setFont(new java.awt.Font("Manjari Thin", 1, 24)); // NOI18N
        SpeedLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        SpeedLabel.setText("-");
        SpeedLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel3.setText("RPM");

        RPMLabel.setFont(new java.awt.Font("Manjari Thin", 1, 24)); // NOI18N
        RPMLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RPMLabel.setText("-");
        RPMLabel.setToolTipText("");
        RPMLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        RPMLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel5.setText("GEAR");

        GearPosLabel.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        GearPosLabel.setForeground(new java.awt.Color(255, 51, 51));
        GearPosLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        GearPosLabel.setText("-");
        GearPosLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(STARTUP)
                        .addGap(0, 143, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(GearPosLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(153, 153, 153))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(SpeedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(RPMLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)))))
                .addGap(34, 34, 34))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(GearPosLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SpeedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RPMLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(STARTUP)
                .addGap(39, 39, 39))
        );

        STARTUP.getAccessibleContext().setAccessibleName("Power");
        SpeedLabel.getAccessibleContext().setAccessibleName("SpeedUI");
        RPMLabel.getAccessibleContext().setAccessibleName("RPMUI");

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));

        GEARDOWN.setBackground(new java.awt.Color(0, 102, 204));
        GEARDOWN.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        GEARDOWN.setForeground(new java.awt.Color(255, 255, 255));
        GEARDOWN.setText("-");
        GEARDOWN.setBorder(null);
        GEARDOWN.setEnabled(false);
        GEARDOWN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GEARDOWNActionPerformed(evt);
            }
        });

        GEARUP.setBackground(new java.awt.Color(51, 51, 255));
        GEARUP.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        GEARUP.setForeground(new java.awt.Color(255, 255, 255));
        GEARUP.setText("+");
        GEARUP.setBorder(null);
        GEARUP.setEnabled(false);
        GEARUP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GEARUPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(GEARUP, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                    .addComponent(GEARDOWN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(GEARUP, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(GEARDOWN, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        GEARDOWN.getAccessibleContext().setAccessibleName("GearDown");
        GEARUP.getAccessibleContext().setAccessibleName("GearUp");

        jPanel3.setBackground(new java.awt.Color(255, 153, 153));

        BRAKE.setBackground(new java.awt.Color(255, 51, 51));
        BRAKE.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BRAKE.setForeground(new java.awt.Color(255, 255, 255));
        BRAKE.setText("BRAKE");
        BRAKE.setBorder(null);
        BRAKE.setEnabled(false);
        BRAKE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BRAKEMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                BRAKEMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BRAKE, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(BRAKE, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(204, 255, 102));

        THROTTLE.setBackground(new java.awt.Color(0, 150, 136));
        THROTTLE.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        THROTTLE.setForeground(new java.awt.Color(255, 255, 255));
        THROTTLE.setText("THROTTLE");
        THROTTLE.setBorder(null);
        THROTTLE.setEnabled(false);
        THROTTLE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                THROTTLEMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                THROTTLEMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(THROTTLE, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(THROTTLE, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void GEARDOWNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GEARDOWNActionPerformed
        // TODO add your handling code here:
        switch(motorCycle.getGear()){
            case "1":
                motorCycle.setGear("N");
                break;
            case "2":
                motorCycle.setGear("1");
                break;
            case "3":
                motorCycle.setGear("2");
                break;
            case "4":
                motorCycle.setGear("3");
                break;
            case "5":
                motorCycle.setGear("4");
                break;
            default:
                motorCycle.setGear("N");
                break;
        }
        GearPosLabel.setText(motorCycle.getGear());
    }//GEN-LAST:event_GEARDOWNActionPerformed

    private void STARTUPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_STARTUPActionPerformed
        // TODO add your handling code here:
        if(motorCycle.getEngineStatus() == false){
            isEnabled = true;
            motorCycle.setEngineStatus(true);
            THROTTLE.setEnabled(true);
            BRAKE.setEnabled(true);
            GEARUP.setEnabled(true);
            GEARDOWN.setEnabled(true);
            SpeedLabel.setText(String.valueOf(motorCycle.getSpeed()));
            RPMLabel.setText(String.valueOf(motorCycle.getRPM()));
            GearPosLabel.setText(motorCycle.getGear());
            System.out.println("Engine start");
        }
        else{
            isEnabled = false;
            motorCycle.setEngineStatus(false);
            motorCycle.setGear("N");
            motorCycle.setRPM(50);
            motorCycle.setSpeed(0);
            THROTTLE.setEnabled(false);
            BRAKE.setEnabled(false);
            GEARUP.setEnabled(false);
            GEARDOWN.setEnabled(false);
            SpeedLabel.setText("-");
            RPMLabel.setText("-");
            GearPosLabel.setText("-");
            speedUp.stop();
            speedRelease.stop();
            brakeSystem.stop();
            System.out.println("Engine stop");
        }
    }//GEN-LAST:event_STARTUPActionPerformed

    private void GEARUPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GEARUPActionPerformed
        // TODO add your handling code here:
        switch(motorCycle.getGear()){
            case "N":
                motorCycle.setGear("1");
                break;
            case "1":
                motorCycle.setGear("2");
                break;
            case "2":
                motorCycle.setGear("3");
                break;
            case "3":
                motorCycle.setGear("4");
                break;
            case "4":
                motorCycle.setGear("5");
                break;
            default:
                motorCycle.setGear("5");
                break;
        }
        GearPosLabel.setText(motorCycle.getGear());
    }//GEN-LAST:event_GEARUPActionPerformed

    private void THROTTLEMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_THROTTLEMousePressed
        if(isEnabled){            
            speedUp = new THROTTLEThread();
            if(speedRelease != null){
                speedRelease.stop();
            }
            speedUp.start(); 
        }    
    }//GEN-LAST:event_THROTTLEMousePressed

    private void THROTTLEMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_THROTTLEMouseReleased
        if(isEnabled){
            speedRelease = new ReleasedThread();
            if(speedUp != null){
                speedUp.stop();            
                speedRelease.start();
            }
            else{
                speedRelease.stop();
            }
        }        
    }//GEN-LAST:event_THROTTLEMouseReleased

    private void BRAKEMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BRAKEMousePressed
        // TODO add your handling code here:
        if(isEnabled){
            brakeSystem = new BrakePressed();
            brakeSystem.start();
        }        
    }//GEN-LAST:event_BRAKEMousePressed

    private void BRAKEMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BRAKEMouseReleased
        // TODO add your handling code here:
        if(isEnabled)brakeSystem.stop();
    }//GEN-LAST:event_BRAKEMouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        //</editor-fold>
        
        /* Create and display the form */
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MotorCycleUI().setVisible(true);                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BRAKE;
    private javax.swing.JButton GEARDOWN;
    private javax.swing.JButton GEARUP;
    private javax.swing.JLabel GearPosLabel;
    private javax.swing.JLabel RPMLabel;
    private javax.swing.JButton STARTUP;
    private javax.swing.JLabel SpeedLabel;
    private javax.swing.JButton THROTTLE;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables

}
