package org.modvox.data;

import java.util.ArrayList;

public class StageableChangeList<T> extends ArrayList<T> {

    private ArrayList<Change> stagedChangeList = new ArrayList<>();

    public void stageAdd(T object) {
        stagedChangeList.add(new Change(object, Action.ADD));
    }

    public void stageRemove(T object) {
        stagedChangeList.add(new Change(object, Action.REMOVE));
    }

    public void executeStagedChanges() {
        for(Change change : stagedChangeList)
            if(change.action == Action.ADD)
                add(change.object);
            else if(change.action == Action.REMOVE)
                remove(change.object);

            stagedChangeList.clear();
    }

    public boolean hasStagedChanges() {
        return stagedChangeList.size() > 0;
    }

    private class Change {

        T object;
        Action action;

        Change(T object, Action action) {
            this.object = object;
            this.action = action;
        }
    }

    private enum Action {
        ADD,
        REMOVE
    }
}
