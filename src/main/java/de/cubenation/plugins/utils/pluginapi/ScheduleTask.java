package de.cubenation.plugins.utils.pluginapi;

public class ScheduleTask {
    private Runnable task;
    private Long start;
    private Long repeat;

    public ScheduleTask(Runnable task, Long start, Long repeat) {
        this.task = task;
        this.start = start;
        this.repeat = repeat;
    }

    public ScheduleTask(Runnable task, Long start) {
        this(task, start, null);
    }

    public Runnable getTask() {
        return task;
    }

    public Long getStart() {
        return start;
    }

    public Long getRepeat() {
        return repeat;
    }
}
