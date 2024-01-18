package advantest;

import org.apache.commons.lang3.time.StopWatch;
import advantest.jobs.AddJob;
import advantest.jobs.JobCompletionObserver;
import advantest.jobs.JobManager;
import advantest.jobs.SubJob;

import java.util.concurrent.CountDownLatch;

public class App implements JobCompletionObserver {
    private static long totalCostTime = 0;
    private static CountDownLatch latch;

    public static void main(String[] args) throws InterruptedException {
        App app = new App();
        JobManager jobManager = new JobManager();

        for (int i = 0; i < 100; i++) {
            AddJob job1 = new AddJob("Demo AddJob " + i, "addMethod" + i, app);
            SubJob job2 = new SubJob("Demo SubJob" + i, "subMethod" + i, app);

            jobManager.scheduleJob(job1);
            jobManager.scheduleJob(job2);
        }

        latch = new CountDownLatch(JobManager.getJobCount());

        // Wait for all jobs to complete
        latch.await();

        System.out.println("Total cost time: " + totalCostTime);
    }

    @Override
    public synchronized void jobCompleted(String methodName, StopWatch stopWatch) {
        totalCostTime += stopWatch.getTime();
        System.out.println(methodName + " Cost: " + stopWatch.getTime() + " milliseconds");
        latch.countDown();
    }
}
