package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.utils.logging.commands.LoggableCommand;

public class Intake extends LoggableCommand {
  private final IntakeSubsystem subsystem;
  private final Timer timer;

  public Intake(IntakeSubsystem subsystem) {
    timer = new Timer();
    this.subsystem = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    timer.restart();
  }

  @Override
  public void execute() {
    subsystem.setSpeed(Constants.INTAKE_SPEED);
  }

  @Override
  public void end(boolean interrupted) {
    subsystem.stopMotors();
  }

  @Override
  public boolean isFinished() {
    return timer.hasElapsed(Constants.INTAKE_TIMEOUT);
  }
}
