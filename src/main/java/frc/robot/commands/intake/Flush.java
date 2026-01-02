package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.utils.logging.commands.LoggableCommand;

public class Flush extends LoggableCommand {
  private final IntakeSubsystem subsystem;
  private final Timer timer;

  public Flush(IntakeSubsystem subsystem) {
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
    subsystem.setSpeed(Constants.FLUSH_SPEED);
  }

  @Override
  public void end(boolean interrupted) {
    subsystem.stopMotors();
  }

  @Override
  public boolean isFinished() {
    return timer.hasElapsed(Constants.FLUSH_TIMEOUT);
  }
}
