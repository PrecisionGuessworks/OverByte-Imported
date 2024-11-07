package frc.robot.Subsystems.Punch;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import frc.robot.constants.RobotMap;

public class PunchSubsystem {
    //private VictorSP topWinchMotor, bottomWinchMotor;
    private DoubleSolenoid punch;
    private VictorSPX topWinchMotor, bottomWinchMotor;
    
    public static PunchSubsystem instance;

    // TODO: Add trunkLatch and winch Limit switches???

    public PunchSubsystem(){
        topWinchMotor = new VictorSPX(RobotMap.WINCH_TOP_ID);
        bottomWinchMotor = new VictorSPX(RobotMap.WINCH_BOTTOM_ID);

        punch = new DoubleSolenoid(PneumaticsModuleType.REVPH, RobotMap.SHOOTER_LATCH_EXTEND, RobotMap.SHOOTER_LATCH_RETRACT);

        retractPunchPiston();
    }

    public static PunchSubsystem getInstance() {
        if (instance == null){
            instance = new PunchSubsystem();
        }
        return instance;
    }

    public void shootPunchPiston(){
        punch.set(DoubleSolenoid.Value.kForward);
    }

    public void retractPunchPiston(){
        punch.set(DoubleSolenoid.Value.kReverse);
    }

    public void setWinchPower(double power){
        topWinchMotor.set(VictorSPXControlMode.PercentOutput, power);
        bottomWinchMotor.set(VictorSPXControlMode.PercentOutput, power);
    }
}
