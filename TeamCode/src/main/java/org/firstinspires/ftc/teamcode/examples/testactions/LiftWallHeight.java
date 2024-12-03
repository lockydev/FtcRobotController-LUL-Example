package org.firstinspires.ftc.teamcode.examples.testactions;

import static dev.locky.lulftc.utils.Tolerance.withinTolerance;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.internal.system.Deadline;

import java.util.concurrent.TimeUnit;

import dev.locky.lulftc.action.InitialLULAction;
import dev.locky.lulftc.action.LULAction;
import dev.locky.lulftc.action.SimpleActionList;

public class LiftWallHeight implements LULAction {
    String name = "Lift Wall Height";

    DcMotorEx liftMotor;

    final int liftTargetPosition = -500;


    public LiftWallHeight(DcMotorEx liftMotor) {
        this.liftMotor = liftMotor;
    }
    @Override
    public void runWhenNext() {
        //actionList.next();
        liftMotor.setTargetPosition(liftTargetPosition);

    }

    @Override
    public void runWhenPrev() {
        this.runWhenNext();
    }

    @Override
    public boolean isCompleteWhenNext() {
        return withinTolerance(liftMotor.getCurrentPosition(), liftTargetPosition, 50 );
    }
    @Override
    public boolean isCompleteWhenPrev() {
        return isCompleteWhenNext();
    }

    @Override
    public void onCompleteWhenNext(SimpleActionList actionList) {
        actionList.next();
    }

    @Override
    public void onCompleteWhenPrev(SimpleActionList actionList) {
        return;
    }

    @Override
    public String getName() {
        return name;
    }
}
