import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ex9_1 {

    public static class Student {
        private String studentId;
        private String studentName;
        private int age;
        private String major;

        public Student() {
        }

        public Student(String studentId, String studentName, int age, String major) {
            this.studentId = studentId;
            this.studentName = studentName;
            this.age = age;
            this.major = major;
        }

        public String getStudentId() {
            return studentId;
        }

        public void setStudentId(String studentId) {
            this.studentId = studentId;
        }

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public void inputData(Scanner scanner, List<Student> studentList) {
            while (true) {
                System.out.print("Nhập mã sinh viên: ");
                String inputId = scanner.nextLine().trim();
                boolean isExist = false;
                for (Student s : studentList) {
                    if (s.getStudentId().equalsIgnoreCase(inputId)) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist && !inputId.isEmpty()) {
                    this.studentId = inputId;
                    break;
                }
                System.out.println("Mã sinh viên đã tồn tại hoặc không hợp lệ! Vui lòng nhập lại.");
            }

            System.out.print("Nhập tên sinh viên: ");
            this.studentName = scanner.nextLine();

            this.age = inputInt(scanner, "Nhập tuổi: ");

            System.out.print("Nhập chuyên ngành: ");
            this.major = scanner.nextLine();
        }

        public void displayData() {
            System.out.println("Mã SV: " + studentId + " | Tên SV: " + studentName + " | Tuổi: " + age + " | Chuyên ngành: " + major);
        }
    }

    private static List<Student> studentList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n*********************QUẢN LÝ SINH VIÊN********************");
            System.out.println("1. Hiển thị danh sách sinh viên");
            System.out.println("2. Thêm sinh viên");
            System.out.println("3. Cập nhật thông tin sinh viên theo mã sinh viên");
            System.out.println("4. Xóa sinh viên theo mã sinh viên");
            System.out.println("5. Tìm sinh viên theo tên sinh viên");
            System.out.println("6. Thoát");
            choice = inputInt(scanner, "Lựa chọn của bạn: ");

            switch (choice) {
                case 1:
                    displayAllStudents();
                    break;
                case 2:
                    addStudents();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    searchStudentByName();
                    break;
                case 6:
                    System.out.println("Đã thoát chương trình!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 6);

        scanner.close();
    }

    public static int inputInt(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số nguyên hợp lệ!");
            }
        }
    }

    private static void displayAllStudents() {
        System.out.println("\n--- DANH SÁCH SINH VIÊN ---");
        if (studentList.isEmpty()) {
            System.out.println("Danh sách sinh viên đang rỗng!");
            return;
        }
        for (Student s : studentList) {
            s.displayData();
        }
    }

    private static void addStudents() {
        System.out.println("\n--- THÊM SINH VIÊN ---");
        int count = inputInt(scanner, "Nhập số lượng sinh viên muốn thêm: ");
        for (int i = 0; i < count; i++) {
            System.out.println("\nNhập thông tin sinh viên thứ " + (i + 1) + ":");
            Student student = new Student();
            student.inputData(scanner, studentList);
            studentList.add(student);
        }
        System.out.println("Thêm thành công!");
    }

    private static void updateStudent() {
        System.out.println("\n--- CẬP NHẬT THÔNG TIN SINH VIÊN ---");
        System.out.print("Nhập mã sinh viên cần cập nhật: ");
        String id = scanner.nextLine();

        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("Mã sinh viên không tồn tại");
            return;
        }

        System.out.print("Nhập tên mới: ");
        student.setStudentName(scanner.nextLine());

        student.setAge(inputInt(scanner, "Nhập tuổi mới: "));

        System.out.print("Nhập chuyên ngành mới: ");
        student.setMajor(scanner.nextLine());

        System.out.println("Cập nhật thông tin sinh viên thành công!");
    }

    private static void deleteStudent() {
        System.out.println("\n--- XÓA SINH VIÊN ---");
        System.out.print("Nhập mã sinh viên cần xóa: ");
        String id = scanner.nextLine();

        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("Mã sinh viên không tồn tại");
            return;
        }

        studentList.remove(student);
        System.out.println("Xóa sinh viên thành công!");
    }

    private static void searchStudentByName() {
        System.out.println("\n--- TÌM SINH VIÊN THEO TÊN ---");
        System.out.print("Nhập tên sinh viên cần tìm: ");
        String keyword = scanner.nextLine().toLowerCase().trim();

        int foundCount = 0;
        System.out.println("Kết quả tìm kiếm:");
        for (Student s : studentList) {
            if (s.getStudentName().toLowerCase().contains(keyword)) {
                s.displayData();
                foundCount++;
            }
        }

        System.out.println("Tổng số lượng sinh viên tìm thấy: " + foundCount);
    }

    private static Student findStudentById(String id) {
        for (Student s : studentList) {
            if (s.getStudentId().equalsIgnoreCase(id)) {
                return s;
            }
        }
        return null;
    }
}