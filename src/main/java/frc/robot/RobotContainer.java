// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.ShooterConstants;
import frc.robot.commands.*;

//Subsystems
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LiftBallSubsystem;
import frc.robot.subsystems.LiftSubsystem;
import frc.robot.subsystems.PickupSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final static XboxController io_drivercontroller = new XboxController(Constants.kDriverController);
  private final static XboxController io_opercontroller = new XboxController(Constants.kOperController);

  //Subsystems
  private final DriveSubsystem s_DriverSubsystem = new DriveSubsystem();
  private final PickupSubsystem s_PickupSubsystem = new PickupSubsystem();
  private final ShooterSubsystem s_ShooterSubsystem = new ShooterSubsystem();
  private final LiftSubsystem s_LiftSubsystem = new LiftSubsystem();
  private final LiftBallSubsystem s_LiftBallSubsystem = new LiftBallSubsystem();

  //Drive Commands
  private final Command z_DriveTeleop = new DriveTeleop(s_DriverSubsystem); 

  //Pickup Commands
  private final Command z_PickupArmUp = new PickupArmUp(s_PickupSubsystem);
  private final Command z_PickupArmDown = new PickupArmDown(s_PickupSubsystem);

  //Shooter Commands
  private final Command z_RunShooter = new RunShooter(s_ShooterSubsystem, ShooterConstants.kShooterLowSpeed, ShooterConstants.kShooterHighSpeed);

  //Lift Commands
  private final Command z_LiftExtend = new LiftExtend(s_LiftSubsystem);
  private final Command z_LiftRetract = new LiftRetract(s_LiftSubsystem);
  private final Command z_LiftRotate = new LiftRotate(s_LiftSubsystem);
  
  //Lift Ball Commands
  private final Command z_LiftBall = new LiftBall(s_LiftBallSubsystem);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    //Set Defualt Commands
    s_DriverSubsystem.setDefaultCommand(z_DriveTeleop);
    s_PickupSubsystem.setDefaultCommand(z_PickupArmUp);
    s_LiftSubsystem.setDefaultCommand(z_LiftRotate);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //Driver Controller
    final JoystickButton d_aButton = new JoystickButton(io_drivercontroller, Button.kA.value);
    final JoystickButton d_bButton = new JoystickButton(io_drivercontroller, Button.kB.value);
    final JoystickButton d_xButton = new JoystickButton(io_drivercontroller, Button.kX.value);
    final JoystickButton d_yButton = new JoystickButton(io_drivercontroller, Button.kY.value);
    final JoystickButton d_startButton = new JoystickButton(io_drivercontroller, Button.kStart.value);
    final JoystickButton d_backButton = new JoystickButton(io_drivercontroller, Button.kBack.value);

    //Operator Controller
    final JoystickButton o_aButton = new JoystickButton(io_opercontroller, Button.kA.value);
    final JoystickButton o_bButton = new JoystickButton(io_opercontroller, Button.kB.value);
    final JoystickButton o_xButton = new JoystickButton(io_opercontroller, Button.kX.value);
    final JoystickButton o_yButton = new JoystickButton(io_opercontroller, Button.kY.value);
    final JoystickButton o_startButton = new JoystickButton(io_opercontroller, Button.kStart.value);
    final JoystickButton o_backButton = new JoystickButton(io_opercontroller, Button.kBack.value);
    final JoystickButton o_leftBumper = new JoystickButton(io_opercontroller, Button.kLeftBumper.value);
    final JoystickButton o_rightBumper = new JoystickButton(io_opercontroller, Button.kRightBumper.value);

    //Driver Controller Binds
    d_aButton.toggleWhenPressed(z_PickupArmDown);
    

    //Operator Controller Binds
    o_leftBumper.whileHeld(z_LiftRetract);
    o_rightBumper.whileHeld(z_LiftExtend);
    o_aButton.whileHeld(z_RunShooter);
    o_startButton.whileHeld(z_LiftBall);
  }
  public static double deadZoneCheck(double rawControllerInput){
    if (rawControllerInput > Constants.kControllerDeadZone || rawControllerInput < -Constants.kControllerDeadZone){
      return rawControllerInput;
    }
    else{
      return 0;
    }
  }

  public static double getDriverLeftSpeed(){
    return deadZoneCheck(io_drivercontroller.getLeftY());
  }
  public static double getDriverRightSpeed() {
    return deadZoneCheck(io_drivercontroller.getRightY());
  }
  public static double getDriverLeftSpeedX(){
    return deadZoneCheck(io_drivercontroller.getLeftX());
  }
  public static double getDriverRightSpeedX(){
    return deadZoneCheck(io_drivercontroller.getRightX());
  }
  public static double getOperRightSpeedY(){
    return deadZoneCheck(io_opercontroller.getRightY());
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
