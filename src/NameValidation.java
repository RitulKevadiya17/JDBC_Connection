import java.util.Scanner;

public class NameValidation {
    Scanner sc = new Scanner(System.in);
    String str = null;
    boolean b2 = false;

    public String namevalid() {
        while (true) {
            b2 = true;

            try {
                str = sc.next();


                if (!str.matches("[a-zA-Z]+")) {
                    System.out.println("OOPS !!! Enter valid Input !!!");
                } else {
                    break;
                }

            } catch (Exception e) {
                System.out.println("OOPS !!! Enter valid Input !!!");
            }
        }
        return str;
    }
}