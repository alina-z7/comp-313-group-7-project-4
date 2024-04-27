package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class RunningState implements StopwatchState {

    public RunningState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    @Override
    public void onStartStop() {
        sm.actionStop();
        sm.toStoppedState();
        if (sm.getRuntime() > 0) {
            sm.actionReset(); // Reset the timer if runtime is greater than 0.
        }
    }


    @Override
    public void onTick() {
        if (sm.getRuntime() == 0) {
            sm.toAlarmState(); // Transition to AlarmState if runtime is 0.
        } else {
            sm.actionDec(); // Decrement the timer value.
            sm.actionUpdateView(); // Update the view with the new runtime value.
        }
    }

    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.RUNNING;
    }
}
