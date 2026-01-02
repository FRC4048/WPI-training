// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.utils.logging.input.MotorLoggableInputs;
import frc.robot.utils.logging.io.motor.MockSparkMaxIo;
import frc.robot.utils.logging.io.motor.RealSparkMaxIo;
import frc.robot.utils.logging.io.motor.SimSparkMaxIo;
import frc.robot.utils.logging.io.motor.SparkMaxIo;
import frc.robot.utils.simulation.MotorSimulator;
import frc.robot.utils.simulation.RobotVisualizer;
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;

// The Intake subsystem spins the wheel that intakes the algae.

public class IntakeSubsystem extends SubsystemBase {
    public static final String LOGGING_NAME = "IntakeSubsystem";
    private final SparkMaxIo io;
    private final SparkMaxIo io2;

    public IntakeSubsystem(SparkMaxIo io, SparkMaxIo io2) {
        this.io = io;
        this.io2 = io2;
    }

    public void setSpeed(double speed) {
        io.set(speed);
        io2.set(-speed);
    }

    public void stopMotors() {
        io.stopMotor();
        io2.stopMotor();
    }

    @Override
    public void periodic() {
        io.periodic();
        io2.periodic();
    }

    public static SparkMaxIo createMockIo() {
        return new MockSparkMaxIo();
    }

    public static SparkMaxIo createRealIo(int motorId) {
        return new RealSparkMaxIo(LOGGING_NAME, createMotor(motorId), MotorLoggableInputs.allMetrics());
    }

    public static SparkMaxIo createSimIo(RobotVisualizer visualizer, SparkMax motor, LoggedMechanismLigament2d ligament) {
        return new SimSparkMaxIo(LOGGING_NAME, motor, MotorLoggableInputs.allMetrics(),
                new MotorSimulator(motor, ligament));
    }

    public static SparkMax createMotor(int port) {
        SparkMax motor = new SparkMax(port, SparkLowLevel.MotorType.kBrushless);
        SparkMaxConfig motorConfig = new SparkMaxConfig();
        motorConfig.idleMode(SparkBaseConfig.IdleMode.kBrake);
        motorConfig.smartCurrentLimit(Constants.NEO_CURRENT_LIMIT);
        motor.configure(
                motorConfig,
                SparkBase.ResetMode.kResetSafeParameters,
                SparkBase.PersistMode.kPersistParameters);

        return motor;
    }
}
