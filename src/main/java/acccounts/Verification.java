package acccounts;

import java.io.File;

public class Verification {

    public static void CheckDir(File file) {
        if(!file.exists()) file.mkdir();
    }

    public static boolean DirectoryIsInvisible(String login, String homeDir, String dir) {
        String[] homeDirectoryDecomposition = homeDir.split("\\\\");
        String[] directoryDecomposition = dir.split("\\\\");
        return directoryDecomposition.length <= homeDirectoryDecomposition.length
                || !directoryDecomposition[homeDirectoryDecomposition.length]
                .equals(login);
    }

    public static boolean SessionIsExist(String sessionId) {
        return AccountService.getUserBySessionId(sessionId) != null;
    }
}
