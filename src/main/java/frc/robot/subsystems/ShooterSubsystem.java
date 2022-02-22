// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

//Motors
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

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

  //Network Tables
  private NetworkTableEntry v_networkShooterSpeed1;
  private NetworkTableEntry v_networkShooterSpeed2;

  /** Creates a new ShooterSubsystem. */
  public ShooterSubsystem() {
    //Motors
    ShooterMotorLow = new CANSparkMax(ShooterConstants.kShooterMotorLow, MotorType.kBrushless);
    ShooterMotorHigh = new CANSparkMax(ShooterConstants.kShooterMotorHigh, MotorType.kBrushless);
    ShooterMotorLow.restoreFactoryDefaults();
    ShooterMotorHigh.restoreFactoryDefaults();
    ShooterMotorLow.setInverted(true);

    //Sensors & Encoders
    ShooterMotorLowEncoder = ShooterMotorLow.getEncoder();
    ShooterMotorHighEncoder = ShooterMotorHigh.getEncoder();

    //Netowrk Tables
    ShuffleboardTab MainTab = Shuffleboard.getTab("Main Tab");
    v_networkShooterSpeed1 = MainTab.add("Shooter Low Speed", 0).getEntry();
    v_networkShooterSpeed2 = MainTab.add("Shooter High Speed", 0).getEntry();

  }

  //Motros
  public void setShooterMotorLowSpeed(double lowshooterspeed){
    v_lowShooterSpeed = lowshooterspeed;
    ShooterMotorLow.set(v_lowShooterSpeed);
  }
  public void setShooterMotorHighSpeed(double highshooterspeed){
    v_highShooterSpeed = -highshooterspeed;
    ShooterMotorHigh.set(v_highShooterSpeed);
  }
  //Senors & Encoders - ur a nerd :) - Might need to create functions for position? Don't think I wil need them though.
  public double getShooterMotorLowEncoderVelocity(){
    return ShooterMotorLowEncoder.getVelocity();
  }
  public double getShooterMotorHighEncoderVelocity(){
    return ShooterMotorHighEncoder.getVelocity();
  }
  public double getShuffleboardLow(){
    return v_networkShooterSpeed1.getDouble(0.0);
  }
  public double getShuffleboardHigh(){
    return v_networkShooterSpeed2.getDouble(0.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Motor RPM LOW", getShooterMotorLowEncoderVelocity());
    SmartDashboard.putNumber("Motor RPM HIGH", getShooterMotorHighEncoderVelocity());
  }
}