// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

//Network & Shuffleboard
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.networktables.NetworkTableEntry;

public class ShooterSubsystem extends SubsystemBase {
  /** Creates a new ShooterSubsystem. */
  private final CANSparkMax TestMotor1;
  private final CANSparkMax TestMotor2;

  private double v_test1Speed;
  private double v_test2Speed;

  //Network Tables
  private NetworkTableEntry v_networkShooterSpeed1;
  private NetworkTableEntry v_networkShooterSpeed2;

  public ShooterSubsystem() {
    TestMotor1 = new CANSparkMax(44, MotorType.kBrushless);
    TestMotor2 = new CANSparkMax(25, MotorType.kBrushless);

    v_test1Speed = 0;
    v_test2Speed = 0;

    //Netowrk Tables
    ShuffleboardTab MainTab = Shuffleboard.getTab("Main Tab");
    v_networkShooterSpeed1 = MainTab.add("Shooter 1 Speed", 0).getEntry();
    v_networkShooterSpeed2 = MainTab.add("Shooter 2 Speed", 0).getEntry();
    
  }

  //Motors
  public void RunTestMotor1(double test1speed) {
    //v_test1Speed = v_networkShooterSpeed1.getDouble(0.0);
    v_test1Speed = -test1speed;
    TestMotor1.set(v_test1Speed);
  }
  public void RunTestMotor2(double test2speed){
    //v_test2Speed = v_networkShooterSpeed2.getDouble(0.0);
    v_test2Speed = -test2speed;
    TestMotor2.set(v_test2Speed);
  }

  public double getShuffleboard1(){
    return v_networkShooterSpeed1.getDouble(0.0);
  }
  public double getShuffleboard2(){
    return v_networkShooterSpeed2.getDouble(0.0);
  }


  @Override
  public void periodic() {
    
  
    //TestMotor1.set(v_test1Speed);
    //TestMotor2.set(v_test2Speed);
    // This method will be called once per scheduler run
  }
}
