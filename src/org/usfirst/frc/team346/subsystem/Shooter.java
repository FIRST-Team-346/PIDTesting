package org.usfirst.frc.team346.subsystem;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

public class Shooter {
	
	private CANTalon m_flyWheel;
	
	private double m_shooterVoltage = 0.65;
	
	public Shooter(int topWheel) {
		this.m_flyWheel = new CANTalon(topWheel);								
		this.m_flyWheel.changeControlMode(TalonControlMode.PercentVbus);
	}    
   
	public void setVoltage() {
		this.m_flyWheel.set(this.m_shooterVoltage);
	}
	
}
