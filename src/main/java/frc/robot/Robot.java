// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Subsystems.Claw.ClawSubsystem;
import frc.robot.Subsystems.Intake.IntakeSubsystem;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  private XboxController xbox;
  private ClawSubsystem claw;
  private IntakeSubsystem intake;

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    xbox = new XboxController(0);
    claw = new ClawSubsystem();
    intake = new IntakeSubsystem();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void disabledExit() {}

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void autonomousExit() {}

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
    if(xbox.getAButtonPressed()) {
      claw.openFinger();
    }
    if(xbox.getBButtonPressed()) {
      claw.closeFinger();
    }
    if(xbox.getXButtonPressed()) {
      claw.raiseClaw();
    }
    if(xbox.getYButtonPressed()) {
      claw.lowerClaw();
    }
    if(xbox.getLeftBumperPressed()) {
      intake.deployIntake();
    }
    if(xbox.getRightBumperPressed()) {
      intake.retractIntake();
    }
    intake.setPower(xbox.getRightTriggerAxis() - xbox.getLeftTriggerAxis());
  }

  @Override
  public void teleopExit() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void testExit() {}
}
