package se.peferb.security.repository;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import se.peferb.security.model.User;

public class UserRepository {

    private Collection<User> users = new ArrayList<User>();
    private final int hashingIterations = 100000;
    private final int hashSize = 2048;

    public User createUser(String username, String password) {
        byte[] salt = generateSalt();
        byte[] hashedPassword = hashPassword(password,salt, hashingIterations, hashSize);
        User user = new User(username, hashedPassword, salt, hashingIterations);
        users.add(user);
        return user;
    }

    private byte[] generateSalt(){
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[hashSize/8];
        secureRandom.nextBytes(salt);
        return salt;
    }

    private byte[] hashPassword(final String password, final byte[] salt, final int iterations, final int keyLength) throws HashingException {
        try {

            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");

            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, keyLength);

            SecretKey key = skf.generateSecret(spec);

            return key.getEncoded();

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new HashingException(String.format("Cannot hash '%s'", password), e);
        }
    }

    public boolean isAuthorized(String username, String password) throws NotFoundException {
        Optional<User> user = users.stream().filter(u -> u.getUsername().equals(username)).findFirst();
        if (!user.isPresent()) throw new NotFoundException(String.format("Cannot find User '%s'", username));

        byte[] hashedPassword = user.get().getHashedPassword();
        byte[] salt = user.get().getSalt();
        return equalPasswords(password, salt, hashedPassword);
    }

    private boolean equalPasswords(String password, byte[] salt, byte[] hashedPassword) {
        return false;
    }
}
