package adaptation.parsers.file;

import io.restassured.internal.util.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static definition.constants.CommonConsts.FOLDER_PATH;

public class FileUtilsWrapper {

    public static File getFile(String filePath) throws Exception {
        File file = new File(filePath);
        if (!file.exists() || !file.canRead()) {
            throw new Exception("ERROR : Sorry the file :" + file.getAbsolutePath() + " is missing");
        }
        return file;
    }

    public static String fileToString(String fileLocation) {
        try {
            return new String(IOUtils.toByteArray(ClassLoader.getSystemResourceAsStream(fileLocation)));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static void saveFileFromUrl(String url, String fileName) throws MalformedURLException, IOException {
        org.apache.commons.io.FileUtils.copyURLToFile(new URL(url), new File(fileName));
    }

    public static boolean excelMatcher(String input) {
        final Pattern pattern = Pattern.compile("(.*)(.(xlsx|xlx|xls))");
        final Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static List<String> getExcelList() {
        final File folder = new File(FOLDER_PATH);
        return listFilesForFolder(folder);
    }

    public static List<String> listFilesForFolder(final File folder) {
        List<String> list = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                if (excelMatcher(fileEntry.getName())) {
                    list.add(fileEntry.getName());
                }
            }
        }
        return list;
    }

    public static String getFirstPartOfStream(String input) {
        final Pattern pattern = Pattern.compile("([a-zA-Z0-9]+)-([a-zA-Z0-9]+)");
        final Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return input;
    }

    public static void copyFile(String source, String dest) {
        try {
            org.apache.commons.io.FileUtils.copyFileToDirectory(new File(source), new File(dest));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createHtmlFile(String string, final String path, String source) {

        try {
            File path1 = new File(path + string + ".html");
            org.apache.commons.io.FileUtils.writeStringToFile(path1, source);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeLineToFile(List<String> list, String path) {
        list.forEach(name -> {
            Path p = Paths.get(path + "\\" + name);
            String s = System.lineSeparator() + "\nauth-user-pass auth.txt";
            try {
                Files.write(p, s.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.err.println(e);
            }
        });
    }
}
