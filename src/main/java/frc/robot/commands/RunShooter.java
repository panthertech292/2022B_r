// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.Timer;

public class RunShooter extends CommandBase {
  private final ShooterSubsystem ShooterSubsystem;

  private double v_shooterLowSpeed;
  private double v_shooterHighSpeed;
  private Timer shooterRPMTimer;
  /** Creates a new RunShooter. */
  public RunShooter(ShooterSubsystem s_ShooterSubsystem, double shooterlowspeed, double shooterhighspeed) {
    ShooterSubsystem = s_ShooterSubsystem;
    v_shooterLowSpeed = shooterlowspeed;
    v_shooterHighSpeed = shooterhighspeed;
    shooterRPMTimer = new Timer();
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_ShooterSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    ShooterSubsystem.setShooterMotorLowSpeed(v_shooterLowSpeed);
    ShooterSubsystem.setShooterMotorHighSpeed(v_shooterHighSpeed);
    shooterRPMTimer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Need to do IRL testing here. Find the target RPM for the set speeds and make a method(or PID :( ) that makes the shooter run quicker to get to it's target RPM.
    ShooterSubsystem.setShooterMotorLowSpeed(v_shooterLowSpeed);
    ShooterSubsystem.setShooterMotorHighSpeed(v_shooterHighSpeed);
    if (ShooterSubsystem.getShooterMotorLowEncoderVelocity() < -2900){
      shooterRPMTimer.stop();
      System.out.println("Shooter to get to RPM took: " + shooterRPMTimer.get());
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    ShooterSubsystem.setShooterMotorLowSpeed(0);
    ShooterSubsystem.setShooterMotorHighSpeed(0);
    shooterRPMTimer.reset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
