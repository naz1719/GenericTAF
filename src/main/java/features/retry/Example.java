package features.retry;

public class Example {

    public static void main(String[] args) throws Exception {
        int maxRetries = 18;
        int delayBetweenRetryInSec = 10;
        String id = "111";
        String a = new RetryCommandJava8<String>(maxRetries, delayBetweenRetryInSec).run(() -> getThatThing(id));
    }


    public static String getThatThing(String id) {
        //Do Something
        System.out.println("Retry");
        if (true) {
            throw new RuntimeException("Fail");
        }
        return "";
    }
}