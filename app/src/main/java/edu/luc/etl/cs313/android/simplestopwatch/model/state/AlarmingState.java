package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import edu.luc.etl.cs313.android.simplestopwatch.R;
import edu.luc.etl.cs313.android.simplestopwatch.android.StopwatchAdapter;

// Alarming State run the ringtone when runtime goes back to zero
// It also makes sure it goes back to the stopped state

class AlarmingState implements StopwatchState {

    public AlarmingState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    @Override
    public void onStartStop() {
        sm.actionStop();
        sm.toStoppedState();
    }

    @Override
    public void updateView() {
        // No implementation here.
    }

    @Override
    public void onTick() {
        if (sm.getRuntime() == 0) {
            alarm(); // Trigger the alarm when runtime reaches 0.
        }
    }

    // play the alarm sound.
    public static void alarm() {
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        if (notification != null) {
            Ringtone r = RingtoneManager.getRingtone(StopwatchAdapter.getAppContext(), notification);
            if (r != null) {
                r.play();
            }
        }

    }

    @Override
    public int getId() {
        return R.string.ALARMING;
    }




}

