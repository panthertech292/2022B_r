// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PickupConstants;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class PickupSubsystem extends SubsystemBase {
  /** Creates a new PickupSubsystem. */
  //Motors
  private final WPI_TalonSRX PickupMotor;
  private final WPI_TalonSRX PickupMotorArm;

  //Encoders & Switches
  private DigitalInput upArmSwitch;
  //Variable
  private double v_pickupSpeed;
  private double v_pickupSpeedArm;

  public PickupSubsystem() {
    //Motors
    PickupMotor = new WPI_TalonSRX(PickupConstants.kPickupMotor);
    PickupMotorArm = new WPI_TalonSRX(PickupConstants.kPickupMotorArm);
    PickupMotor.setNeutralMode(NeutralMode.Brake);

    //Encoders & Switches
    upArmSwitch = new DigitalInput(PickupConstants.kPickupArmUpSwitch);
  }
  //Motors
  public void setPickupMotorSpeed(double pickupspeed) {
    v_pickupSpeed = pickupspeed;
    PickupMotor.set(v_pickupSpeed);
  }
  public void setPickupArmMotorSpeed(double armpickupspeed){
    v_pickupSpeedArm = armpickupspeed;
    PickupMotorArm.set(v_pickupSpeedArm);
  }
  //Encoder & Limit Switches
  public double getArmEncoderVelocity(){ //This is a placeholder function, don't know what Encoder they are using
    //return pickupMotorArmEncoder.getVelocity();
    return 0;
  }
  public double getArmEncoderPosition(){
    //return pickupMotorArmEncoder.getPosition();
    return 0;
  }
  public boolean getArmUpLimitSwitch(){
    return upArmSwitch.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
