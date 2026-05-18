public class App {
    public static void main(String[] args) {
        String password = "S3cretP@ssw0rd";

        // Generate a random salt for this user
        String salt = SecurityUtil.generateSalt();
        String hashedPassword = SecurityUtil.hashPassword(password, salt);

        System.out.println("=== Keamanan Password ===");
        System.out.println("Password asli: " + password);
        System.out.println("Salt (Base64): " + salt);
        System.out.println("Hash (Base64): " + hashedPassword);

        // Verifikasi password
        String loginPassword = "S3cretP@ssw0rd";
        boolean valid = SecurityUtil.verifyPassword(loginPassword, hashedPassword, salt);
        System.out.println("Verifikasi login berhasil: " + valid);
    }
}
