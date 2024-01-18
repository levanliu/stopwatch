package advantest.jobs;

import org.apache.commons.lang3.time.StopWatch;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

public class SubJob extends Job  {
    private String methodName;
    private JobCompletionObserver observer;
    private StopWatch stopWatch;

    public SubJob(String name, String methodName, JobCompletionObserver observer) {
        super(name);
        this.methodName = methodName;
        this.observer = observer;
    }

    @Override
    protected IStatus run(IProgressMonitor monitor) {
        // Create a StopWatch instance
        stopWatch = new StopWatch();
        stopWatch.start();

        // Simulate some work
        try {
            Thread.sleep(500); // Sleep for 0.5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stopWatch.stop();

        if (observer != null) {
            observer.jobCompleted(methodName,stopWatch);
        }

        return Status.OK_STATUS;
    }
}
