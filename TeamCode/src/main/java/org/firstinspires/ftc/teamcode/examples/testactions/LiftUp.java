package org.firstinspires.ftc.teamcode.examples.testactions;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.internal.system.Deadline;

import java.util.concurrent.TimeUnit;

import dev.locky.lulftc.action.InitialLULAction;
import dev.locky.lulftc.action.LULAction;
import dev.locky.lulftc.action.SimpleActionList;

public class LiftUp implements LULAction {
    String name = "Lift Up";

    DcMotorEx liftMotor;
    Deadline wait;

    public LiftUp(DcMotorEx liftMotor) {
        this.liftMotor = liftMotor;
    }
    @Override
    public void runWhenNext() {
        liftMotor.setTargetPosition(-1000);
        wait = new Deadline(5, TimeUnit.SECONDS);
    }

    @Override
    public void runWhenPrev() {
        this.runWhenNext();
    }

    @Override
    public boolean isCompleteWhenNextAndPeriodic(SimpleActionList actionList) {
        return wait.hasExpired();
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
