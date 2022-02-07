// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class RunTestMotor1 extends CommandBase {
  private final ShooterSubsystem ShooterSubsystem;
  /** Creates a new RunTestMotor1. */
  public RunTestMotor1(ShooterSubsystem s_ShooterSubsystem) {
    ShooterSubsystem = s_ShooterSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_ShooterSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    ShooterSubsystem.RunTestMotor1(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    ShooterSubsystem.RunTestMotor1(ShooterSubsystem.getShuffleboard1());
    ShooterSubsystem.RunTestMotor2(ShooterSubsystem.getShuffleboard2());
  // testing different speeds for distance and height feb 1 -- Gavan
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    ShooterSubsystem.RunTestMotor1(0);
    ShooterSubsystem.RunTestMotor2(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
