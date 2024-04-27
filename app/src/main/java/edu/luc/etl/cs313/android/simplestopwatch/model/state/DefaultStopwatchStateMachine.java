package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.android.StopwatchAdapter;
import edu.luc.etl.cs313.android.simplestopwatch.common.StopwatchModelListener;
import edu.luc.etl.cs313.android.simplestopwatch.model.clock.ClockModel;
import edu.luc.etl.cs313.android.simplestopwatch.model.time.TimeModel;

/**
 * An implementation of the state machine for the stopwatch.
 *
 * @author laufer
 */
public class DefaultStopwatchStateMachine implements StopwatchStateMachine {

    public DefaultStopwatchStateMachine(final TimeModel timeModel, final ClockModel clockModel, StopwatchModelListener uiUpdateListener) {
        this.timeModel = timeModel;
        this.clockModel = clockModel;
        this.uiUpdateListener = uiUpdateListener;
    }

    private final TimeModel timeModel;

    private final ClockModel clockModel;

    private StopwatchModelListener uiUpdateListener;

    /**
     * The internal state of this adapter component. Required for the State pattern.
     */
    private StopwatchState state;

    int delay = 0;

    protected void setState(final StopwatchState state) {
        this.state = state;
        listener.onStateUpdate(state.getId());
    }

    private StopwatchModelListener listener;
    @Override
    public void setUIUpdateListener(StopwatchModelListener uiUpdateListener) {
        this.uiUpdateListener = uiUpdateListener;
    }

    // forward event uiUpdateListener methods to the current state
    // these must be synchronized because events can come from the
    // UI thread or the timer thread
    @Override public synchronized void onStartStop() {
        state.onStartStop();
    }
    @Override public synchronized void onTick() {
        state.onTick();
    }

    @Override public void updateUIRuntime() {
        listener.onTimeUpdate(timeModel.getRuntime());
    }

    // known states
    private final StopwatchState STOPPED = new StoppedState(this);
    private final StopwatchState RUNNING = new RunningState(this);
    private final StopwatchState INCREMENT = new IncrementState(this);
    private final StopwatchState RESET = new ResetState(this);

    // transitions
    @Override public void toRunningState() {
        setState(RUNNING);
    }
    @Override public void toStoppedState() {
        setState(STOPPED);
    }

    @Override
    public void toIncrementState() {
        setState(INCREMENT);
    }

    @Override
    public void toResetState() {
        setState(RESET);
    }


    // actions
    @Override public void actionInit() {
        toStoppedState();
        actionReset();
    }
    @Override public void actionReset() {
        timeModel.resetRuntime();
        actionUpdateView();
    }
    @Override public void actionStart() {
        clockModel.start();
    }
    @Override public void actionStop() {
        clockModel.stop();
    }

    @Override public void actionInc() {
        timeModel.incRuntime();
        delay = 0;
        actionUpdateView();
    }

    @Override
    public void actionDec() {
        timeModel.decRuntime();
        actionUpdateView();
    }

    @Override public void actionUpdateView() {
        state.updateView();
    }

    @Override
    public void actionRingTheAlarm() {
        uiUpdateListener.playDefaultNotification();
    }

    @Override
    public boolean decCount() {
        return false;
    }

    @Override
    public boolean maxReached() {
        return false;
    }

    @Override
    public int getDelay() {
        return 0;
    }

    @Override
    public void setDelay(int delay) {

    }
}
