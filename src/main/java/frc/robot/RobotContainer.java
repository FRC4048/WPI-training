// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.example.SpinExample;
import frc.robot.subsystems.example.ExampleSubsystem;
import frc.robot.utils.simulation.RobotVisualizer;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    private final ExampleSubsystem byebyeRoller;

    private RobotVisualizer robotVisualizer;

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        switch (Constants.currentMode) {
            case REAL -> {
                byebyeRoller = new ExampleSubsystem(ExampleSubsystem.createRealIo());
            }
            case REPLAY -> {
                byebyeRoller = new ExampleSubsystem(ExampleSubsystem.createMockIo());
            }
            case SIM -> {
                robotVisualizer = new RobotVisualizer();
                byebyeRoller = new ExampleSubsystem(ExampleSubsystem.createSimIo(robotVisualizer));
            }
            default -> {
                throw new RuntimeException("Did not specify Robot Mode");
            }
        }
        configureBindings();
        putShuffleboardCommands();
    }

    /**
     * Use this method to define your trigger->command mappings. Triggers can be created via the
     * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
     * predicate, or via the named factories in {@link
     * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
     * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
     * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
     * joysticks}.
     */
    private void configureBindings() {
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An example command will be run in autonomous
        return null;
    }

    public RobotVisualizer getRobotVisualizer() {
        return robotVisualizer;
    }

    public void putShuffleboardCommands() {
        if (Constants.EXAMPLE_DEBUG) {
            // ByeBye Commands
            SmartDashboard.putData(
                    "Spin ByeBye Roller",
                    new SpinExample(byebyeRoller));
        }
    }

    public void updateSimulation() {
        //        if (Constants.currentMode == Constants.Mode.SIM) {
        //            SimulatedArena.getInstance().simulationPeriodic();
        //            Logger.recordOutput(
        //                    "FieldSimulation/RobotPosition", driveSimulation.getSimulatedDriveTrainPose());
        //        }
    }
}