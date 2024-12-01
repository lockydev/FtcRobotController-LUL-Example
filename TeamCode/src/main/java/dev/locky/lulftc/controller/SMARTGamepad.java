package dev.locky.lulftc.controller;

import com.qualcomm.robotcore.hardware.Gamepad;

public class SMARTGamepad  {
    private Gamepad previousGamepad = new Gamepad();
    private Gamepad currentGamepad = new Gamepad();
    public SMARTGamepad(Gamepad gamepad) {
        this.startUpdate(gamepad);
    }

    public void startUpdate(Gamepad gamepad) {
        Gamepad gp = new Gamepad();
        gp.fromByteArray(gamepad.toByteArray());
        this.previousGamepad = this.currentGamepad;
        this.currentGamepad = gp;
    }

    public Gamepad getPreviousGamepadState() {

        return previousGamepad;
    }

    public boolean press(GamepadButtons button) {
        try {
            return !(boolean) previousGamepad.getClass().getField(button.name()).get(previousGamepad) && (boolean) currentGamepad.getClass().getField(button.name()).get(currentGamepad);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return false;
        }
    }

    public boolean press(GamepadEmulatedButtons button) {
        return this.press(button, 0.5F);
    }
    public boolean press(GamepadEmulatedButtons button, float threshold) {
        try {
            return !((float) previousGamepad.getClass().getField(button.name()).get(previousGamepad) > threshold)
                    && (float) currentGamepad.getClass().getField(button.name()).get(currentGamepad) > threshold;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return false;
        }
    }

    public boolean release(GamepadButtons button) {
        try {
            return (boolean) previousGamepad.getClass().getField(button.name()).get(previousGamepad) && !(boolean) currentGamepad.getClass().getField(button.name()).get(currentGamepad);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return false;
        }
    }

    public boolean release(GamepadEmulatedButtons button) {
        return this.release(button, 0.5F);
    }
    public boolean release(GamepadEmulatedButtons button, float threshold) {
        try {
            return (float) previousGamepad.getClass().getField(button.name()).get(previousGamepad) > threshold
                    && !((float) currentGamepad.getClass().getField(button.name()).get(currentGamepad) > threshold);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return false;
        }
    }

    public boolean down(GamepadEmulatedButtons button) {
        return this.down(button, 0.5F);
    }
    public boolean down(GamepadEmulatedButtons button, float threshold) {
        try {
            return (float) currentGamepad.getClass().getField(button.name()).get(currentGamepad) > threshold;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return false;
        }
    }




}
