package org.usfirst.frc.team4213.robot;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MotorController {
    Jaguar motorTop = new Jaguar(8);
    Jaguar motorBot = new Jaguar(9);
    
     void rawDrive(double ry, double ly){
   	 
    	 motorTop.set(ly);
    	motorBot.set(ry);
    }
}
