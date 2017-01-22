package org.usfirst.frc.team346.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
		
    // Human interface device (HID) ports:
	public static final int LEFT_JOYSTICK_PORT = 0;
	public static final int RIGHT_JOYSTICK_PORT = 1;
	public static final int BUTTON_BOARD_PORT = 2;
	
	// Motor controller ports:
	public static final int LEFT_DRIVE_MASTER_PORT = 2;
	public static final int LEFT_DRIVE_SLAVE_PORT = 1;
	
	public static final int RIGHT_DRIVE_MASTER_PORT = 16;
	public static final int RIGHT_DRIVE_SLAVE_PORT = 3;
	
	// Solenoid ports:
	public static final int GEAR_SOLENOID_PORT_1 = 5;
	public static final int GEAR_SOLENOID_PORT_2 = 6;
	
	// Shooter motor ports:
	//public static final int LEFT_SHOOTER_MOTOR_PORT;
	
}
