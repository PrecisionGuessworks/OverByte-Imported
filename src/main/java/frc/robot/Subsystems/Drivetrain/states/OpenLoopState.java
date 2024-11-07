package frc.robot.Subsystems.Drivetrain.states;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.Drivetrain.DrivetrainSubsystem;
import frc.robot.constants.Constants;
import frc.robot.constants.Constants.DriveConstants;
import frc.robot.constants.Constants.joysticks;

public class OpenLoopState extends Command {

    DrivetrainSubsystem drive = DrivetrainSubsystem.getInstance();
    double throttle, rotation;
    boolean halfSpeedRequested = false;
    boolean yeetRequested = false;
  
    public OpenLoopState() {
      // this.throttle = throttle;
      // this.rotation = rotation;
      addRequirements(drive);
    }
  
    @Override
    public void execute() {
      throttle = joysticks.DRIVER.getLeftX();
      rotation = joysticks.DRIVER.getRightX();
  
      if(joysticks.DRIVER.getRightBumper()){
        halfSpeedRequested = true;
      } else {
        halfSpeedRequested = false;
      }
      
      if(halfSpeedRequested) {
        throttle *= 0.5;
      }
  
      if(joysticks.DRIVER.getLeftBumper()){
        yeetRequested = true;
        // TODO: If desired, update throttle value here to eliminate the effects of the scalar
      } else {
        yeetRequested = false;
      }
  
      drive.curvatureDrive(throttle * DriveConstants.THROTTLE_SCALER, rotation * DriveConstants.ROTATION_SCALAR, yeetRequested);
    }
    
  }
