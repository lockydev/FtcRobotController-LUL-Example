package dev.locky.lulftc.action;

public interface LULAction {

    void runWhenNext();
    void runWhenPrev();

    default boolean isCompleteWhenNextAndPeriodic(SimpleActionList actionList) {
        return true;
    }
    default boolean isCompleteWhenPrevAndPeriodic(SimpleActionList actionList) {
        return true;
    }
    String getName();
}
