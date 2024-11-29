package StudentManager;// StudentManager.java
import java.util.ArrayList;
import Students.Student;


public class StudentManager {
    private final ArrayList<Student> students = new ArrayList<>();
    private final int maxStudents;
    public StudentManager(int maxStudents) {
        this.maxStudents = maxStudents;
    }
    public void addStudent(String studentID, String name, double score) {
        if (students.size() >= maxStudents) {
            System.out.println("Số lượng học sinh đã đầy.");
            return;
        }
        if(score>10){
            System.out.println("Diem ban nhapj khong hop le");
            return;
        }
        if(score<0){
            System.out.println("Diem ban nhapj khong hop le");
            return;
        }
        if (findStudentByID(studentID) != null) {
            System.out.println("Mã số học sinh đã tồn tại.");
            return;
        }
        students.add(new Student(studentID, name, score));
        System.out.println("Học sinh đã được thêm.");
    }
    public void updateStudent(String studentID, String newName, double newScore) {
        Student student = findStudentByID(studentID);
        if (student != null) {
            student.setScore(newScore);
            System.out.println("Thông tin học sinh đã được cập nhật.");
        } else {
            System.out.println("Không tìm thấy học sinh.");
        }
    }
    // delete
    public void deleteStudent(String studentID) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentID().equals(studentID)) {
                students.remove(i);
                System.out.println("Học sinh đã được xóa.");
                return;
            }
        }
        System.out.println("Không tìm thấy học sinh với ID này.");
    }
// những thuật toán thay thế
//    public void sortStudents(boolean ascending) {
//        if (ascending) {
//            // Sử dụng Merge Sort cho sắp xếp tăng dần
//            students.sort((s1, s2) -> Double.compare(s1.getScore(), s2.getScore()));
//        } else {
//            // Sử dụng Quick Sort cho sắp xếp giảm dần
//            quickSort(0, students.size() - 1);
//        }
//    }
//
//    // Hàm hỗ trợ Quick Sort
//    private void quickSort(int low, int high) {
//        if (low < high) {
//            int pivotIndex = partition(low, high);
//            quickSort(low, pivotIndex - 1);
//            quickSort(pivotIndex + 1, high);
//        }
//    }

//    private int partition(int low, int high) {
//        double pivot = students.get(high).getScore();
//        int i = low - 1;
//        for (int j = low; j < high; j++) {
//            if (students.get(j).getScore() > pivot) { /
//            / Giảm dần
//                i++;
//                // Hoán đổi
//                Student temp = students.get(i);
//                students.set(i, students.get(j));
//                students.set(j, temp);
//            }
//        }
//        // Đặt phần tử chốt vào đúng vị trí
//        Student temp = students.get(i + 1);
//        students.set(i + 1, students.get(high));
//        students.set(high, temp);
//        return i + 1;
//    }
    public void sortStudents(boolean ascending) {
        if (ascending) {
            // Sử dụng Insertion Sort cho sắp xếp tăng dần
            for (int i = 1; i < students.size(); i++) {//vòng lặp bắt đầu từ phần tử thứ 2 , khi đứng ở từng phần tử thì nó sẽ so sánh ngược từ vị trí đó sang hết bên trái
                Student currentStudent = students.get(i);
                int j = i - 1;
                while (j >= 0 && students.get(j).getScore() > currentStudent.getScore()) {
                    students.set(j + 1, students.get(j));
                    j--;
                }
                students.set(j + 1, currentStudent);
            }
        } else {
            // Sử dụng Bubble Sort cho sắp xếp giảm dần
            for (int i = 0; i < students.size() - 1; i++) {
                for (int j = 0; j < students.size() - i - 1; j++) {
                    if (students.get(j).getScore() < students.get(j + 1).getScore()) {
                        // Hoán đổi nếu điểm của học sinh tại vị trí j nhỏ hơn học sinh sau nó.temp()biến tạm thời
                        Student temp = students.get(j);
                        students.set(j, students.get(j + 1));
                        students.set(j + 1, temp);
                    }
                }
            }
        }
    }
    public Student findStudentByID(String studentID) {
        for (Student s : students) {
            if (s.getStudentID().equals(studentID)) {
                return s;
            }
        }
        return null;
    }
    public void displayStudents() {
        for (Student s : students) {
            System.out.println("Mã số: " + s.getStudentID() + ", Tên: " + s.getName() + ", Điểm: " + s.getScore() + ", Xếp hạng: " + s.getRank());
        }
    }
}
