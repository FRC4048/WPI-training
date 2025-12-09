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

public class IntakeSubsystem extends SubsystemBase {
    
    public static final String LOGGING_NAME = "IntakeSubsystem";
    private final SparkMaxIo Io;

    public IntakeSubsystem(SparkMaxIo Io) {
        this.Io = Io;
    }

    public void setSpeed(double speed) {
        Io.set(speed);
    }

    public void stopMotors() {
        Io.stopMotor();
    }

    @Override
    public void periodic() {
        Io.periodic();
    }

    public static SparkMaxIo createMockIo() {
        return new MockSparkMaxIo();
    }

    public static SparkMaxIo createRealIo() {
        return new RealSparkMaxIo(LOGGING_NAME, createMotor(), MotorLoggableInputs.allMetrics());
    }

    public static SparkMaxIo createSimIo(RobotVisualizer visualizer) {
        SparkMax motor = createMotor();
        return new SimSparkMaxIo(LOGGING_NAME, motor, MotorLoggableInputs.allMetrics(),
                new MotorSimulator(motor, visualizer.getIntakeLigament()));
    }

    private static SparkMax createMotor() {
        SparkMax motor = new SparkMax(Constants.INTAKE_MOTOR_ID, SparkLowLevel.MotorType.kBrushless);
        SparkMaxConfig motorConfig = new SparkMaxConfig();
        motorConfig.idleMode(SparkBaseConfig.IdleMode.kBrake);
        motorConfig.smartCurrentLimit(Constants.NEO_CURRENT_LIMIT_2);
        motor.configure(
                motorConfig,
                SparkBase.ResetMode.kResetSafeParameters,
                SparkBase.PersistMode.kPersistParameters);
//changed constructor name

                return motor;
    }
}


