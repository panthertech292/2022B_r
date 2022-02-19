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
import com.revrobotics.SparkMaxPIDController;

//Sensors & Encoders
import com.revrobotics.RelativeEncoder;

public class ShooterSubsystem extends SubsystemBase {
  //Motors
  private final CANSparkMax ShooterMotorLow;
  private final CANSparkMax ShooterMotorHigh;

  //Sensors & Encoders
  private RelativeEncoder ShooterMotorLowEncoder;
  private RelativeEncoder ShooterMotorHighEncoder;
  private SparkMaxPIDController ShooterLowPID;

  //Variables
  private double v_lowShooterSpeed;
  private double v_highShooterSpeed;
  public double c_P, c_I, c_D, c_Iz, c_FF, c_MaxOutput, c_MinOutput, c_maxRPM; //PID

  /** Creates a new ShooterSubsystem. */
  public ShooterSubsystem() {
    //Motors
    ShooterMotorLow = new CANSparkMax(ShooterConstants.kShooterMotorLow, MotorType.kBrushless);
    ShooterMotorHigh = new CANSparkMax(ShooterConstants.kShooterMotorHigh, MotorType.kBrushless);
    ShooterMotorLow.setInverted(true);

    //Sensors & Encoders
    ShooterMotorLowEncoder = ShooterMotorLow.getEncoder();
    ShooterMotorHighEncoder = ShooterMotorHigh.getEncoder();
    ShooterLowPID = ShooterMotorLow.getPIDController();

    c_P = 6e-5; 
    c_I = 0;
    c_D = 0; 
    c_Iz = 0; 
    c_FF = 0.000015; 
    c_MaxOutput = 1; 
    c_MinOutput = -1;
    c_maxRPM = 5700;

    // set PID coefficients
    ShooterLowPID.setP(c_P);
    ShooterLowPID.setI(c_I);
    ShooterLowPID.setD(c_D);
    ShooterLowPID.setIZone(c_Iz);
    ShooterLowPID.setFF(c_FF);
    ShooterLowPID.setOutputRange(c_MinOutput, c_MaxOutput);

    // display PID coefficients on SmartDashboard
    SmartDashboard.putNumber("P Gain", c_P);
    SmartDashboard.putNumber("I Gain", c_I);
    SmartDashboard.putNumber("D Gain", c_D);
    SmartDashboard.putNumber("I Zone", c_Iz);
    SmartDashboard.putNumber("Feed Forward", c_FF);
    SmartDashboard.putNumber("Max Output", c_MaxOutput);
    SmartDashboard.putNumber("Min Output", c_MinOutput);
  }
  

  //Motors
  public void setShooterMotorLowSpeed(double lowshooterspeed){
    v_lowShooterSpeed = lowshooterspeed;
    ShooterMotorLow.set(v_lowShooterSpeed);
  }
  public void setShooterMotorHighSpeed(double highshooterspeed){
    v_highShooterSpeed = -highshooterspeed;
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

    // read PID coefficients from SmartDashboard
    double p = SmartDashboard.getNumber("P Gain", 0);
    double i = SmartDashboard.getNumber("I Gain", 0);
    double d = SmartDashboard.getNumber("D Gain", 0);
    double iz = SmartDashboard.getNumber("I Zone", 0);
    double ff = SmartDashboard.getNumber("Feed Forward", 0);
    double max = SmartDashboard.getNumber("Max Output", 0);
    double min = SmartDashboard.getNumber("Min Output", 0);

    // if PID coefficients on SmartDashboard have changed, write new values to controller
    if((p != c_P)) { ShooterLowPID.setP(p); c_P = p; }
    if((i != c_I)) { ShooterLowPID.setI(i); c_I = i; }
    if((d != c_D)) { ShooterLowPID.setD(d); c_D = d; }
    if((iz != c_Iz)) { ShooterLowPID.setIZone(iz); c_Iz = iz; }
    if((ff != c_FF)) { ShooterLowPID.setFF(ff); c_FF = ff; }
    if((max != c_MaxOutput) || (min != c_MinOutput)) { 
      ShooterLowPID.setOutputRange(min, max); 
      c_MinOutput = min; c_MaxOutput = max; 
    }
    ShooterLowPID.setReference(2500, CANSparkMax.ControlType.kVelocity);
    SmartDashboard.putNumber("Shooter Power", ShooterMotorLow.get());
  }
}