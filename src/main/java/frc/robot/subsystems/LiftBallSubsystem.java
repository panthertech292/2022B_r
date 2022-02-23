// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import frc.robot.Constants.LiftBallConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LiftBallSubsystem extends SubsystemBase {
  //Motors
  //private final CANSparkMax LiftBallHighMotor;
  private final CANSparkMax LiftBallLowMotor;
  //Vars
  private double v_liftBallLowSpeed;
  private double v_liftBallHighSpeed;
  /** Creates a new BallLiftSubsystem. */
  public LiftBallSubsystem() {
    //Motors
    LiftBallLowMotor = new CANSparkMax(LiftBallConstants.kLiftBallLowMotor, MotorType.kBrushless);
    //LiftBallHighMotor = new CANSparkMax(LiftBallConstants.kLiftBallHighMotor, MotorType.kBrushless);
    LiftBallLowMotor.restoreFactoryDefaults();
    LiftBallLowMotor.setIdleMode(IdleMode.kBrake);
    LiftBallLowMotor.setInverted(true);
  }
  public void setLiftBallLowMotor(double liftballLowspeed){
    v_liftBallLowSpeed = liftballLowspeed;
    LiftBallLowMotor.set(v_liftBallLowSpeed);
  }
  public void setLiftBallHighMotor(double liftballHighspeed){
    v_liftBallHighSpeed = liftballHighspeed;
    //LiftBallHighMotor.set(v_liftBallHighSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
