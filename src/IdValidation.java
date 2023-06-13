import java.util.Scanner;

public class IdValidation {
    Scanner sc = new Scanner(System.in);
    int no = 0;
    boolean b = false;

    public int valid() {
        while (true) {
            b = true;

            try {
                String str1 = sc.next();
                no = Integer.parseInt(str1);
                if (no <= 0) {

                    System.out.println("OOPS !!! Enter Valid Input !!!");
                } else {
                    break;
                }
            } catch (Exception e) {

                System.out.println("OOPS !!! Enter valid Input !!!");

            }
        }
        return no;
    }

}