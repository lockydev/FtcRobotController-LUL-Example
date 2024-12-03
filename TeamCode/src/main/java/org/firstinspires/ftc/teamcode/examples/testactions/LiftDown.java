package org.firstinspires.ftc.teamcode.examples.testactions;

import static dev.locky.lulftc.utils.Tolerance.withinTolerance;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import dev.locky.lulftc.action.InitialLULAction;
import dev.locky.lulftc.action.LULAction;
import dev.locky.lulftc.action.SimpleActionList;

public class LiftDown implements LULAction, InitialLULAction {
     private final String name = "Lift Down";

    DcMotorEx liftMotor;

    public LiftDown(DcMotorEx liftMotor) {
        this.liftMotor = liftMotor;
    }
    @Override
    public void initRun() {
        liftMotor.setTargetPosition(0);
    }
    @Override
    public void runWhenNext() {
        this.initRun();
    }

    @Override
    public void runWhenPrev() {
        this.initRun();
    }

    @Override
    public boolean isCompleteWhenNextAndPeriodic(SimpleActionList actionList) {
        return withinTolerance(liftMotor.getCurrentPosition(), 0, 50 );
    }
    @Override
    public boolean isCompleteWhenPrevAndPeriodic(SimpleActionList actionList) {
        return this.isCompleteWhenNextAndPeriodic(actionList);
    }

    @Override
    public String getName() {
        return name;
    }


}
