
package org.usfirst.frc.team4213.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team4213.robot.commands.ExampleCommand;
import org.usfirst.frc.team4213.robot.subsystems.ExampleSubsystem;

import edu.wpi.first.wpilibj.Jaguar;

import org.usfirst.frc.team4213.robot.AIRFLOController;
import org.usfirst.frc.team4213.robot.MotorController;

import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.CameraServer;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	MotorController motor = new MotorController();
	public AIRFLOController controller = new AIRFLOController(1);
	private double currentVelocityTop = 0.0;
	private double currentVelocityBottom = 0.0;
	private boolean lockSpeed = false;
	private boolean topMotorSet = false;
	private boolean bottomMotorSet = false;
	private int speed;
	CameraServer server;

	
    public void robotInit() {
    }
	
	public void disabledPeriodic() {
	}

    public void autonomousInit() {

    }

    public void autonomousPeriodic() {
    }

    public void teleopInit() {

    }

    public void disabledInit(){

    }
    public Robot() {
        server = CameraServer.getInstance();
        server.setQuality(50);
        //the camera name (ex "cam0") can be found through the roborio web interface
        server.startAutomaticCapture("cam0");
    }

    /**
     * start up automatic capture you should see the video stream from the
     * webcam in your FRC PC Dashboard.
     */
    public void operatorControl() {

        while (isOperatorControl() && isEnabled()) {
            /** robot code here! **/
            Timer.delay(0.005);		// wait for a motor update time
        }
    }
    public void teleopPeriodic() {
        
        
        double velocityChange = 0.05;
       	try{
    		SmartDashboard.putNumber("Speed", speed);
    		SmartDashboard.putNumber("D-Pad", controller.getRawAxis(4));
    	}catch(Exception e){
    		SmartDashboard.putNumber("Speed", speed);
    	}
       		SmartDashboard.putNumber("D-Pad", controller.getRawAxis(4));
        
        if( controller.getButtonTripped(2)){
        	toggleLock();
            speed = 0;
        }
        topMotorSet = controller.getButton(8);
        bottomMotorSet = controller.getButton(7);


        if (lockSpeed){
        	
        	if(topMotorSet)
        		
        		if(controller.getButtonTripped(4) == true){
        			currentVelocityTop += velocityChange;
        			speed += 5;
        			
        		}
        		else if(controller.getButtonTripped(1) == true){
        			currentVelocityTop -= velocityChange;
        			speed -= 5;
        		}
        	if(bottomMotorSet)
        		if(controller.getButtonTripped(4) == true){
        			currentVelocityBottom += velocityChange;
        			speed += 5;
        		}
        		else if(controller.getButtonTripped(1) == true){
        			currentVelocityBottom -= velocityChange;
        			speed -= 5;
        		}
        }else{
        	/*int topVelocityChange = 0;
        	int bottomVelocityChange = 0;

        	if(controller.getRY() > 0)
        		topVelocityChange = 1;
        	else if(controller.getRY() < 0)
        		topVelocityChange = -1;
        	
        	if(controller.getLY() > 0)
        		bottomVelocityChange = 1;
        	else if(controller.getLY() < 0)
        		bottomVelocityChange = -1;
        		
        	currentVelocityTop += .04*topVelocityChange;
        	currentVelocityBottom += .04*bottomVelocityChange;*/
        	currentVelocityTop = controller.getRY();
        	currentVelocityBottom = controller.getLY();

        }
        if(currentVelocityTop > 1)
        	currentVelocityTop = 1;
        if(currentVelocityBottom > 1)
        	currentVelocityBottom = 1;
        
        motor.rawDrive(currentVelocityBottom,currentVelocityTop);

        
    }
    private void toggleLock(){
    	lockSpeed = !lockSpeed;
    }
    
    
    public void testPeriodic() {
    }
}