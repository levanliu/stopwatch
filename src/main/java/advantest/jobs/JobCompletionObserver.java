package advantest.jobs;

import org.apache.commons.lang3.time.StopWatch;

public interface JobCompletionObserver {
    void jobCompleted(String methodName, StopWatch stopWatch);
}