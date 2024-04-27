package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class AlarmingState implements StopwatchState {

    public AlarmingState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    @Override
    public void onStartStop() {
        sm.actionStart();
        sm.toIncrementState();
    }

    @Override
    public void updateView() {
        // No implementation here.
    }

    @Override
    public void onTick() {
        throw new UnsupportedOperationException("onTick");
    }


    @Override
    public int getId() {
        return R.string.ALARMING;
    }
}


