// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.RobotBase;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public enum Mode {
        /**
         * Running on a real robot.
         */
        REAL,
        /**
         * Running a physics simulator.
         */
        SIM,
        /**
         * Replaying from a log file.
         */
        REPLAY
    }

    // Mode
    public static final Mode simMode = Mode.SIM;
    //  public static final Mode simMode = Mode.REPLAY;

    public static final Mode currentMode = RobotBase.isReal() ? Mode.REAL : simMode;

    public static final int NEO_CURRENT_LIMIT = 20;

    // Logging
    public static final long MAX_LOG_TIME_WAIT = 10;
    public static final boolean ENABLE_LOGGING = true;

    public static final boolean EXAMPLE_DEBUG = true;
    public static final int EXAMPLE_MOTOR_ID = 1;
    public static final double EXAMPLE_ROLLER_SPEED = 0.25;
    public static final double EXAMPLE_SPIN_TIMEOUT = 5;

    public static final boolean ARM_DEBUG = true;
    public static final double DRIVE_BASE_WIDTH = 0.635;
    public static final double DRIVE_BASE_LENGTH = 0.635;
    public static final double INITIAL_ROBOT_HEIGHT = 0;

}
