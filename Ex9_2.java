import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ex9_2 {

    public static abstract class Pet {
        private String petId;
        private String petName;
        private int age;

        public Pet() {
        }

        public Pet(String petId, String petName, int age) {
            this.petId = petId;
            this.petName = petName;
            this.age = age;
        }

        public String getPetId() {
            return petId;
        }

        public void setPetId(String petId) {
            this.petId = petId;
        }

        public String getPetName() {
            return petName;
        }

        public void setPetName(String petName) {
            this.petName = petName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public abstract void speak();

        public void inputData(Scanner scanner, List<Pet> petList) {
            while (true) {
                System.out.print("Nhập mã thú cưng (4 ký tự, bắt đầu bằng C/D + 3 số): ");
                String idInput = scanner.nextLine().trim();
                if (idInput.matches("^[CD]\\d{3}$")) {
                    boolean isExist = false;
                    for (Pet p : petList) {
                        if (p.getPetId().equalsIgnoreCase(idInput)) {
                            isExist = true;
                            break;
                        }
                    }
                    if (!isExist) {
                        this.petId = idInput;
                        break;
                    } else {
                        System.out.println("Mã thú cưng đã tồn tại! Vui lòng nhập lại.");
                    }
                } else {
                    System.out.println("Mã không hợp lệ! Mã phải có 4 ký tự, bắt đầu bằng C hoặc D và theo sau là 3 chữ số.");
                }
            }

            while (true) {
                System.out.print("Nhập tên thú cưng (20-50 ký tự): ");
                String nameInput = scanner.nextLine().trim();
                if (nameInput.length() >= 20 && nameInput.length() <= 50) {
                    this.petName = nameInput;
                    break;
                } else {
                    System.out.println("Tên thú cưng phải dài từ 20 đến 50 ký tự!");
                }
            }

            while (true) {
                this.age = inputInt(scanner, "Nhập tuổi (lớn hơn 0): ");
                if (this.age > 0) {
                    break;
                } else {
                    System.out.println("Tuổi phải lớn hơn 0!");
                }
            }
        }

        public void displayData() {
            System.out.println("Mã: " + petId + " | Tên: " + petName + " | Tuổi: " + age);
        }
    }

    public static class Dog extends Pet {
        public Dog() {
            super();
        }

        public Dog(String petId, String petName, int age) {
            super(petId, petName, age);
        }

        @Override
        public void speak() {
            System.out.println("Gâu gâu");
        }
    }

    public static class Cat extends Pet {
        public Cat() {
            super();
        }

        public Cat(String petId, String petName, int age) {
            super(petId, petName, age);
        }

        @Override
        public void speak() {
            System.out.println("Meo meo");
        }
    }

    private static List<Pet> petList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n*********************QUẢN LÝ THÚ CƯNG********************");
            System.out.println("1. Hiển thị danh sách thú cưng");
            System.out.println("2. Thêm thú cưng");
            System.out.println("3. Gọi tiếng kêu");
            System.out.println("4. Xóa thú cưng");
            System.out.println("5. Tìm thú cưng theo tên");
            System.out.println("6. Thoát");
            choice = inputInt(scanner, "Lựa chọn của bạn: ");

            switch (choice) {
                case 1:
                    displayAllPets();
                    break;
                case 2:
                    addPet();
                    break;
                case 3:
                    makePetsSpeak();
                    break;
                case 4:
                    deletePet();
                    break;
                case 5:
                    searchPetByName();
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

    private static void displayAllPets() {
        System.out.println("\n--- DANH SÁCH THÚ CƯNG ---");
        if (petList.isEmpty()) {
            System.out.println("Danh sách thú cưng đang rỗng!");
            return;
        }
        for (Pet p : petList) {
            p.displayData();
        }
    }

    private static void addPet() {
        System.out.println("\n--- THÊM THÚ CƯNG ---");
        System.out.println("Chọn loại thú cưng (1. Chó | 2. Mèo): ");
        int type = inputInt(scanner, "Lựa chọn: ");

        Pet pet;
        if (type == 1) {
            pet = new Dog();
        } else if (type == 2) {
            pet = new Cat();
        } else {
            System.out.println("Loại thú cưng không hợp lệ!");
            return;
        }

        pet.inputData(scanner, petList);
        petList.add(pet);
        System.out.println("Thêm thú cưng thành công!");
    }

    private static void makePetsSpeak() {
        System.out.println("\n--- TIẾNG KÊU CỦA CÁC THÚ CƯNG ---");
        if (petList.isEmpty()) {
            System.out.println("Chưa có thú cưng nào trong danh sách!");
            return;
        }
        for (Pet p : petList) {
            System.out.print(p.getPetName() + " kêu: ");
            p.speak();
        }
    }

    private static void deletePet() {
        System.out.println("\n--- XÓA THÚ CƯNG ---");
        System.out.print("Nhập mã thú cưng cần xóa: ");
        String id = scanner.nextLine().trim();

        Pet foundPet = null;
        for (Pet p : petList) {
            if (p.getPetId().equalsIgnoreCase(id)) {
                foundPet = p;
                break;
            }
        }

        if (foundPet != null) {
            petList.remove(foundPet);
            System.out.println("Xóa thú cưng thành công!");
        } else {
            System.out.println("Mã thú cưng không tồn tại!");
        }
    }

    private static void searchPetByName() {
        System.out.println("\n--- TÌM THÚ CƯNG THEO TÊN ---");
        System.out.print("Nhập tên thú cưng cần tìm: ");
        String keyword = scanner.nextLine().toLowerCase().trim();

        boolean found = false;
        System.out.println("Kết quả tìm kiếm:");
        for (Pet p : petList) {
            if (p.getPetName().toLowerCase().contains(keyword)) {
                p.displayData();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy thú cưng nào khớp với tên vừa nhập!");
        }
    }
}