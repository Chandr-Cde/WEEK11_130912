package week11.chandra.id.ac.umn;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exceptions.AuthenticationException;
import exceptions.ExcessiveFailedLoginException;
import exceptions.InvalidPropertyException;

public class Main {
    private static List<User> listOfUser = new ArrayList<>();

    public static void main(String[] args) {
        initialize();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu Utama");
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.print("Pilihan: ");
            String choice = scanner.nextLine();

            try {
                if (choice.equals("1")) {
                    System.out.println("\nMenu login");
                    System.out.print("Username: ");
                    String username = scanner.nextLine();
                    System.out.print("Password: ");
                    String password = scanner.nextLine();

                    handleLogin(username, password);

                } else if (choice.equals("2")) {
                    System.out.println("\nMenu Sign Up");
                    handleSignUp(scanner);
                } else {
                    System.out.println("Pilihan tidak valid");
                }

            } catch (ExcessiveFailedLoginException e) {
                System.out.println(e.getMessage());
            } catch (AuthenticationException e) {
                System.out.println(e.getMessage());
            } catch (InvalidPropertyException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan sistem");
            }
        }
    }

    public static void initialize() {
        User admin = new User(
            "John",
            "Doe",
            'L',
            "Jl. Merpati No. 1 RT 1 RW 1, Banten",
            "admin",
            "admin"
        );
        listOfUser.add(admin);
    }

    public static void handleLogin(String username, String password) throws AuthenticationException, ExcessiveFailedLoginException {
        for (User user : listOfUser) {
            if (user.getUserName().equals(username)) {
                if (user.login(username, password)) {
                    System.out.println(user.greeting());
                    return;
                } else {
                    throw new AuthenticationException("Username / password salah");
                }
            }
        }
        throw new AuthenticationException("Username / password salah");
    }

    public static void handleSignUp(Scanner scanner) throws InvalidPropertyException {
        System.out.print("Nama Depan: ");
        String namaDepan = scanner.nextLine();

        System.out.print("Nama Belakang: ");
        String namaBelakang = scanner.nextLine();

        System.out.print("Jenis Kelamin (L/P): ");
        String jkInput = scanner.nextLine();
        char jenisKelamin = jkInput.length() > 0 ? jkInput.charAt(0) : '-';

        System.out.print("Alamat: ");
        String alamat = scanner.nextLine();

        System.out.print("Username: ");
        String username = scanner.nextLine();

        if (username.length() < 8) {
            throw new InvalidPropertyException("Username harus lebih dari 8 karakter");
        }

        System.out.print("Password: ");
        String password = scanner.nextLine();

        validatePassword(password);

        User newUser = new User(namaDepan, namaBelakang, jenisKelamin, alamat, username, password);
        listOfUser.add(newUser);
        System.out.println("User telah berhasil didaftarkan.");
    }

    public static void validatePassword(String password) throws InvalidPropertyException {
        if (password.length() < 6 || password.length() > 16) {
            throw new InvalidPropertyException("Password harus mengandung huruf besar, angka, minumum 6 karakter dan maksimum 16 karakter");
        }

        boolean hasUpper = false;
        boolean hasDigit = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            if (Character.isDigit(c)) hasDigit = true;
        }

        if (!hasUpper || !hasDigit) {
            throw new InvalidPropertyException("Password harus mengandung huruf besar, angka, minumum 6 karakter dan maksimum 16 karakter");
        }
    }
}