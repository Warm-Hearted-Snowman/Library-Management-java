package Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    public static void log(String message) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String time = simpleDateFormat.format(new Date());
        System.out.println("[ " + time + " ] : " + message);
    }

    public static void addmemberLogger(String memberId, String memberName) {
        log("Member Added :\n" +
                "ID : ( " + memberId + " ) - Name : ( " + memberName + " )");
    }

    public static void addBookLogger(String bookId, String bookName, String bookCategory) {
        log("Book Added :\n" +
                "ID: ( " + bookId + " ) - Name: ( " + bookName + " ) - Category: ( " + bookCategory + " )");
    }

    public static void loginPageUserLogger(String memberId, String memberName) {
        log("Member Login :\n" +
                "ID : ( " + memberId + " ) - Name : ( " + memberName + " ) .");
    }

    public static void loginPageAdminLogger() {
        log("Admin Logged in Successfully.");
    }

    public static void programStartedLogger() {
        log("Program Started successfully.");
    }
    public static void programLoginLogger() {
        log("Login page launched.");
    }

    public static void issueBookLogger(String bookId, String bookName, String memberId, String memberName) {
        log("Book Issued :\n" +
                "Book ID: ( " + bookId + " ) - Book Name: ( " + bookName + " ) - Member ID: ( " + memberId + " ) - Member Name: ( " + memberName + " )");
    }

    public static void issueBookErrorLogger(String bookId, String memberId) {
        log("Book Issued Error :\n" +
                "Book ID: ( " + bookId + " ) - Member ID: ( " + memberId + " )");
    }

    public static void returnBookLogger(String bookId, String bookName, String memberId, String memberName) {
        log("Book Returned :\n" +
                "Book ID: ( " + bookId + " ) - Book Name: ( " + bookName + " ) - Member ID: ( " + memberId + " ) - Member Name: ( " + memberName + " )");
    }

    public static void adminExitLogger() {
        log("Admin logged out Successfully.");
    }
    public static void userExitLogger(String id) {
        log("User : ( "+id+" ) logged out Successfully.");
    }
    public static void programExitLogger() {
        log("Program stopped successfully.");
    }

}
