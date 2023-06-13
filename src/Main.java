import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee?useSSL=false", "root", "Admin@123$");
        Scanner sc = new Scanner(System.in);

        IdValidation id_val = new IdValidation();
        NameValidation name_val = new NameValidation();
        DateValidation date_val = new DateValidation();

        boolean fact = true;
        while (fact) {

//            int no = 0;
//            boolean b = false;
//            while (true) {
//                b = true;
//                System.out.println("number of row:");
//
//                try {
//                    String str1 = sc.next();
//                    no = Integer.parseInt(str1);
//                    if (no <= 0) {
//
//                        System.out.println("OOPS !!! Enter Valid Input !!!");
//                    }
//                } catch (Exception e) {
//
//                    System.out.println("OOPS !!! Enter valid Input !!!");
//
//                }
//                break;
//            }
//                if (b == true) {
//                    break;
//                }
            System.out.println("number of row:");
            int row = id_val.valid();

            for (int i = 0; i < row; i++) {


//                int Id1 = 0;
//
//                boolean b1 = false;
//                while (true) {
//                    b1 = true;
//                    System.out.println("Enter EmpID :");
//
//                    try {
//                        String str2 = sc.next();
//                        Id1 = Integer.parseInt(str2);
//                        if (Id1 <= 0) {
//
////                                b1 = false;
//                            System.out.println("OOPS !!! Enter Valid Input !!!");
//                        } else {
//
//                            break;
//                        }
//                    } catch (Exception e) {
////                            b1 = false;
//                        System.out.println("OOPS !!! Enter valid Input !!!");
//                    }
//                }

                System.out.println("Enter EmpID :");
                int empid = id_val.valid();

//                String str = null;
//                boolean b2 = false;
//                while (true) {
//                    b2 = true;
//                    System.out.println("Enter EmpName :");
//
//                    try {
//                        str = sc.next();
//
//
//                        if (!str.matches("[a-zA-Z]+")) {
//                            System.out.println("OOPS !!! Enter valid Input !!!");
//                        } else {
//                            break;
//                        }
//
//                    } catch (Exception e) {
//                        System.out.println("OOPS !!! Enter valid Input !!!");
//                    }
//                }
                System.out.println("Enter EmpName :");
                String empname = name_val.namevalid();

//                String dob = null;
//                boolean fact1 = true;
//                LocalDate date = null;
//                while (fact1) {
//
//                    System.out.println("enter Date of Birth(yyyy-MM-dd):");
//                    dob = sc.next();
//
//                    DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//                    try {
//
//                        date = LocalDate.parse(dob, formater);
////                        System.out.println("date "  + date);
//
//                        fact1 = false;
//                    } catch (Exception e) {
//                        System.out.println("enter valid format(yyyy-MM-dd)");
//                    }
//                }
                System.out.println("enter Date of Birth(yyyy-MM-dd):");
                LocalDate empdate = date_val.datevalid();

                if (i <= row) {
                    fact = false;
                }


                PreparedStatement ps = con.prepareStatement("insert into records(Name,ID,Date) values(?,?,?)");
                ps.setString(1, empname);
                ps.setInt(2, empid);
                ps.setDate(3, Date.valueOf(empdate));

                ps.executeUpdate();

                ps.close();
            }
        }

        boolean flag = true;

        while (flag) {
            System.out.println("Press(1) for Seraching");
            System.out.println("Press(2) for Sorting");
            System.out.println("Press(3) for find Data from Particular date to Upper side");
            System.out.println("Press(4) for find Data from Particular date to bottom Side");
            System.out.println("Press(5) for Exit");
            System.out.println("----------------------------------");

            int number = sc.nextInt();
            switch (number) {
                case 1:
                    System.out.println("Press(1) for Seraching by empID");
                    System.out.println("Press(2) for Seraching by empName");
                    System.out.println("Press(3) for Seraching by empDOB");

                    int number1 = sc.nextInt();
                    switch (number1) {
                        case 1:
                            System.out.println("Enter ID for searching by empID :");
                            int number2 = sc.nextInt();
                            PreparedStatement ps1 = con.prepareStatement("select * from records where ID=?");
                            ps1.setInt(1, number2);
                            ResultSet rs = ps1.executeQuery();
                            while (rs.next()) {
                                int id = rs.getInt(1);
                                String str = rs.getString(2);
                                Date d = rs.getDate(3);
                                System.out.println("ID :" + id);
                                System.out.println("Name : " + str);
                                System.out.println("DOB : " + d);
                                System.out.println("----------------------------------");

                            }

                            ps1.close();
                            break;

                        case 2:
                            System.out.println("Enter Name for searching by enpName :");
                            String name = sc.next();
                            PreparedStatement ps2 = con.prepareStatement("select * from records where name=?");
                            ps2.setString(1, name);
                            ResultSet rs1 = ps2.executeQuery();
                            while (rs1.next()) {
                                int id1 = rs1.getInt(1);
                                String str1 = rs1.getString(2);
                                Date d1 = rs1.getDate(3);
                                System.out.println("ID :" + id1);
                                System.out.println("Name : " + str1);
                                System.out.println("DOB : " + d1);
                                System.out.println("----------------------------------");

                            }
                            ps2.close();
                            break;

                        case 3:
                            System.out.println("Enter DOB for saerching by empDOB");
//                            String dob = null;
//                            boolean fact1 = true;
//                            LocalDate date = null;
//                            while (fact1) {
//
//                                System.out.println("enter Date of Birth(yyyy-MM-dd):");
//                                dob = sc.next();
//
//                                DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//                                try {
//                                    date = LocalDate.parse(dob, formater);
//                                    fact1 = false;
//                                } catch (Exception e) {
//                                    System.out.println("enter valid format(yyyy-MM-dd)");
//                                }
//                            }
                            LocalDate searchdate = date_val.datevalid();

                            PreparedStatement ps3 = con.prepareStatement("select * from records where Date=?");
                            ps3.setDate(1, Date.valueOf(searchdate));
                            ResultSet rs2 = ps3.executeQuery();
                            while (rs2.next()) {
                                int id2 = rs2.getInt(1);
                                String str2 = rs2.getString(2);
                                Date d2 = rs2.getDate(3);
                                System.out.println("ID :" + id2);
                                System.out.println("Name : " + str2);
                                System.out.println("DOB : " + d2);
                                System.out.println("----------------------------------");

                            }
                            ps3.close();
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Press(1) for Sorting by empID");
                    System.out.println("Press(2) for Sorting by empName");
                    System.out.println("Press(3) for Sorting by empDOB");
                    int number3 = sc.nextInt();
                    switch (number3) {
                        case 1:
                            PreparedStatement ps4 = con.prepareStatement("select * from records order by ID asc");
                            ResultSet rs3 = ps4.executeQuery();
                            while (rs3.next()) {
                                int id3 = rs3.getInt(1);
                                String str3 = rs3.getString(2);
                                Date d3 = rs3.getDate(3);
                                System.out.println("ID :" + id3);
                                System.out.println("Name : " + str3);
                                System.out.println("DOB : " + d3);
                                System.out.println("----------------------------------");

                            }
                            ps4.close();
                            break;
                        case 2:
                            PreparedStatement ps5 = con.prepareStatement("select * from records order by name asc");
                            ResultSet rs4 = ps5.executeQuery();
                            while (rs4.next()) {
                                int id4 = rs4.getInt(1);
                                String str4 = rs4.getString(2);
                                Date d4 = rs4.getDate(3);
                                System.out.println("ID :" + id4);
                                System.out.println("Name : " + str4);
                                System.out.println("DOB : " + d4);
                                System.out.println("----------------------------------");

                            }
                            ps5.close();
                            break;
                        case 3:
                            PreparedStatement ps6 = con.prepareStatement("select * from records order by Date asc");
                            ResultSet rs5 = ps6.executeQuery();
                            while (rs5.next()) {
                                int id5 = rs5.getInt(1);
                                String str5 = rs5.getString(2);
                                Date d5 = rs5.getDate(3);
                                System.out.println("ID :" + id5);
                                System.out.println("Name : " + str5);
                                System.out.println("DOB : " + d5);
                                System.out.println("----------------------------------");

                            }
                            ps6.close();
                            break;

                    }
                    break;

                case 3:
                    System.out.println("Enetr Date for find records from particular date to up :");
//                    String dob2 = null;
//                    boolean fact3 = true;
//                    LocalDate date2 = null;
//                    while (fact3) {
//
//                        System.out.println("enter Date of Birth(yyyy-MM-dd):");
//                        dob2 = sc.next();
//
//                        DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//                        try {
//                            date2 = LocalDate.parse(dob2, formater);
//                            fact3 = false;
//                        } catch (Exception e) {
//                            System.out.println("enter valid format(yyyy-MM-dd)");
//                        }
//                    }
                    LocalDate findup = date_val.datevalid();

                    PreparedStatement ps7 = con.prepareStatement("select * from records where Date<=?");
                    ps7.setDate(1, Date.valueOf(findup));
                    ResultSet rs7 = ps7.executeQuery();
                    while (rs7.next()) {
                        int id7 = rs7.getInt(1);
                        String str7 = rs7.getString(2);
                        Date d7 = rs7.getDate(3);
                        System.out.println("ID :" + id7);
                        System.out.println("Name : " + str7);
                        System.out.println("DOB : " + d7);
                        System.out.println("----------------------------------");

                    }
                    ps7.close();
                    break;


                case 4:
                    System.out.println("Enetr Date for find records from particular date to down :");
//                    String dob1 = null;
//                    boolean fact2 = true;
//                    LocalDate date1 = null;
//                    while (fact2) {
//
//                        System.out.println("enter Date of Birth(yyyy-MM-dd):");
//                        dob1 = sc.next();
//
//                        DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//                        try {
//                            date1 = LocalDate.parse(dob1, formater);
//                            fact2 = false;
//                        } catch (Exception e) {
//                            System.out.println("enter valid format(yyyy-MM-dd)");
//                        }
//                    }
                    LocalDate finddown = date_val.datevalid();

                    PreparedStatement ps = con.prepareStatement("select * from records where Date>=?");
                    ps.setDate(1, Date.valueOf(finddown));
                    ResultSet rs6 = ps.executeQuery();
                    while (rs6.next()) {
                        int id6 = rs6.getInt(1);
                        String str6 = rs6.getString(2);
                        Date d6 = rs6.getDate(3);
                        System.out.println("ID :" + id6);
                        System.out.println("Name : " + str6);
                        System.out.println("DOB : " + d6);
                        System.out.println("----------------------------------");

                    }
                    ps.close();
                    break;

                case 5:
                    System.out.println("Thank You!!!!!!");
                    flag = false;
                    break;

            }

        }
        con.close();
    }
}