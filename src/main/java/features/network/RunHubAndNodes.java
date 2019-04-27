package features.network;

import java.io.File;
import java.io.IOException;

public class RunHubAndNodes {

    public static final String COMMON_PATH = "./testData/web-selenium-server";
    private static String operatingSystem = System.getProperty("os.name").toLowerCase();

    private static File getAbsolutePath(String filePath) {
        File file = new File(filePath);
        return new File(file.getAbsolutePath());
    }

    private static boolean isWindows() {
        return operatingSystem.contains("win");
    }

    private static boolean isUnix() {
        return operatingSystem.contains("nix") || operatingSystem.contains("nux") || operatingSystem.contains("aix");

    }

    private static void runHubAndNodes() throws IOException {
        if (isWindows()) {
            Runtime.getRuntime().exec("cmd /c start runHub.bat", null, getAbsolutePath(COMMON_PATH + "/windows"));
            Runtime.getRuntime().exec("cmd /c start runNode1.bat", null, getAbsolutePath(COMMON_PATH + "/windows"));
            Runtime.getRuntime().exec("cmd /c start runNode2.bat", null, getAbsolutePath(COMMON_PATH + "/windows"));
        } else if (isUnix()) {
            Runtime.getRuntime().exec(getAbsolutePath(COMMON_PATH + "/unix/runHub.sh").getAbsolutePath());
            Runtime.getRuntime().exec(getAbsolutePath(COMMON_PATH + "/unix/runNode1.sh").getAbsolutePath());
            Runtime.getRuntime().exec(getAbsolutePath(COMMON_PATH + "/unix/runNode2.sh").getAbsolutePath());
        }
    }

    private static void stopHubAndNodes() {
        if (isWindows()) {
            try {
                Runtime.getRuntime().exec("cmd /c start stopAllServersAndNodes.bat", null, getAbsolutePath(COMMON_PATH));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            runHubAndNodes();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        stopHubAndNodes();
    }
}
