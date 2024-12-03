package dev.locky.lulftc.action;

public interface LULAction {

    void runWhenNext();
    void runWhenPrev();

    default boolean isCompleteWhenNext() {
        return true;
    }
    default boolean isCompleteWhenPrev() {
        return true;
    }

    default void onCompleteWhenNext(SimpleActionList actionList) {
        return;
    }
    default void onCompleteWhenPrev(SimpleActionList actionList) {
        return;
    }

    String getName();
}
