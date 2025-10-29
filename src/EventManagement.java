import java.util.*;

public class EventManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<User> userList = new ArrayList<>();
        String[] tech = {"PPT", "Workshops", "Webdevelopment", "Robotics", "Hackathon"};
        String[] nontech = {"Talent shows", "Critical thinking", "Craft exhibitions", "Quiz contests", "Debate competitions"};
        List<Admin> admins = new ArrayList<>();
        admins.add(new Admin("admin", "password"));

        while (true) {
            System.out.println("Select your role\n1.USER\n2.ADMIN\n3.EXIT\nEnter your choice:");
            int ch1 = sc.nextInt();
            switch (ch1) {
                case 1: {
                    System.out.println("-----------------------------------------------------------------------------");
                    System.out.println("\t\t____EVENT MANAGEMENT WELCOMES YOU____\n\t\t\t Lets move to Registration");
                    sc.nextLine();
                    String stdName;
                    while (true) {
                        System.out.print("\nStudent name       : ");
                        stdName = sc.nextLine();
                        if (stdName.matches("[a-zA-Z ]+")) {
                            break;
                        } else {
                            System.out.println("Invalid name. Please enter a name.");
                            
                        }
                    }
                    String gender;
                    while (true) {
                        System.out.print("Gender             : ");
                        gender = sc.nextLine().toLowerCase();
                        if (gender.equals("male") || gender.equals("female") || gender.equals("others")) {
                            break;
                        } else {
                            System.out.println("Incorrect Gender. Please enter 'male', 'female', or 'others'.");
                        }
                    }

                    long phno;
                    while (true) {
                        System.out.print("ph.no              : ");
                        phno = sc.nextLong();
                        if (String.valueOf(phno).length() == 10) {
                            break;
                        } else {
                            System.out.println("Invalid phone number. Please enter a 10-digit phone number.");
                        }
                    }

                    System.out.print("Year(1 - 4)        : ");
                    int year = sc.nextInt();
                    System.out.print("Semester(1 - 8)    : ");
                    int sem = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Department         : ");
                    String dept = sc.nextLine();
                    System.out.print("College/University : ");
                    String clgName = sc.nextLine();
                    User u = new User(stdName, gender, phno, year, sem, dept, clgName);
                    userList.add(u);
                    System.out.println("\nNote : you can participate :\n            (1). one technical and one non-technical   (or)\n            (2). one technical /one non-technical.\n");
                    System.out.println("Do you want to register for technical event? (yes/no)");
                    String techChoice = sc.next().toLowerCase();
                    if (techChoice.equals("yes")) {
                        System.out.println("Select technical event:");
                        u.registerEvent(tech, sc);
                    }
                    System.out.println("Do you want to register for non-technical event? (yes/no)");
                    String nontechChoice = sc.next().toLowerCase();
                    if (nontechChoice.equals("yes")) {
                        System.out.println("Select non-technical event:");
                        u.registerEvent(nontech, sc);
                    }
                    System.out.println("Registered Successfully");
                    break;
                }
                case 2: {
                    sc.nextLine();
                    System.out.println("Login");
                    System.out.print("User name : ");
                    String userName = sc.nextLine();
                    System.out.print("Password  : ");
                    String password = sc.nextLine();
                    Admin authenticatedAdmin = null;
                    for (Admin admin : admins) {
                        if (admin.isAuthenticated(userName, password)) {
                            authenticatedAdmin = admin;
                            break;
                        }
                    }
                    if (authenticatedAdmin == null) {
                        System.out.println("Incorrect username or password");
                        break;
                    } else {
                        System.out.println("Login Successful");
                    }

                    while (true) {
                        System.out.println("Select : \n1. Event participants counts \n2. Registered Students details \n3. Logout\nEnter your choice :");
                        int ach = sc.nextInt();
                        if (ach == 3) {
                            System.out.println("Logging out...");
                            break;
                        }
                        switch (ach) {
                            case 1: {
                                authenticatedAdmin.displayEventParticipationCount(userList, tech, nontech);
                                break;
                            }
                            case 2: {
                                System.out.println("Registered Students Details:");
                                System.out.println("s.no name   gender  ph.no     year sem dept  clgname    eventparticipated");
                                for (int i = 0; i < userList.size(); i++) {
                                    userList.get(i).display1(i + 1);
                                }
                                break;
                            }
                            default: {
                                System.out.println("Invalid choice. Please try again.");
                                break;
                            }
                        }
                    }
                    break;
                }
                case 3: {
                    System.out.println("Exiting.....");
                    sc.close();
                    System.exit(0);
                }
                default: {
                    System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
}

class User {
    String stuName;
    String gender;
    long phno;
    int year;
    int sem;
    String dept;
    String clgName;
    ArrayList<String> events = new ArrayList<>();

    User() {
    }

    User(String stuName, String gender, long phno, int year, int sem, String dept, String clgName) {
        this.stuName = stuName;
        this.gender = gender;
        this.phno = phno;
        this.year = year;
        this.sem = sem;
        this.dept = dept;
        this.clgName = clgName;
    }

    void registerEvent(String[] eventArray, Scanner sc) {
        for (int i = 0; i < eventArray.length; i++) {
            System.out.println((i + 1) + ". " + eventArray[i]);
        }
        System.out.println("Enter the number corresponding to the event you want to register:");
        int ch = sc.nextInt();
        if (ch >= 1 && ch <= eventArray.length) {
            events.add(eventArray[ch - 1]);
        } else {
            System.out.println("Invalid choice.");
        }
    }

    String getEvents() {
        return String.join(", ", events);
    }

    void display1(int serialNumber) {
        System.out.println(serialNumber + "\t" + this.stuName + "\t" + this.gender + "\t" + this.phno + "\t" + this.year + "\t" + this.sem + "\t" + this.dept + "  \t" + this.clgName + "  \t" + getEvents());
    }
}

class Admin {
    private String userName;
    private String password;

    Admin(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    boolean isAuthenticated(String userName, String password) {
        return this.userName.equals(userName) && this.password.equals(password);
    }

    void displayEventParticipationCount(List<User> userList, String[] tech, String[] nontech) {
        HashMap<String, Integer> eventCounts = new HashMap<>();

        for (String event : tech) {
            eventCounts.put(event, 0);
        }
        for (String event : nontech) {
            eventCounts.put(event, 0);
        }

        for (User user : userList) {
            for (String event : user.events) {
                eventCounts.put(event, eventCounts.get(event) + 1);
            }
        }

        System.out.println("\nEvent Participation Counts:");
        for (Map.Entry<String, Integer> entry : eventCounts.entrySet()) {
            System.out.printf("%-20s : %d%n", entry.getKey(), entry.getValue());
        }
    }
}