package dev.locky.lulftc.action;

public class SimpleActionList {

    private LULAction[] actionList;
    private int currentActionIndex;
    private final boolean cyclic;
    private ActionDirection currentDirection = ActionDirection.NONE;
    private boolean blocked = false;

    public SimpleActionList(LULAction[] actionList, boolean cyclic) {
        this(actionList,  cyclic, 0);
    }
    public SimpleActionList(LULAction[] actionList, boolean cyclic, int initialActionIndex) {

        if (actionList.length == 1) throw new RuntimeException("Action list must have length greater than 1");

        this.actionList = actionList;
        this.cyclic = cyclic;
        currentActionIndex = initialActionIndex;

        if (!InitialLULAction.class.isAssignableFrom(actionList[0].getClass())) throw new RuntimeException("Initial action (default is first action) must implement InitialLULAction");
        ((InitialLULAction) actionList[0]).initRun();
    }

    public void next() {
        if (blocked) return;
        if ( currentActionIndex + 1 >= actionList.length && !cyclic  ) return;

        currentActionIndex += 1;
        if (cyclic) currentActionIndex = currentActionIndex % actionList.length;

        getCurrentAction().runWhenNext();
        currentDirection = ActionDirection.NEXT;
        blocked = true;
    }

    public void prev() {
        if (blocked) return;
        if ( currentActionIndex == 0 && !cyclic ) return;

        currentActionIndex -= 1;
        if (cyclic && currentActionIndex < 0) currentActionIndex = actionList.length - 1;

        getCurrentAction().runWhenPrev();
        currentDirection = ActionDirection.PREV;
        blocked = true;
    }

    public void endUpdate() {
        if (this.currentDirection == ActionDirection.NEXT) {
            if (this.getCurrentAction().isCompleteWhenNextAndPeriodic(this)) {
                blocked = false;
            }
        } else if (this.currentDirection == ActionDirection.PREV) {
            if (this.getCurrentAction().isCompleteWhenPrevAndPeriodic(this)) {
                blocked = false;
            }
        }
    }

    public LULAction getCurrentAction() {
        return actionList[currentActionIndex];
    }

    public int getCurrentActionIndex() {
        return currentActionIndex;
    }

    public LULAction[] getActionList() {
        return actionList;
    }
    public boolean isBlocked() {
        return blocked;
    }

    public ActionDirection getCurrentDirection() {
        return currentDirection;
    }

}
