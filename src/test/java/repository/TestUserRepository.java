package repository;

import org.junit.Test;
import se.peferb.security.model.User;
import se.peferb.security.repository.UserRepository;

public class TestUserRepository {

    private final UserRepository repo = new UserRepository();

    @Test
    public void canCreateUser() {
        User user = repo.createUser("Username", "Password");
        System.out.println(user);
    }
}
