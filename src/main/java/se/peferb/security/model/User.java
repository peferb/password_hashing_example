package se.peferb.security.model;

public final class User {

    private final String username;
    private final byte[] hashedPassword;
    private final byte[] salt;
    private final int saltingIterations;

    public User(String username, byte[] hashedPassword, byte[] salt, int saltingIterations) {
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.salt = salt;
        this.saltingIterations = saltingIterations;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("username='").append(username).append('\'');
        sb.append(", \nhashedPassword=");
        if (hashedPassword == null) sb.append("null");
        else {
            sb.append('[');
            for (int i = 0; i < hashedPassword.length; ++i)
                sb.append(i == 0 ? "" : ", ").append(hashedPassword[i]);
            sb.append(']');
        }
        sb.append(", \nsalt=");
        if (salt == null) sb.append("null");
        else {
            sb.append('[');
            for (int i = 0; i < salt.length; ++i)
                sb.append(i == 0 ? "" : ", ").append(salt[i]);
            sb.append(']');
        }
        sb.append(", \nsaltingIterations=").append(saltingIterations);
        sb.append('}');
        return sb.toString();
    }

    public String getUsername() {
        return username;
    }

    public byte[] getHashedPassword() {
        return hashedPassword;
    }

    public byte[] getSalt() {
        return salt;
    }

    public int getSaltingIterations() {
        return saltingIterations;
    }
}
