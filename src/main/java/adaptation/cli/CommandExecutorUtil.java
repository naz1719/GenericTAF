package adaptation.cli;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandExecutorUtil {

    protected static final Logger LOG = Logger.getLogger(CommandExecutorUtil.class);

    public static String executeCommand(String command) {
        StringBuilder output = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                output.append(line).append("\n");
            }
        } catch (Exception e) {
            LOG.warn(e.getMessage());
        }
        return output.toString();
    }
}
