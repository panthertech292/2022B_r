// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
//import com.kauailabs.navx.frc.AHRS;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.Constants.DriveConstants;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
  /** Creates a new drive. */
  private final WPI_TalonSRX FrontLeftMotor;
  private final WPI_TalonSRX FrontRightMotor;
  private final WPI_TalonSRX BackLeftMotor;
  private final WPI_TalonSRX BackRightMotor;
  private final WPI_TalonSRX DeleteThisMotorPickup;
  private final MotorControllerGroup LeftSide;
  private final MotorControllerGroup RightSide;
  private final DifferentialDrive DifDrive;
  private double v_leftSpeed;
  private double v_rightSpeed;
  private double v_xSpeed;
  private double v_rotationSpeed;
  private int c_modeTeleop;
  private int v_driveMode;
  private int c_modeSetPoint;

  
  public DriveSubsystem() {
    FrontLeftMotor = new WPI_TalonSRX(DriveConstants.kFrontLeftMotor);
    FrontRightMotor = new WPI_TalonSRX(DriveConstants.kFrontRightMotor);
    BackLeftMotor = new WPI_TalonSRX(DriveConstants.kBackLeftMotor);
    BackRightMotor = new WPI_TalonSRX(DriveConstants.kBackRightMotor);
    DeleteThisMotorPickup = new WPI_TalonSRX(40);
    LeftSide = new MotorControllerGroup(FrontLeftMotor, BackLeftMotor);
    RightSide = new MotorControllerGroup(FrontRightMotor, BackRightMotor);
    DifDrive = new DifferentialDrive(LeftSide,RightSide);
    c_modeTeleop = 0;
    c_modeSetPoint = 1;
    v_driveMode = c_modeTeleop;
  
  }
  
  public void differentialDrive(double leftspeed, double rightspeed){
    v_leftSpeed = -leftspeed;
    v_rightSpeed = rightspeed;
    DifDrive.tankDrive(v_leftSpeed,v_rightSpeed);
    
  }
  public void differentialDriveArcade(double xspeed, double rotation){
    v_xSpeed = xspeed;
    v_rotationSpeed = -rotation;
    DifDrive.arcadeDrive(v_xSpeed, v_rotationSpeed);
  }
  public void driveModeTeleop(){
    v_driveMode = c_modeTeleop;


  }

  public void driveTeleop() {
    //differentialDrive(RobotContainer.getLeftSpeed(), RobotContainer.getRightSpeed());
    differentialDriveArcade(RobotContainer.getLeftSpeedX(), RobotContainer.getRightSpeed());
  } 


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    driveTeleop();
    //DeleteThisMotorPickup.set(.40);

  }
}
