// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.LiftBallConstants;
import frc.robot.subsystems.LiftBallSubsystem;

public class LiftBall extends CommandBase {
  private final LiftBallSubsystem LiftBallSubsystem;
  /** Creates a new LiftBall. */
  public LiftBall(LiftBallSubsystem s_LiftBallSubsystem) {
    LiftBallSubsystem = s_LiftBallSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_LiftBallSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    LiftBallSubsystem.setLiftBallLowMotor(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    LiftBallSubsystem.setLiftBallLowMotor(LiftBallConstants.kLiftBallLowSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    LiftBallSubsystem.setLiftBallLowMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
