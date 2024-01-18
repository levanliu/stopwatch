package advantest.jobs;

import org.eclipse.core.runtime.jobs.Job;

public class JobManager {
    private static int jobCounter = 0 ;

    public void scheduleJob(Job job){
        job.schedule();
        jobCounter++;
    }

    public static int getJobCount(){
        return jobCounter;
    }

    public void cancelJob(Job job){

    }
}
