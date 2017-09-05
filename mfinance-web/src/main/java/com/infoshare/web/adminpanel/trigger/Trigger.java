package com.infoshare.web.adminpanel.trigger;

import com.infoshare.web.adminpanel.agentservice.Task;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.*;

public class Trigger implements ITerminable {

    private final ScheduledExecutorService scheduler =
            Executors.newSingleThreadScheduledExecutor();
    private ScheduledFuture<?> actionHandle;
    private ITriggerable actionProvider;

    private long delay;
    private long period;
    private TimeUnit timeUnit;

    private Task triggeredTask;

    public void setTriggeredTask(Task triggeredTask) {
        this.triggeredTask = triggeredTask;
    }

    public Trigger(ITriggerable actionProvider, long delay, long period, TimeUnit timeUnit) {

        this.actionProvider = actionProvider;
        this.delay = delay;
        this.period = period;
        this.timeUnit = timeUnit;
    }

    @Override
    public long getTaskId() {
        return triggeredTask.getId();
    }

    public void startAction() {

        Runnable trigger = new Runnable() {
            public void run() {
                actionProvider.executeAction();
            }
        };
        this.actionHandle = scheduler.scheduleAtFixedRate(trigger, delay, period, timeUnit);
    }

    public void killAction() {

        scheduler.schedule(new Runnable() {
            public void run() {
                actionHandle.cancel(true);
            }
        }, 0, SECONDS);
    }
}


