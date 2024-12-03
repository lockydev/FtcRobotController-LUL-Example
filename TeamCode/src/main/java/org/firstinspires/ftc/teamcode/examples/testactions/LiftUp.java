package org.firstinspires.ftc.teamcode.examples.testactions;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.internal.system.Deadline;

import java.util.concurrent.TimeUnit;

import dev.locky.lulftc.action.LULAction;

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
    public boolean isCompleteWhenNext() {
        return wait.hasExpired();
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
