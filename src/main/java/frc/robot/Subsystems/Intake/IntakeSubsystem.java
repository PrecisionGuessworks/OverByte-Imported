package frc.robot.Subsystems.Intake;



import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.RobotMap;

public class IntakeSubsystem extends SubsystemBase{
    private DoubleSolenoid intakePistons;
    private VictorSPX intakeMotor;
    
    public static final DoubleSolenoid.Value EXTEND = DoubleSolenoid.Value.kForward;
    public static final DoubleSolenoid.Value STOW = DoubleSolenoid.Value.kReverse;

    public static IntakeSubsystem instance;

    public IntakeSubsystem(){
        intakePistons = new DoubleSolenoid(PneumaticsModuleType.REVPH, RobotMap.INTAKE_EXTEND, RobotMap.INTAKE_RETRACT);
        intakeMotor = new VictorSPX(RobotMap.INTAKE_ID);

        retractIntake();
    }

    public static IntakeSubsystem getInstance() {
        if (instance == null){
            instance = new IntakeSubsystem();
        }
        return instance;
    }

    public void deployIntake(){
        intakePistons.set(EXTEND);
    }

    public void retractIntake(){
        intakePistons.set(STOW);
    }

    public void setPower(double power){
        intakeMotor.set(VictorSPXControlMode.PercentOutput, power);
    }
}
