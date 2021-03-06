// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

//Motors
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;

//Sensors & Encoders
import com.revrobotics.RelativeEncoder;


public class ShooterSubsystem extends SubsystemBase {
  //Motors
  private final CANSparkMax ShooterMotorLow;
  private final CANSparkMax ShooterMotorHigh;

  //Sensors & Encoders
  private RelativeEncoder ShooterMotorLowEncoder;
  private RelativeEncoder ShooterMotorHighEncoder;

  //Variables
  private double v_lowShooterSpeed;
  private double v_highShooterSpeed;

  /** Creates a new ShooterSubsystem. */
  public ShooterSubsystem() {
    //Motors
    ShooterMotorLow = new CANSparkMax(ShooterConstants.kShooterMotorLow, MotorType.kBrushless);
    ShooterMotorHigh = new CANSparkMax(ShooterConstants.kShooterMotorHigh, MotorType.kBrushless);
    ShooterMotorLow.restoreFactoryDefaults();
    ShooterMotorHigh.restoreFactoryDefaults();
    ShooterMotorLow.setIdleMode(IdleMode.kCoast);
    ShooterMotorHigh.setIdleMode(IdleMode.kCoast);
    
    ShooterMotorLow.setInverted(true);
    ShooterMotorHigh.setInverted(true);

    //Sensors & Encoders
    ShooterMotorLowEncoder = ShooterMotorLow.getEncoder();
    ShooterMotorHighEncoder = ShooterMotorHigh.getEncoder();
  }

  //Motors
  public void setShooterMotorLowSpeed(double lowshooterspeed){
    v_lowShooterSpeed = lowshooterspeed;
    ShooterMotorLow.set(v_lowShooterSpeed);
  }
  public void setShooterMotorHighSpeed(double highshooterspeed){
    v_highShooterSpeed = highshooterspeed;
    ShooterMotorHigh.set(v_highShooterSpeed);
  }
  //Senors & Encoders - Might need to create functions for position? Don't think I wil need them though.
  public double getShooterMotorLowEncoderVelocity(){
    return ShooterMotorLowEncoder.getVelocity();
  }
  public double getShooterMotorHighEncoderVelocity(){
    return ShooterMotorHighEncoder.getVelocity();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Motor RPM", getShooterMotorLowEncoderVelocity());
    SmartDashboard.putNumber("Motor RPM HIGH", getShooterMotorHighEncoderVelocity());
  }
}