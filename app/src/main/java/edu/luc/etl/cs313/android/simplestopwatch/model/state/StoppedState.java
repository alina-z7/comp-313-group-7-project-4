package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

// When the button is clicked, it transitions to the increment state

class StoppedState implements StopwatchState {

    public StoppedState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    // Transition the Stop State to the IncrementState
    //
    @Override
    public void onStartStop() {
        if (sm.getRuntime() == 0) {
            // If runtime is 0, reset the timer, start it, increment the action, and transition to IncrementState.
            sm.actionReset();
            sm.actionStart();
            sm.actionInc();
            sm.toIncrementState();
        } else if (sm.getRuntime() > 0) {
            // If runtime is greater than 0, start the timer and transition to RunningState.
            sm.actionStart();
            sm.toDecrementState();
        }
    }



    @Override
    public void onTick() {
        throw new UnsupportedOperationException("onTick");
    }

    // Update the view with the current runtime instead of the laptime.
    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.STOPPED;
    }
}
