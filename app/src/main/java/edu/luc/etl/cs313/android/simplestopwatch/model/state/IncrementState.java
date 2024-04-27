package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

public class IncrementState implements StopwatchState{

    private final StopwatchSMStateView sm;

    public IncrementState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }
    @Override
    public void onStartStop() {
        sm.actionInc();
        sm.toIncrementState();
    }

    @Override
    public void onTick() {
        int delay = sm.getDelay();
        delay++;
        sm.setDelay(delay);

        if (sm.maxReached() || delay == 3) {
            sm.actionRingTheAlarm();
            sm.toRunningState();
        }
    }

    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.INCREMENT;
    }
}
