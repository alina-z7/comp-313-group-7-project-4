package edu.luc.etl.cs313.android.simplestopwatch.model.state;

/**
 * The restricted view states have of their surrounding state machine.
 * This is a client-specific interface in Peter Coad's terminology.
 *
 * @author laufer
 */
interface StopwatchSMStateView {

    // transitions
    void toRunningState();
    void toStoppedState();
    void toIncrementState();
    void toResetState();


    // actions
    void actionInit();
    void actionReset();
    void actionStart();
    void actionStop();
    void actionInc();
    void actionDec();
    void actionUpdateView();
    void actionRingTheAlarm();

    // transition special cases
    boolean decCount();
    boolean maxReached();
    int getDelay();
    void setDelay(int delay);

    // state-dependent UI updates
    void updateUIRuntime();
}
