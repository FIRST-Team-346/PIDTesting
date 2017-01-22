package org.usfirst.frc.team346.subsystem;

import org.usfirst.frc.team346.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive implements Subsystem {
	
	private CANTalon m_leftDriveMaster;
	private CANTalon m_leftDriveSlave;
	private CANTalon m_rightDriveMaster;
	private CANTalon m_rightDriveSlave;
	
	private DoubleSolenoid m_gearShiftSolenoid;
	
	private boolean gearSpeed;
	
	private double speedP;
	private double speedI;
	private double speedD;
	private double speedF;
	
	Preferences preferences;
	
	public Drive(int leftMasterPort,int leftSlavePort,int rightMasterport,int rightSlavePort){
		preferences = Preferences.getInstance();
		
		this.m_leftDriveMaster = new CANTalon(leftMasterPort);								
		this.m_leftDriveMaster.changeControlMode(TalonControlMode.Speed);
		this.m_leftDriveMaster.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		this.m_leftDriveMaster.configEncoderCodesPerRev(4096);
		this.m_leftDriveMaster.disableControl();
		this.m_leftDriveMaster.setP(0);
		this.m_leftDriveMaster.setI(0);
		this.m_leftDriveMaster.setD(0);
		this.m_leftDriveMaster.setF(0);
		this.m_leftDriveMaster.enableControl();
		
		
		this.m_leftDriveSlave = new CANTalon(leftSlavePort);	
		this.m_leftDriveSlave.changeControlMode(TalonControlMode.Follower);
		this. m_leftDriveSlave.set(this.m_leftDriveMaster.getDeviceID());
		
		
		this.m_rightDriveMaster = new CANTalon(rightMasterport);								
		this.m_rightDriveMaster.changeControlMode(TalonControlMode.PercentVbus);
		
		this.m_rightDriveSlave = new CANTalon(rightSlavePort);		
		this.m_rightDriveSlave.changeControlMode(TalonControlMode.Follower);
		this.m_rightDriveSlave.set(this.m_rightDriveMaster.getDeviceID());
		
		this.m_gearShiftSolenoid = new DoubleSolenoid(RobotMap.GEAR_SOLENOID_PORT_1, RobotMap.GEAR_SOLENOID_PORT_2);
		this.gearSpeed = false;
	}
	
	public void setDrive(double leftDriveSpeed, double rightDriveSpeed){
		SmartDashboard.putNumber("Encoder Velocity (nu per 100ms)", this.m_leftDriveMaster.getEncVelocity());
		SmartDashboard.putNumber("Motor Output Voltage", this.m_leftDriveMaster.getOutputVoltage());
		SmartDashboard.putNumber("Error (nu per 100ms)", this.m_leftDriveMaster.getError());
		SmartDashboard.putNumber("Joystick L Y", leftDriveSpeed);
		
		this.speedP = this.preferences.getDouble("Speed P", 0.0);
		this.speedI = this.preferences.getDouble("Speed I", 0.0);
		this.speedD = this.preferences.getDouble("Speed D", 0.0);
		this.speedF = this.preferences.getDouble("Speed F", 0.0);
		
		this.m_leftDriveMaster.setP(this.speedP);
		this.m_leftDriveMaster.setI(this.speedI);
		this.m_leftDriveMaster.setD(this.speedD);
		this.m_leftDriveMaster.setF(this.speedF);
		
		// 1500 approximately full speed
		this.m_leftDriveMaster.set(-1500*leftDriveSpeed);
		this.m_rightDriveMaster.set(rightDriveSpeed);
	}
	
	public void gearShift() {
		this.gearSpeed = !this.gearSpeed;
		if(this.gearSpeed) {
			this.m_gearShiftSolenoid.set(Value.kForward);
		}
		else {
			this.m_gearShiftSolenoid.set(Value.kReverse);
		}
	}
	
	public void disable() {
		this.m_leftDriveMaster.disable();
		this.m_leftDriveSlave.disable();
		this.m_rightDriveMaster.disable();
		this.m_rightDriveSlave.disable();
	}

	@Override
	public void runPeriodic(Object... objects) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enable() {
		// TODO Auto-generated method stub
		
	}
	
}
