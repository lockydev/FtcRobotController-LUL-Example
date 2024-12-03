package org.firstinspires.ftc.teamcode.examples.testactions;

import static dev.locky.lulftc.utils.Tolerance.withinTolerance;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import dev.locky.lulftc.action.InitialLULAction;
import dev.locky.lulftc.action.LULAction;

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
    public boolean isCompleteWhenNext() {
        return withinTolerance(liftMotor.getCurrentPosition(), 0, 50 );
    }
    @Override
    public boolean isCompleteWhenPrev() {
        return this.isCompleteWhenNext();
    }

    @Override
    public String getName() {
        return name;
    }


}
