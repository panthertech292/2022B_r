// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PickupConstant;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


public class PickupSubsysytem extends SubsystemBase {
  /** Creates a new PickupSubsysytem. */
  //Motors
  private final WPI_TalonSRX pickupMotor;
  private final WPI_TalonSRX pickupMotorArm;
 //Variable
 private double v_pickupSpeedTop;
 private double v_pickupSpeedArm;


  public PickupSubsysytem() {
    pickupMotor = new WPI_TalonSRX(PickupConstant.kPickupMotor);
    pickupMotorArm = new WPI_TalonSRX(PickupConstant.kPickupMotorArm);
    pickupMotor.setNeutralMode(NeutralMode.Brake);
  }
  //Motors
  public void ChangeSetpoint(double pickupspeed) {
    v_pickupSpeedTop = -pickupspeed;
  }
  
  public void ArmDown() {
    v_pickupSpeedArm = -.5;
  }

  public void ArmUp() {
    v_pickupSpeedArm = .5;
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    pickupMotor.set(v_pickupSpeedTop);
    pickupMotorArm.set(v_pickupSpeedArm);
  }
}
