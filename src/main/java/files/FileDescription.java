package files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FileDescription {
    private final String fileName;
    private final String URL;
    private final String length;
    private final long fileLength;
    private final String date;

    public FileDescription(String url, File file) throws IOException {
        if(file.isDirectory()) {
            this.URL = url+"?path="+file.getAbsolutePath().replace(' ','+');
            this.fileName = "📁"+file.getName().substring(0, Math.min(file.getName().length(), 20));
        }
        else {
            this.URL = "download"+("?path="+ Paths.get(file.getPath()))
                    .replace(' ','+');
            this.fileName = "📄" + file.getName()
                    .substring(0,Math.min(file.getName().length(), 20));
        }
        this.fileLength = file.length();
        this.length = fileLength != 0 ? fileLength+"B" : "";
        this.date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                .format(new Date(Files.readAttributes(Paths.get(file.getAbsolutePath()),
                        BasicFileAttributes.class).creationTime().toMillis()));
    }

    public static ArrayList<FileDescription> getArray(File[] files, String url) {
        if (files == null)
            files = new File[0];
        ArrayList<FileDescription> result = new ArrayList<>();
        for (File file : files) {
            try {
                result.add(new FileDescription(url, file));
            } catch (IOException ignored) {
            }
        }
        return result;
    }

    public String getURL() {
        return URL;
    }

    public String getLength() {
        return length;
    }

    public String getFileName() {
        return fileName;
    }

    public String getDate() {
        return date;
    }

    public long getFileLength() {
        return fileLength;
    }
}
