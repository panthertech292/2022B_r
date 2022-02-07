// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    static final int kDriverController = 0;
    static final int kOperController = 1;
    static final double kControllerDeadZone = 0.16;

    public static final class DriveConstants{

        public static final int kFrontLeftMotor = 2;
        public static final int kFrontRightMotor = 1;
        public static final int kBackLeftMotor = 4;
        public static final int kBackRightMotor = 3;
    }
    public static final class PickupConstant{
        //Motors
        public static final int kPickupMotor = 9; // place holder value
        public static final int kPickupMotorArm = 8; // place holder value
        //Encoders & Switches
        public static final int kPickupArmUpSwitch = 0; //DIO - place holder value
        //Speeds
        public static final double kPickupMotorSpeed = 0.52;
    } 



}

