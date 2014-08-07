package internship.issuetracker.utils;

import org.junit.Test;

public class EncryptDataTest {

    @Test
    public void testSha256() {
        String password = "parola";
        String expHashPassword = "a80b568a237f50391d2f1f97beaf99564e33d2e1c8a2e5cac21ceda701570312";
        String hashPassword = EncryptData.sha256(password);
        assert (expHashPassword.equals(hashPassword));

    }

};
