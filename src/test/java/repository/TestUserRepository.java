package repository;

import org.junit.Test;
import se.peferb.security.model.User;
import se.peferb.security.repository.UserRepository;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class TestUserRepository {

    private final UserRepository repo = new UserRepository();

    @Test
    public void canVerifyUserPassword() {
        String username = "username";
        String password = "password";
        User user = repo.createUser(username, password);
        assertTrue(repo.isAuthorized(user.getUsername(), password));
    }

    @Test
    public void canCreateUser() {
        User user = repo.createUser("Username", "Password");
        assertNotNull(user.getHashedPassword());
        assertNotNull(user.getSalt());
        assertNotNull(user.getSaltingIterations());
    }
}
