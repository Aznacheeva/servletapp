package acccounts;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private static final Map<String, UserProfile> loginToProfile = new HashMap<>();
    private static final Map<String, UserProfile> sessionIdToProfile = new HashMap<>();
    private static final Map<String, String> emailLogin = new HashMap<>();

    public static void addNewUser(UserProfile userModel) {
        loginToProfile.put(userModel.getLogin(), userModel);
        emailLogin.put(userModel.getEmail(),userModel.getLogin());
    }

    public static String getLoginByEmail(String email){
        return emailLogin.get(email);
    }

    public static UserProfile getUserByLogin(String login) {
        return loginToProfile.get(login);
    }

    public static UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public static void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public static void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
}
