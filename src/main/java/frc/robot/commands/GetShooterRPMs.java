// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class GetShooterRPMs extends CommandBase {
  private final ShooterSubsystem ShooterSubsystem;

  private double v_shooterLowSpeed;
  private double v_shooterHighSpeed;
  private int v_counter;
  /** Creates a new GetShooterRPMs. */
  public GetShooterRPMs(ShooterSubsystem s_ShooterSubsystem) {
    ShooterSubsystem = s_ShooterSubsystem;
    v_shooterLowSpeed = 0;
    v_shooterHighSpeed = 0;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_ShooterSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    for (v_counter = 0; v_counter < 101; v_counter = v_counter + 1){
      System.out.println("Testing shooter RPM @: "+ v_counter*.01);
    }


    //ShooterSubsystem.setShooterMotorLowSpeed(v_shooterLowSpeed);
    //ShooterSubsystem.setShooterMotorHighSpeed(v_shooterHighSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    ShooterSubsystem.setShooterMotorLowSpeed(0);
    ShooterSubsystem.setShooterMotorHighSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (v_counter > 100){
      return true;
    }
    else{
      return false;
    }
  }
}
