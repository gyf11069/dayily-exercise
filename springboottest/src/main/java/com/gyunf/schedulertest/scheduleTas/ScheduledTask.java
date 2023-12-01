package com.gyunf.schedulertest.scheduleTas;

import java.util.concurrent.ScheduledFuture;

/**
 * @author gyf
 * @date 2023-11-13 16:06
 */
public final class ScheduledTask {

    volatile ScheduledFuture<?> future;

    /**
     * 取消定时任务
     */
    public void cancel() {
        ScheduledFuture<?> future = this.future;
        if (future != null) {
            future.cancel(true);
        }
    }
}
