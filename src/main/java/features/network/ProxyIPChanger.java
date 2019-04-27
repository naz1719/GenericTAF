package features.network;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ProxyIPChanger {

    public static void main(String[] args) throws IOException, InterruptedException {
        new ProxyIPChanger().changeIP();
    }

    public void changeIP() throws IOException, InterruptedException {
        File file1 = new File("C:\\Program Files\\OpenVPN\\config");
        Runtime runtime = Runtime.getRuntime();
        runtime.addShutdownHook(new Thread(() -> System.out.println("Shutdown Hook is running !")));
        System.out.println("Application Terminating ...");
        runtime.exec("cmd /c start openvpn uk83.nordvpn.com.udp1194.ovpn", null, file1).waitFor(3, TimeUnit.MINUTES);
//        runtime.exec("cmd.lnk /c start openvpn uk15.nordvpn.com.tcp443.ovpn", null, file1).waitFor(3, TimeUnit.MINUTES);
    }
}
