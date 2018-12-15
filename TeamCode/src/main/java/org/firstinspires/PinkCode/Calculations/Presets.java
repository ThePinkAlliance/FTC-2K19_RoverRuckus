package org.firstinspires.PinkCode.Calculations;

// Abstract Class to Define Preset Values Used Throughout Subsystems
public abstract class Presets {
    // Collector Presets TODO: Confirm Collector Presets
    public static final double COLLECTOR_COLLECT_POWER = 1; // Power Sent to Motor While Collecting
    public static final double COLLECTOR_EJECT_POWER = -1; // Power Sent to Motor While Ejecting
    public static final double COLLECTOR_COLLECT_POSITION = -105; // Was 25 in positive world
    public static final double COLLECTOR_TRAVEL_POSITION = -75; // Was 55 in positive world
    public static final double COLLECTOR_SORT_POSITION = -35; // Was 95 in positive world
    public static final double COLLECTOR_STOW_POSITION = 0;
    static final double COLLECTOR_ROTATE_Kp = .03; //
    static final double COLLECTOR_ROTATE_Kd = 0.003; // Kd for Lift PD
    static final double COLLECTOR_ROTATE_MIN_POWER = -1;
    static final double COLLECTOR_ROTATE_MAX_POWER = 1;
    public static final double COLLECTOR_ROTATE_MIN_POSITION = -130;
    public static final double COLLECTOR_ROTATE_MAX_POSITION = 0;

    // Extender Presets TODO: Confirm Extender Presets
    public static final double EXTEND_COLLECT_POSITION = 1600; // Position for Collecting From Crater
    public static final double EXTEND_CRATER_POSITION = 1200; // Position Barely Outside Crater Plane
    public static final double EXTEND_SORT_POSITION = 0; // Retracted Position for Transfer to Lift
    public static final double EXTEND_GOLD_POSITION = 1400; // Position to Knock Gold if on Side
    public static final double EXTEND_MID_GOLD_POSITION = 1300; // Position to Knock Gold if in Mid
    public static final double EXTEND_MIN_POSITION = 0; // Min position allowed for the extender
    public static final double EXTEND_MAX_POSITION = 1600; // Max position allowed for the extender
    static final double EXTEND_Kp = .004; // Kp for Extender PD
    static final double EXTEND_Kd = 0.001; // Kd for Extender PD
    static final double EXTEND_MIN_POWER = -1; // Most Power Sent While Retracting Extender
    static final double EXTEND_MAX_POWER = 1; // Most Power Sent While Extending Extender

    // Lift Presets TODO: Confirm Lift Presets
    public static final double LIFT_SCORE_POSITION = 4400; // Raised Position for Scoring in Lander
    public static final double LIFT_CLEAR_POSITION = 3000;
    public static final double LIFT_LANDED_POSITION = 1900;
    public static final double LIFT_SORT_POSITION = 0; // Lowered Position for Collection from Sort
    public static final double LIFT_RELEASE_BREAK = -.25;
    public static final double LIFT_RELEASE_POSITION = 1800;
    static final double LIFT_Kp = .01; // Kp for Lift PD
    static final double LIFT_Kd = 0.001; // Kd for Lift PD
    static final double LIFT_MIN_POWER = -1; // Most Power Sent While Lowering Lift
    static final double LIFT_MAX_POWER = 1; // Most Power Sent While Raising Lift

    // Scorer Presets TODO: Confirm Scorer Presets
    public static final double SCORER_COLLECT = 1;
    public static final double SCORER_SCORE = 0;
    public static final double SCORER_FLAP_OPEN = 0.5;
    public static final double SCORER_FLAP_CLOSED = 1;
    public static final double SCORER_KICKER_STOW = 0;
    public static final double SCORER_KICKER_KICK = 0.5;

    public static final double HOOK_LATCH_POSITION = 0;
    public static final double HOOK_RELEASE_POSITION = 0.5;
}