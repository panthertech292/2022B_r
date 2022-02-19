// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
//THIS IS NOT ACTUALLY A PID, JUST NAMED PID FOR CONVIENCE SAKES
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class RunShooterPID extends CommandBase {
  private final ShooterSubsystem ShooterSubsystem;

  private double v_shooterLowSpeed;
  private double v_shooterHighSpeed;
  public double c_P, c_I, c_D, c_Iz, c_FF, c_MaxOutput, c_MinOutput, c_maxRPM; //PID
  /** Creates a new RunShooterPID. */
  public RunShooterPID(ShooterSubsystem s_ShooterSubsystem, double shooterlowspeed, double shooterhighspeed) {
    ShooterSubsystem = s_ShooterSubsystem;
    v_shooterLowSpeed = shooterlowspeed;
    v_shooterHighSpeed = shooterhighspeed;


    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_ShooterSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  
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
    return false;
  }
}
