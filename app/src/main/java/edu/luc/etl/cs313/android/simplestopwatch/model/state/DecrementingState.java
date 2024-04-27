package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

// Initially Called the Running State
// We changed it to decrement from the increment state to zero
// We changed to transition to the alarm state when runtime is zero
class DecrementingState implements StopwatchState {

    public DecrementingState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    @Override
    public void onStartStop() {
        sm.actionStop();
        sm.toStoppedState();
        // Reset the timer if runtime is greater than 0.
        if (sm.getRuntime() > 0) {
            sm.actionReset();
        }
    }


    @Override
    public void onTick() {
        // Transition to AlarmState if runtime is 0.
        if (sm.getRuntime() == 0) {
            sm.toAlarmState();
        } else {
            // Decrement the timer value.
            sm.actionDec();
            // Update the view with the new runtime value.
            sm.actionUpdateView();
        }
    }

    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.DECREMENTING;
    }
}
