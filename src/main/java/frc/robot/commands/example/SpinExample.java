package frc.robot.commands.example;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;
import frc.robot.subsystems.example.ExampleSubsystem;
import frc.robot.utils.logging.commands.LoggableCommand;

public class SpinExample extends LoggableCommand {
  private final ExampleSubsystem byebyeRoller;
  private final Timer timer;

  public SpinExample(ExampleSubsystem byebyeRoller) {
    timer = new Timer();
    this.byebyeRoller = byebyeRoller;
    addRequirements(byebyeRoller);
  }

  @Override
  public void initialize() {
    timer.restart();
  }

  @Override
  public void execute() {
    byebyeRoller.setSpeed(Constants.EXAMPLE_ROLLER_SPEED);
  }

  @Override
  public void end(boolean interrupted) {
    byebyeRoller.stopMotors();
  }

  @Override
  public boolean isFinished() {
    if (timer.hasElapsed(Constants.EXAMPLE_SPIN_TIMEOUT)) {
      return true;
    } else{
      return false;
    }
  }
}
