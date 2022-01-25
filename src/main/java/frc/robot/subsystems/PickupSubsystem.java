// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PickupConstant;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class PickupSubsystem extends SubsystemBase {
  /** Creates a new PickupSubsystem. */
  //Motors
  private final WPI_TalonSRX pickupMotor;
  private final WPI_TalonSRX pickupMotorArm;

  //Encoders & Switches
  private DigitalInput upArmSwitch;
  //Variable
  private double v_pickupSpeed;
  private double v_pickupSpeedArm;

  public PickupSubsystem() {
    //Motors
    pickupMotor = new WPI_TalonSRX(PickupConstant.kPickupMotor);
    pickupMotorArm = new WPI_TalonSRX(PickupConstant.kPickupMotorArm);
    pickupMotor.setNeutralMode(NeutralMode.Brake);

    //Encoders & Switches
    upArmSwitch = new DigitalInput(PickupConstant.kPickupArmUpSwitch);
  }
  //Motors
  public void ChangePickupMotor(double pickupspeed) {
    v_pickupSpeed = -pickupspeed;
  }
  public void ChangePickupArmMotor(double armpickupspeed){
    v_pickupSpeedArm = armpickupspeed;
  }
  //Encoder & Limit Switches
  public double getArmEncoder(){ //This is a placeholder function, don't know what Encoder they are using
    return 0;
  }
  public boolean getArmUpLimitSwitch(){
    return upArmSwitch.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //pickupMotor.set(v_pickupSpeed);
    //pickupMotorArm.set(v_pickupSpeedArm);
  }
}
