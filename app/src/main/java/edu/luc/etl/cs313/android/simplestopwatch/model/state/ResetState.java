package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

public class ResetState implements StopwatchState {

    private final StopwatchSMStateView sm;

    public ResetState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }
    @Override
    public void onStartStop() {
        sm.actionStart();
        sm.toIncrementState();
    }

    @Override
    public void onTick() {
    }

    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.RESET;
    }
}
