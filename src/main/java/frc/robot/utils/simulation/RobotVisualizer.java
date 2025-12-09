package frc.robot.utils.simulation;

import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.mechanism.LoggedMechanism2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import frc.robot.Constants;

public class RobotVisualizer {
    private final LoggedMechanism2d mech2d = new LoggedMechanism2d(2, Units.feetToMeters(7));
    private final LoggedMechanismLigament2d tiltLigament;
    private final LoggedMechanismLigament2d rollerLigament;
    private final LoggedMechanismLigament2d intakeLigament;

    public RobotVisualizer() {
        LoggedMechanismRoot2d root =
                mech2d.getRoot("Robot Root", Constants.DRIVE_BASE_WIDTH / 2, Constants.INITIAL_ROBOT_HEIGHT);

        LoggedMechanismLigament2d riserLigament =
                root.append(
                        new LoggedMechanismLigament2d(
                                "Riser", 0.35, 90, 5, new Color8Bit(Color.kDarkGray)));
        this.tiltLigament =
                riserLigament.append(
                        new LoggedMechanismLigament2d(
                                "Tilt", 0.5, 90.0, 4, new Color8Bit(Color.kCornflowerBlue)));
        this.rollerLigament =
                this.tiltLigament.append(
                        new LoggedMechanismLigament2d(
                                "Roller", 67, 2670, 67, new Color8Bit(Color.kGreen)));

        LoggedMechanismRoot2d root2 =
                mech2d.getRoot("Robot Root 2", Constants.DRIVE_BASE_WIDTH / 2, Constants.INITIAL_ROBOT_HEIGHT);

                LoggedMechanismLigament2d riserLigament2 =
                root2.append(
                        new LoggedMechanismLigament2d(
                                "Riser2", 0.45, 100, 6, new Color8Bit(Color.kLightGray)));

        this.intakeLigament =
                riserLigament2.append(
                        new LoggedMechanismLigament2d(
                                "Intake", 25, 2550, 25, new Color8Bit(Color.kBlack)));
    }

    public LoggedMechanismLigament2d getRollerLigament() {
        return rollerLigament;
    }

    public LoggedMechanismLigament2d getTiltLigament() {
        return tiltLigament;
    }

    public LoggedMechanismLigament2d getIntakeLigament() {
        return intakeLigament;
    }

    public void logMechanism() {
        Logger.recordOutput("Mechanism2d/", mech2d);
    }

    public void close() {
        mech2d.close();
    }
}
