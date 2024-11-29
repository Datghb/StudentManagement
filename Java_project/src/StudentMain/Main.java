package StudentMain;

import java.util.Scanner;
import Students.Student;
import StudentManager.StudentManager;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int maxStudents = 0;
        boolean validInput = false;

        // Kiểm tra giá trị maxStudents là số nguyên
        while (!validInput) {
            System.out.print("Enter maximum number of students: ");
            if (scanner.hasNextInt()) {
                maxStudents = scanner.nextInt();
                scanner.nextLine(); // Loại bỏ ký tự xuống dòng thừa
                if (maxStudents > 0) { // Chỉ chấp nhận số dương
                    validInput = true;
                } else {
                    System.out.println("Vui lòng nhập số lớn hơn 0.");
                }
            } else {
                System.out.println("Lỗi: Bạn đã nhập không phải là số. Vui lòng thử lại!");
                scanner.nextLine(); // Loại bỏ giá trị không hợp lệ
            }
        }
        // Khởi tạo StudentManager
        StudentManager studentManager = new StudentManager(maxStudents);
        int choice;
        do {
            showMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Nhập mã số học sinh: ");
                    String studentID = scanner.nextLine();
                    System.out.print("Nhập tên học sinh: ");
                    String name = scanner.nextLine();
                    System.out.print("Nhập điểm học sinh: ");
                    double score = scanner.nextDouble();
                    scanner.nextLine();
                    studentManager.addStudent(studentID, name, score);
                    break;
                case 2:
                    System.out.print("Nhập mã số học sinh cần sửa: ");
                    String updateID = scanner.nextLine();
                    System.out.print("Nhập tên mới: ");
                    String newName = scanner.nextLine();
                    System.out.print("Nhập điểm mới: ");
                    double newScore = scanner.nextDouble();
                    scanner.nextLine();
                    studentManager.updateStudent(updateID, newName, newScore);
                    break;
                case 3:
                    System.out.print("Nhập mã số học sinh cần xóa: ");
                    String deleteID = scanner.nextLine();
                    studentManager.deleteStudent(deleteID);
                    break;
                case 4:
                    System.out.println("Sắp xếp từ thấp đến cao (1) hoặc từ cao đến thấp (2)?");
                    int sortChoice = scanner.nextInt();
                    scanner.nextLine();
                    studentManager.sortStudents(sortChoice == 1);
                    //studentManager.sortStudents(sortChoice == 1);
                    studentManager.displayStudents();
                    break;
                case 5:
                    System.out.print("Nhập mã số học sinh cần tìm: ");
                    String searchID = scanner.nextLine();
                    Student student = studentManager.findStudentByID(searchID);
                    if (student != null) {
                        System.out.println("Thông tin học sinh: Mã số: " + student.getStudentID() + ", Tên: " + student.getName() + ", Điểm: " + student.getScore() + ", Xếp hạng: " + student.getRank());
                    } else {
                        System.out.println("Không tìm thấy học sinh.");
                    }
                    break;
                case 6:
                    studentManager.displayStudents();
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (choice != 0);
    }

    public static void showMenu() {
        System.out.println("======== Student Manager =========");
        System.out.println("1. Add student                   ||");
        System.out.println("2. Edit student information      ||");
        System.out.println("3. Delete Student                ||");
        System.out.println("4. Sort students by score        ||");
        System.out.println("5. Search student by ID          ||");
        System.out.println("6. Show student list             ||");
        System.out.println("===================================");
        System.out.print("Enter your selection: ");
    }
}
