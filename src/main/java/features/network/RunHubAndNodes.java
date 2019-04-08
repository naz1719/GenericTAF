package features.network;

import java.io.File;
import java.io.IOException;

public class RunHubAndNodes {

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
            Runtime.getRuntime().exec("cmd /c start runHub.bat",null,  getAbsolutePath("./web-selenium-server/windows"));
            Runtime.getRuntime().exec("cmd /c start runNode1.bat",null ,getAbsolutePath("./web-selenium-server/windows"));
            Runtime.getRuntime().exec("cmd /c start runNode2.bat",null , getAbsolutePath("./web-selenium-server/windows"));
        } else if (isUnix()) {
            Runtime.getRuntime().exec(getAbsolutePath("./web-selenium-server/unix/runHub.sh").getAbsolutePath());
            Runtime.getRuntime().exec(getAbsolutePath("./web-selenium-server/unix/runNode1.sh").getAbsolutePath());
            Runtime.getRuntime().exec(getAbsolutePath("./web-selenium-server/unix/runNode2.sh").getAbsolutePath());
        }
    }

    private static void stopHubAndNodes() {
        if (isWindows()) {
            try {
                Runtime.getRuntime().exec("cmd /c start stopAllServersAndNodes.bat",null,  getAbsolutePath("./web-selenium-server"));
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
