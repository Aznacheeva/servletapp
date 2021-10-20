package acccounts;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private static final Map<String, UsersDataSet> sessionIdToProfile = new HashMap<>();

    public static UsersDataSet getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public static void addSession(String sessionId, UsersDataSet user) {
        sessionIdToProfile.put(sessionId, user);
    }

    public static void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
}
