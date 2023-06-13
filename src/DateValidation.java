import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class DateValidation {
    Scanner sc = new Scanner(System.in);

    String dob = null;
    boolean fact1 = true;
    LocalDate date = null;

    public LocalDate datevalid() {
        while (true) {

            dob = sc.next();

            DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            try {

                date = LocalDate.parse(dob, formater);
//                        System.out.println("date "  + date);

                break;
            } catch (Exception e) {
                System.out.println("enter valid format(yyyy-MM-dd)");
            }
        }
        return date;
    }
}