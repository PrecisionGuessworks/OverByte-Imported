package frc.robot.Subsystems.Drivetrain;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.DifferentialDrive.WheelSpeeds;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.Constants;
import frc.robot.constants.RobotMap;

public class DrivetrainSubsystem extends SubsystemBase{
    private VictorSP frontRightMotor, backRightMotor, frontLeftMotor, backLeftMotor;

    private SlewRateLimiter throttleFilter, rotationFilter;

    private static DrivetrainSubsystem instance;

    private DrivetrainSubsystem(){
        frontLeftMotor = new VictorSP(RobotMap.DRIVETRAIN_LEFT_FRONT_ID);
        backLeftMotor = new VictorSP(RobotMap.DRIVETRAIN_LEFT_BACK_ID);
        frontRightMotor = new VictorSP(RobotMap.DRIVETRAIN_RIGHT_FRONT_ID);
        backRightMotor = new VictorSP(RobotMap.DRIVETRAIN_RIGHT_BACK_ID);

        throttleFilter = new SlewRateLimiter(Constants.DriveConstants.POSITIVE_SLEW_RATE_LIMIT, Constants.DriveConstants.NEGATIVE_SLEW_RATE_LIMIT, 0);
        rotationFilter = new SlewRateLimiter(0.95);
    }
    public static synchronized DrivetrainSubsystem getInstance(){
        if (instance == null){
          instance = new DrivetrainSubsystem();
        }
        return instance;
      }
    
      public void setPower(double leftPower, double rightPower){
        frontLeftMotor.set(leftPower);
        frontRightMotor.set(rightPower);
        backLeftMotor.set(leftPower);
        backRightMotor.set(rightPower);
      }
    
      public void curvatureDrive(double throttle, double rotation, boolean yeetRequested){
        boolean quickTurn = true;
        if (throttle > 0.15){
          quickTurn = false;
        }
    
        if (!yeetRequested) {
          throttle = throttleFilter.calculate(throttle);
          rotation = rotationFilter.calculate(rotation);
        }
            
        //Note: Even though the variable is called wheel speed, this is actually for wheel powers
        WheelSpeeds wheelSpeed = DifferentialDrive.curvatureDriveIK(throttle, rotation, quickTurn);
    
        setPower(wheelSpeed.left, wheelSpeed.right);
      }
     
     
    
    //   @Override
    //   public void periodic() {
    //     // This method will be called once per scheduler run
    //   }
}
