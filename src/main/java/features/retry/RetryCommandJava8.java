package features.retry;

import features.wait.DelaySteps;
import org.testng.Assert;

import java.util.function.Supplier;

public class RetryCommandJava8<T> {
    private int retryCounter;
    private int maxRetries;
    private Integer delayBetweenRetryInSec;

    public RetryCommandJava8(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public RetryCommandJava8(int maxRetries, int delayBetweenRetryInSec) {
        this.maxRetries = maxRetries;
        this.delayBetweenRetryInSec = delayBetweenRetryInSec;
    }

    // Takes a function and executes it, if fails, passes the function to the retry command
    public T run(Supplier<T> function) throws InterruptedException {
        try {
            return function.get();
        } catch (Throwable e) {
            return retry(function);
        }
    }

    public int getRetryCounter() {
        return retryCounter;
    }

    private T retry(Supplier<T> function) throws RuntimeException, InterruptedException {
        System.out.println("FAILED - Command failed, will be retried " + maxRetries + " times.");
        retryCounter = 0;
        Throwable lastException = null;
        while (retryCounter < maxRetries) {
            try {
                if (delayBetweenRetryInSec != null) {
                    DelaySteps.getInstance().delayStep(delayBetweenRetryInSec, "delay between retry in seconds ");
                }
                return function.get();
            } catch (Throwable ex) {
                lastException = ex;
                retryCounter++;
                System.out.println("FAILED - Command failed on retry " + retryCounter + " of " + maxRetries + " error: " + ex);
                if (retryCounter >= maxRetries) {
                    System.out.println("Max retries exceeded.");
                    break;
                }
            }
        }
        //throw new RuntimeException("Command failed on all of " + maxRetries + " retries");
        Assert.fail("Command failed on all of " + maxRetries + " retries, last error: " + lastException.getMessage());
        return null;

    }
}
