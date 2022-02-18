// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//Network & Shuffleboard
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.networktables.NetworkTableEntry;

//Drive Imports
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants.DriveConstants;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class DriveSubsystem extends SubsystemBase {
  /** Creates a new drive. */
  private final WPI_TalonSRX FrontLeftMotor;
  private final WPI_TalonSRX FrontRightMotor;
  private final WPI_TalonSRX BackLeftMotor;
  private final WPI_TalonSRX BackRightMotor;
  private final MotorControllerGroup LeftSide;
  private final MotorControllerGroup RightSide;
  private final DifferentialDrive DifDrive;
  private double v_leftSpeed;
  private double v_rightSpeed;
  private double v_leftXSpeed;
  private double v_rightYSpeed;

  private double v_setPointLeft;
  private double v_setPointRight;
  private boolean v_arcadeDrive;

  //Network Tables
  private NetworkTableEntry v_networkTableDriveMode;

  public DriveSubsystem() {
    FrontLeftMotor = new WPI_TalonSRX(DriveConstants.kFrontLeftMotor);
    FrontRightMotor = new WPI_TalonSRX(DriveConstants.kFrontRightMotor);
    BackLeftMotor = new WPI_TalonSRX(DriveConstants.kBackLeftMotor);
    BackRightMotor = new WPI_TalonSRX(DriveConstants.kBackRightMotor);
    LeftSide = new MotorControllerGroup(FrontLeftMotor, BackLeftMotor);
    RightSide = new MotorControllerGroup(FrontRightMotor, BackRightMotor);
    DifDrive = new DifferentialDrive(LeftSide,RightSide);

    //Drive Modes
    v_arcadeDrive = true;

    //Netowrk Tables
    ShuffleboardTab MainTab = Shuffleboard.getTab("Main Tab");
    v_networkTableDriveMode = MainTab.add("Arcade Drive Enabled", v_arcadeDrive).withWidget(BuiltInWidgets.kToggleButton).getEntry();
  
  }
  
  public void differentialTankDrive(double leftspeed, double rightspeed){
    v_leftSpeed = -leftspeed;
    v_rightSpeed = rightspeed;
    DifDrive.tankDrive(v_leftSpeed,v_rightSpeed);
  }
  //Telop Drive
  public void differentialArcadeDrive(double leftXspeed, double rightYspeed){
    v_leftXSpeed = leftXspeed;
    v_rightYSpeed = -rightYspeed;
    DifDrive.arcadeDrive(v_leftXSpeed, v_rightYSpeed);
  }
  /*
  public void driveTeleop(double driverLeftX, double driverLeftY, double driverRightX, double driverRightY) {
    if(v_arcadeDrive == true){
      differentialArcadeDrive(RobotContainer.getDriverLeftSpeedX(), RobotContainer.getDriverRightSpeed());
    }
    else{
      differentialTankDrive((RobotContainer.getDriverLeftSpeed()), RobotContainer.getDriverRightSpeed());
    }
  }
  */
  public void driveTeleopArcade(double driverLeftX, double driverRightY){
    differentialArcadeDrive(driverLeftX, driverRightY);
  }
  public void driveTeleopTank(double driverLeftY, double driverRightY){
    differentialTankDrive(driverLeftY, driverRightY);
  }
  public void driveAuto() {
    differentialTankDrive(-v_setPointLeft, -v_setPointRight);
  }
  //Shuffleboard Handler
  //public void updateShuffleBoard(){
  //  v_arcadeDrive = v_networkTableDriveMode.getBoolean(true);
  //}
  //This is untested on an actual robot, and needs to be demoed IRL
  public boolean isDriveModeArcade(){
    return v_networkTableDriveMode.getBoolean(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    //Update Shuffleboard(Maybe I should stop making useless comments like these?)
    //updateShuffleBoard();
  }
}
