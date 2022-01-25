// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.*;

//Subsystems
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.PickupSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final static XboxController io_drivecontroller = new XboxController(Constants.kDriverController);
  private final static XboxController io_opercontroller = new XboxController(Constants.kOperController);

  //Subsystems
  private final DriveSubsystem s_DriverSubsystem = new DriveSubsystem();
  private final PickupSubsystem s_PickupSubsystem = new PickupSubsystem();


  //Drive Commands
  private final Command z_DriveTeleop = new DriveTeleop(s_DriverSubsystem); 

  //Pickup Commands
  private final Command z_PickupArmUp = new PickupArmUp(s_PickupSubsystem);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    //Set Defualt Commands
    s_DriverSubsystem.setDefaultCommand(z_DriveTeleop);
    s_PickupSubsystem.setDefaultCommand(z_PickupArmUp);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

  }
  public static double deadZoneCheck(double rawControllerInput){
    if (rawControllerInput > Constants.kControllerDeadZone || rawControllerInput < -Constants.kControllerDeadZone){
      return rawControllerInput;
    }
    else{
      return 0;
    }
  }

  public static double getLeftSpeed(){
    return deadZoneCheck(io_drivecontroller.getLeftY());
  }
  public static double getRightSpeed() {
    return deadZoneCheck(io_drivecontroller.getRightY());
  }
  public static double getLeftSpeedX(){
    return deadZoneCheck(io_drivecontroller.getLeftX());
  }
  public static double getRightSpeedX(){
    return deadZoneCheck(io_drivecontroller.getRightX());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
