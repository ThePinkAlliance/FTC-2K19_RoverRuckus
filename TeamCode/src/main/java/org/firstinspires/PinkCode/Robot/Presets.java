package org.firstinspires.PinkCode.Robot;

// Abstract Class to Define Preset Values Used Throughout Subsystems
public abstract class Presets {
    // Collector Presets TODO: Confirm Collector Presets
    public static final double COLLECTOR_COLLECT_POWER = 1; // Power Sent to Motor While Collecting
    public static final double COLLECTOR_EJECT_POWER = -1; // Power Sent to Motor While Ejecting
    public static final double COLLECTOR_HOLD_POWER = 0; // Power Sent to Motor by Default
    public static final double COLLECTOR_COLLECT_POSITION = 0;
    public static final double COLLECTOR_TRAVEL_POSITION = .2;
    public static final double COLLECTOR_SORT_POSITION = .4;

    // Extender Presets TODO: Confirm Extender Presets
    public static final double EXTEND_Kp = 1; // Kp for Extender PD
    public static final double EXTEND_Kd = 0; // Kd for Extender PD
    public static final double EXTEND_POSITION_THRESHOLD = 0; // Success Threshold for Extender PD
    public static final double EXTEND_MIN_POWER = -1; // Most Power Sent While Retracting Extender
    public static final double EXTEND_MAX_POWER = 1; // Most Power Sent While Extending Extender
    public static final double EXTEND_COLLECT_POSITION = 100; // Position for Collecting From Crater
    public static final double EXTEND_CRATER_POSITION = 80; // Position Barely Outside Crater Plane
    public static final double EXTEND_SORT_POSITION = 0; // Retracted Position for Transfer to Lift
    public static final double EXTEND_GOLD_POSITION = 75; // Position to Knock Gold if on Side
    public static final double EXTEND_MID_GOLD_POSITION = 80; // Position to Knock Gold if in Mid
    public static final double EXTEND_MIN_POSITION = 0; // Min position allowed for the extender
    public static final double EXTEND_MAX_POSITION = 200; // Max position allowed for the extender

    // Lift Presets TODO: Confirm Lift Presets
    public static final double LIFT_Kp = 1; // Kp for Lift PD
    public static final double LIFT_Kd = 0; // Kd for Lift PD
    public static final double LIFT_POSITION_THRESHOLD = 0; // Success Threshold for Lift PD
    public static final double LIFT_MIN_POWER = -1; // Most Power Sent While Lowering Lift
    public static final double LIFT_MAX_POWER = 1; // Most Power Sent While Raising Lift
    public static final double LIFT_SCORE_POSITION = 80; // Raised Position for Scoring in Lander
    public static final double LIFT_SORT_POSITION = 0; // Lowered Position for Collection from Sort
    public static final double LIFT_RELEASE_BREAK = -.25;
    public static final double LIFT_RELEASE_POSITION = 80;
    public static final double LIFT_TRAVEL_POSITION = 40;
    public static final double LIFT_MIN_POSITION = 0; // Min position allowed for the lift
    public static final double LIFT_MAX_POSITION = 200; // Max position allowed for the lift

    // Scorer Presets TODO: Confirm Scorer Presets
    public static final double SCORER_COLLECT = 0;
    public static final double SCORER_TRAVEL = 0.5;
    public static final double SCORER_SCORE = 1;
    public static final double SCORER_FLAP_OPEN = 0.5;
    public static final double SCORER_FLAP_CLOSED = 1;
}