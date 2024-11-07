package frc.robot.Subsystems.Claw;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.RobotMap;

public class ClawSubsystem extends SubsystemBase{
    private DoubleSolenoid claw, finger;

    public static final DoubleSolenoid.Value ARM_UP = DoubleSolenoid.Value.kReverse;
    public static final DoubleSolenoid.Value ARM_DOWN = DoubleSolenoid.Value.kForward;
    public static final DoubleSolenoid.Value FINGER_UP = DoubleSolenoid.Value.kForward;
    public static final DoubleSolenoid.Value FINGER_DOWN = DoubleSolenoid.Value.kReverse;

    public static ClawSubsystem instance;

    public ClawSubsystem(){
        claw = new DoubleSolenoid(PneumaticsModuleType.REVPH, RobotMap.CLAW_EXTEND, RobotMap.CLAW_RETRACT);
        finger = new DoubleSolenoid(PneumaticsModuleType.REVPH, RobotMap.FINGER_EXTEND,RobotMap.FINGER_RETRACT);
    
        closeFinger();

        raiseClaw();
    }

    public static ClawSubsystem getInstance() {
        if (instance == null){
            instance = new ClawSubsystem();
        }
        return instance;
    }

    public void raiseClaw(){
        claw.set(ARM_UP);
    }

    public void lowerClaw(){
        claw.set(ARM_DOWN);
    }

    public void closeFinger(){
        finger.set(FINGER_DOWN);
    }

    public void openFinger(){
        finger.set(FINGER_UP);
    }
}
