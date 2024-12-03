/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.examples;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.examples.testactions.LiftDown;
import org.firstinspires.ftc.teamcode.examples.testactions.LiftUp;
import org.firstinspires.ftc.teamcode.examples.testactions.LiftWallHeight;

import dev.locky.lulftc.action.LULAction;
import dev.locky.lulftc.action.SimpleActionList;
import dev.locky.lulftc.controller.GamepadButtons;
import dev.locky.lulftc.controller.GamepadTriggers;
import dev.locky.lulftc.controller.SMARTGamepad;


/*
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When a selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list
 */

@TeleOp(name="SimpleActionListTest", group="LUL-FTC Examples")
public class SimpleActionListTest extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private SMARTGamepad gp1;
    private DcMotorEx liftMotor;
    private SimpleActionList testActionList;


    @Override
    public void runOpMode() {

        gp1 = new SMARTGamepad(gamepad1);

        liftMotor = hardwareMap.get(DcMotorEx.class, "Slides");
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftMotor.setTargetPosition(0);
        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        testActionList = new SimpleActionList( new LULAction[]{
                new LiftDown(liftMotor),
                new LiftWallHeight(liftMotor),
                new LiftUp(liftMotor)
            }, true);


        telemetry.addData("Status", "Initialized");
        telemetry.update();


        // Wait for the game to start (driver presses START)
        waitForStart();
        runtime.reset();
        liftMotor.setPower(0.4);

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            gp1.startUpdate(gamepad1);

            if (gp1.press(GamepadButtons.dpad_up)) {
                testActionList.next();
            } else if (gp1.press(GamepadButtons.dpad_down)) {
                testActionList.prev();
            }

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Action List Index", testActionList.getCurrentActionIndex());
            telemetry.addData("Action List Value", testActionList.getCurrentAction().getName());
            telemetry.addData("Action List Blocked", testActionList.isBlocked());

            telemetry.addData("Lift Target Pos", liftMotor.getTargetPosition());
            telemetry.addData("Lift Current Pos", liftMotor.getCurrentPosition());

            telemetry.update();

            testActionList.endUpdate();

        }
    }
}
