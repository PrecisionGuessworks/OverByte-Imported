package frc.robot.constants;

import edu.wpi.first.wpilibj.XboxController;

public class Constants {
    public class DriveConstants{
        public static final double POSITIVE_SLEW_RATE_LIMIT = 0.95;
        public static final double NEGATIVE_SLEW_RATE_LIMIT = -1.75;

        public static final double THROTTLE_SCALER = 0.8;
        public static final double ROTATION_SCALAR = 0.2;
    }

    public static class joysticks{
        public static final XboxController DRIVER = new XboxController(0);
        public static final XboxController OPERATOR = new XboxController(1);
    }
    
}
