import java.util.Scanner;

public class Ex9_3 {

    public interface IShop {
        void displayData();
    }

    public static class Categories implements IShop {
        private static int autoId = 1;
        private int catalogId;
        private String catalogName;
        private String descriptions;
        private Boolean catalogStatus;

        public Categories() {
        }

        public Categories(int catalogId, String catalogName, String descriptions, Boolean catalogStatus) {
            this.catalogId = catalogId;
            this.catalogName = catalogName;
            this.descriptions = descriptions;
            this.catalogStatus = catalogStatus;
        }

        public int getCatalogId() {
            return catalogId;
        }

        public void setCatalogId(int catalogId) {
            this.catalogId = catalogId;
        }

        public String getCatalogName() {
            return catalogName;
        }

        public void setCatalogName(String catalogName) {
            this.catalogName = catalogName;
        }

        public String getDescriptions() {
            return descriptions;
        }

        public void setDescriptions(String descriptions) {
            this.descriptions = descriptions;
        }

        public Boolean getCatalogStatus() {
            return catalogStatus;
        }

        public void setCatalogStatus(Boolean catalogStatus) {
            this.catalogStatus = catalogStatus;
        }

        public static void updateAutoId(Categories[] arrCategories, int index) {
            int maxId = 0;
            for (int i = 0; i < index; i++) {
                if (arrCategories[i].getCatalogId() > maxId) {
                    maxId = arrCategories[i].getCatalogId();
                }
            }
            autoId = maxId + 1;
        }

        public void inputData(Scanner scanner, Categories[] arrCategories, int index) {
            updateAutoId(arrCategories, index);
            this.catalogId = autoId++;

            while (true) {
                System.out.print("Nhập tên danh mục (tối đa 50 ký tự, không trùng): ");
                String nameInput = scanner.nextLine().trim();
                if (nameInput.length() > 0 && nameInput.length() <= 50) {
                    boolean isExist = false;
                    for (int i = 0; i < index; i++) {
                        if (arrCategories[i].getCatalogName().equalsIgnoreCase(nameInput)) {
                            isExist = true;
                            break;
                        }
                    }
                    if (!isExist) {
                        this.catalogName = nameInput;
                        break;
                    } else {
                        System.out.println("Tên danh mục đã tồn tại! Vui lòng nhập lại.");
                    }
                } else {
                    System.out.println("Tên danh mục không được rỗng và không dài quá 50 ký tự!");
                }
            }

            System.out.print("Nhập mô tả danh mục: ");
            this.descriptions = scanner.nextLine();

            while (true) {
                System.out.print("Nhập trạng thái danh mục (true - Hoạt động / false - Không hoạt động): ");
                String statusInput = scanner.nextLine().trim().toLowerCase();
                if (statusInput.equals("true") || statusInput.equals("false")) {
                    this.catalogStatus = Boolean.parseBoolean(statusInput);
                    break;
                } else {
                    System.out.println("Trạng thái chỉ nhận giá trị true hoặc false!");
                }
            }
        }

        @Override
        public void displayData() {
            System.out.println("Mã DM: " + catalogId + " | Tên DM: " + catalogName + " | Mô tả: " + descriptions + " | Trạng thái: " + (catalogStatus ? "Hoạt động" : "Không hoạt động"));
        }
    }

    public static class Product implements IShop {
        private String productId;
        private String productName;
        private float price;
        private String description;
        private int catalogId;
        private int productStatus;

        public Product() {
        }

        public Product(String productId, String productName, float price, String description, int catalogId, int productStatus) {
            this.productId = productId;
            this.productName = productName;
            this.price = price;
            this.description = description;
            this.catalogId = catalogId;
            this.productStatus = productStatus;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getCatalogId() {
            return catalogId;
        }

        public void setCatalogId(int catalogId) {
            this.catalogId = catalogId;
        }

        public int getProductStatus() {
            return productStatus;
        }

        public void setProductStatus(int productStatus) {
            this.productStatus = productStatus;
        }

        public void inputData(Scanner scanner, Product[] arrProduct, int indexProduct, Categories[] arrCategories, int indexCatalog) {
            while (true) {
                System.out.print("Nhập mã sản phẩm (4 ký tự, bắt đầu C, S hoặc A): ");
                String idInput = scanner.nextLine().trim();
                if (idInput.matches("^[CSA].{3}$")) {
                    boolean isExist = false;
                    for (int i = 0; i < indexProduct; i++) {
                        if (arrProduct[i].getProductId().equalsIgnoreCase(idInput)) {
                            isExist = true;
                            break;
                        }
                    }
                    if (!isExist) {
                        this.productId = idInput;
                        break;
                    } else {
                        System.out.println("Mã sản phẩm đã tồn tại! Vui lòng nhập lại.");
                    }
                } else {
                    System.out.println("Mã gồm 4 ký tự và bắt đầu bằng C, S, hoặc A!");
                }
            }

            while (true) {
                System.out.print("Nhập tên sản phẩm (10 - 50 ký tự): ");
                String nameInput = scanner.nextLine().trim();
                if (nameInput.length() >= 10 && nameInput.length() <= 50) {
                    boolean isExist = false;
                    for (int i = 0; i < indexProduct; i++) {
                        if (arrProduct[i].getProductName().equalsIgnoreCase(nameInput)) {
                            isExist = true;
                            break;
                        }
                    }
                    if (!isExist) {
                        this.productName = nameInput;
                        break;
                    } else {
                        System.out.println("Tên sản phẩm đã tồn tại! Vui lòng nhập lại.");
                    }
                } else {
                    System.out.println("Tên sản phẩm phải có độ dài từ 10 đến 50 ký tự!");
                }
            }

            while (true) {
                System.out.print("Nhập giá sản phẩm (> 0): ");
                try {
                    float priceInput = Float.parseFloat(scanner.nextLine());
                    if (priceInput > 0) {
                        this.price = priceInput;
                        break;
                    } else {
                        System.out.println("Giá phải lớn hơn 0!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Vui lòng nhập giá trị số hợp lệ!");
                }
            }

            System.out.print("Nhập mô tả sản phẩm: ");
            this.description = scanner.nextLine();

            System.out.println("\n--- DANH SÁCH DANH MỤC HIỆN CÓ ---");
            for (int i = 0; i < indexCatalog; i++) {
                System.out.println((i + 1) + ". ID: " + arrCategories[i].getCatalogId() + " - " + arrCategories[i].getCatalogName());
            }
            while (true) {
                int catChoice = inputInt(scanner, "Chọn mã danh mục cho sản phẩm: ");
                boolean isExist = false;
                for (int i = 0; i < indexCatalog; i++) {
                    if (arrCategories[i].getCatalogId() == catChoice) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    this.catalogId = catChoice;
                    break;
                } else {
                    System.out.println("Mã danh mục không tồn tại! Vui lòng chọn lại.");
                }
            }

            while (true) {
                int statusInput = inputInt(scanner, "Nhập trạng thái sản phẩm (0: Đang bán - 1: Hết hàng - 2: Không bán): ");
                if (statusInput >= 0 && statusInput <= 2) {
                    this.productStatus = statusInput;
                    break;
                } else {
                    System.out.println("Trạng thái chỉ được nhận 0, 1 hoặc 2!");
                }
            }
        }

        @Override
        public void displayData() {
            String statusStr = "";
            switch (productStatus) {
                case 0:
                    statusStr = "Đang bán";
                    break;
                case 1:
                    statusStr = "Hết hàng";
                    break;
                case 2:
                    statusStr = "Không bán";
                    break;
            }
            System.out.println("Mã SP: " + productId + " | Tên SP: " + productName + " | Giá: " + price + " | Mô tả: " + description + " | Mã DM: " + catalogId + " | Trạng thái: " + statusStr);
        }
    }

    private static Categories[] arrCategories = new Categories[100];
    private static int indexCatalog = 0;

    private static Product[] arrProduct = new Product[100];
    private static int indexProduct = 0;

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n******************SHOP MENU*******************");
            System.out.println("1. Quản lý danh mục sản phẩm");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            choice = inputInt(scanner, "Lựa chọn của bạn: ");

            switch (choice) {
                case 1:
                    menuCategories();
                    break;
                case 2:
                    menuProduct();
                    break;
                case 3:
                    System.out.println("Đã kết thúc chương trình!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 3);

        scanner.close();
    }

    private static void menuCategories() {
        int choice;
        do {
            System.out.println("\n********************CATEGORIES MENU***********");
            System.out.println("1. Nhập thông tin các danh mục");
            System.out.println("2. Hiển thị thông tin các danh mục");
            System.out.println("3. Cập nhật thông tin danh mục");
            System.out.println("4. Xóa danh mục");
            System.out.println("5. Cập nhật trạng thái danh mục");
            System.out.println("6. Thoát");
            choice = inputInt(scanner, "Lựa chọn của bạn: ");

            switch (choice) {
                case 1:
                    int count = inputInt(scanner, "Nhập số lượng danh mục muốn thêm: ");
                    for (int i = 0; i < count; i++) {
                        if (indexCatalog >= arrCategories.length) {
                            System.out.println("Mảng danh mục đã đầy!");
                            break;
                        }
                        System.out.println("\nNhập danh mục thứ " + (i + 1) + ":");
                        Categories cat = new Categories();
                        cat.inputData(scanner, arrCategories, indexCatalog);
                        arrCategories[indexCatalog++] = cat;
                    }
                    System.out.println("Thêm danh mục thành công!");
                    break;
                case 2:
                    System.out.println("\n--- DANH SÁCH DANH MỤC ---");
                    if (indexCatalog == 0) {
                        System.out.println("Chưa có danh mục nào!");
                    } else {
                        for (int i = 0; i < indexCatalog; i++) {
                            arrCategories[i].displayData();
                        }
                    }
                    break;
                case 3:
                    int updateId = inputInt(scanner, "Nhập mã danh mục cần cập nhật: ");
                    int updateIndex = -1;
                    for (int i = 0; i < indexCatalog; i++) {
                        if (arrCategories[i].getCatalogId() == updateId) {
                            updateIndex = i;
                            break;
                        }
                    }
                    if (updateIndex != -1) {
                        System.out.println("Cập nhật lại thông tin danh mục ID: " + updateId);
                        while (true) {
                            System.out.print("Nhập tên danh mục mới: ");
                            String nameInput = scanner.nextLine().trim();
                            if (nameInput.length() > 0 && nameInput.length() <= 50) {
                                boolean isExist = false;
                                for (int i = 0; i < indexCatalog; i++) {
                                    if (i != updateIndex && arrCategories[i].getCatalogName().equalsIgnoreCase(nameInput)) {
                                        isExist = true;
                                        break;
                                    }
                                }
                                if (!isExist) {
                                    arrCategories[updateIndex].setCatalogName(nameInput);
                                    break;
                                } else {
                                    System.out.println("Tên danh mục đã tồn tại!");
                                }
                            } else {
                                System.out.println("Tên danh mục dài tối đa 50 ký tự!");
                            }
                        }
                        System.out.print("Nhập mô tả mới: ");
                        arrCategories[updateIndex].setDescriptions(scanner.nextLine());

                        while (true) {
                            System.out.print("Nhập trạng thái mới (true / false): ");
                            String statusInput = scanner.nextLine().trim().toLowerCase();
                            if (statusInput.equals("true") || statusInput.equals("false")) {
                                arrCategories[updateIndex].setCatalogStatus(Boolean.parseBoolean(statusInput));
                                break;
                            } else {
                                System.out.println("Trạng thái chỉ nhận true hoặc false!");
                            }
                        }
                        System.out.println("Cập nhật danh mục thành công!");
                    } else {
                        System.out.println("Mã danh mục không tồn tại!");
                    }
                    break;
                case 4:
                    int deleteId = inputInt(scanner, "Nhập mã danh mục cần xóa: ");
                    int deleteIndex = -1;
                    for (int i = 0; i < indexCatalog; i++) {
                        if (arrCategories[i].getCatalogId() == deleteId) {
                            deleteIndex = i;
                            break;
                        }
                    }
                    if (deleteIndex != -1) {
                        boolean hasProduct = false;
                        for (int i = 0; i < indexProduct; i++) {
                            if (arrProduct[i].getCatalogId() == deleteId) {
                                hasProduct = true;
                                break;
                            }
                        }
                        if (hasProduct) {
                            System.out.println("Không thể xóa danh mục vì đang chứa sản phẩm!");
                        } else {
                            for (int i = deleteIndex; i < indexCatalog - 1; i++) {
                                arrCategories[i] = arrCategories[i + 1];
                            }
                            arrCategories[--indexCatalog] = null;
                            System.out.println("Xóa danh mục thành công!");
                        }
                    } else {
                        System.out.println("Mã danh mục không tồn tại!");
                    }
                    break;
                case 5:
                    int statusId = inputInt(scanner, "Nhập mã danh mục cần cập nhật trạng thái: ");
                    boolean foundStatus = false;
                    for (int i = 0; i < indexCatalog; i++) {
                        if (arrCategories[i].getCatalogId() == statusId) {
                            arrCategories[i].setCatalogStatus(!arrCategories[i].getCatalogStatus());
                            System.out.println("Cập nhật trạng thái thành công! Trạng thái mới: " + arrCategories[i].getCatalogStatus());
                            foundStatus = true;
                            break;
                        }
                    }
                    if (!foundStatus) {
                        System.out.println("Mã danh mục không tồn tại!");
                    }
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 6);
    }

    private static void menuProduct() {
        int choice;
        do {
            System.out.println("\n*******************PRODUCT MANAGEMENT*****************");
            System.out.println("1. Nhập thông tin các sản phẩm");
            System.out.println("2. Hiển thị thông tin các sản phẩm");
            System.out.println("3. Sắp xếp các sản phẩm theo giá");
            System.out.println("4. Cập nhật thông tin sản phẩm theo mã sản phẩm");
            System.out.println("5. Xóa sản phẩm theo mã sản phẩm");
            System.out.println("6. Tìm kiếm các sản phẩm theo tên sản phẩm");
            System.out.println("7. Tìm kiếm sản phẩm trong khoảng giá a – b");
            System.out.println("8. Thoát");
            choice = inputInt(scanner, "Lựa chọn của bạn: ");

            switch (choice) {
                case 1:
                    if (indexCatalog == 0) {
                        System.out.println("Chưa có danh mục nào! Vui lòng tạo danh mục trước khi thêm sản phẩm.");
                        break;
                    }
                    int count = inputInt(scanner, "Nhập số lượng sản phẩm muốn thêm: ");
                    for (int i = 0; i < count; i++) {
                        if (indexProduct >= arrProduct.length) {
                            System.out.println("Mảng sản phẩm đã đầy!");
                            break;
                        }
                        System.out.println("\nNhập sản phẩm thứ " + (i + 1) + ":");
                        Product p = new Product();
                        p.inputData(scanner, arrProduct, indexProduct, arrCategories, indexCatalog);
                        arrProduct[indexProduct++] = p;
                    }
                    System.out.println("Thêm sản phẩm thành công!");
                    break;
                case 2:
                    System.out.println("\n--- DANH SÁCH SẢN PHẨM ---");
                    if (indexProduct == 0) {
                        System.out.println("Chưa có sản phẩm nào!");
                    } else {
                        for (int i = 0; i < indexProduct; i++) {
                            arrProduct[i].displayData();
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < indexProduct - 1; i++) {
                        for (int j = i + 1; j < indexProduct; j++) {
                            if (arrProduct[i].getPrice() > arrProduct[j].getPrice()) {
                                Product temp = arrProduct[i];
                                arrProduct[i] = arrProduct[j];
                                arrProduct[j] = temp;
                            }
                        }
                    }
                    System.out.println("Đã sắp xếp sản phẩm tăng dần theo giá!");
                    break;
                case 4:
                    System.out.print("Nhập mã sản phẩm cần cập nhật: ");
                    String updateId = scanner.nextLine().trim();
                    int updateIndex = -1;
                    for (int i = 0; i < indexProduct; i++) {
                        if (arrProduct[i].getProductId().equalsIgnoreCase(updateId)) {
                            updateIndex = i;
                            break;
                        }
                    }
                    if (updateIndex != -1) {
                        System.out.println("Cập nhật lại thông tin sản phẩm " + updateId);
                        while (true) {
                            System.out.print("Nhập tên sản phẩm mới: ");
                            String nameInput = scanner.nextLine().trim();
                            if (nameInput.length() >= 10 && nameInput.length() <= 50) {
                                boolean isExist = false;
                                for (int i = 0; i < indexProduct; i++) {
                                    if (i != updateIndex && arrProduct[i].getProductName().equalsIgnoreCase(nameInput)) {
                                        isExist = true;
                                        break;
                                    }
                                }
                                if (!isExist) {
                                    arrProduct[updateIndex].setProductName(nameInput);
                                    break;
                                } else {
                                    System.out.println("Tên sản phẩm đã tồn tại!");
                                }
                            } else {
                                System.out.println("Tên sản phẩm phải từ 10 đến 50 ký tự!");
                            }
                        }

                        while (true) {
                            System.out.print("Nhập giá mới (> 0): ");
                            try {
                                float priceInput = Float.parseFloat(scanner.nextLine());
                                if (priceInput > 0) {
                                    arrProduct[updateIndex].setPrice(priceInput);
                                    break;
                                } else {
                                    System.out.println("Giá phải lớn hơn 0!");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Vui lòng nhập số!");
                            }
                        }

                        System.out.print("Nhập mô tả mới: ");
                        arrProduct[updateIndex].setDescription(scanner.nextLine());

                        System.out.println("\n--- DANH SÁCH DANH MỤC HIỆN CÓ ---");
                        for (int i = 0; i < indexCatalog; i++) {
                            System.out.println((i + 1) + ". ID: " + arrCategories[i].getCatalogId() + " - " + arrCategories[i].getCatalogName());
                        }
                        while (true) {
                            int catChoice = inputInt(scanner, "Chọn mã danh mục mới: ");
                            boolean isExist = false;
                            for (int i = 0; i < indexCatalog; i++) {
                                if (arrCategories[i].getCatalogId() == catChoice) {
                                    isExist = true;
                                    break;
                                }
                            }
                            if (isExist) {
                                arrProduct[updateIndex].setCatalogId(catChoice);
                                break;
                            } else {
                                System.out.println("Mã danh mục không tồn tại!");
                            }
                        }

                        while (true) {
                            int statusInput = inputInt(scanner, "Nhập trạng thái mới (0: Đang bán - 1: Hết hàng - 2: Không bán): ");
                            if (statusInput >= 0 && statusInput <= 2) {
                                arrProduct[updateIndex].setProductStatus(statusInput);
                                break;
                            } else {
                                System.out.println("Trạng thái chỉ nhận 0, 1 hoặc 2!");
                            }
                        }
                        System.out.println("Cập nhật thông tin sản phẩm thành công!");
                    } else {
                        System.out.println("Mã sản phẩm không tồn tại!");
                    }
                    break;
                case 5:
                    System.out.print("Nhập mã sản phẩm cần xóa: ");
                    String deleteId = scanner.nextLine().trim();
                    int deleteIndex = -1;
                    for (int i = 0; i < indexProduct; i++) {
                        if (arrProduct[i].getProductId().equalsIgnoreCase(deleteId)) {
                            deleteIndex = i;
                            break;
                        }
                    }
                    if (deleteIndex != -1) {
                        for (int i = deleteIndex; i < indexProduct - 1; i++) {
                            arrProduct[i] = arrProduct[i + 1];
                        }
                        arrProduct[--indexProduct] = null;
                        System.out.println("Xóa sản phẩm thành công!");
                    } else {
                        System.out.println("Mã sản phẩm không tồn tại!");
                    }
                    break;
                case 6:
                    System.out.print("Nhập tên sản phẩm cần tìm: ");
                    String nameSearch = scanner.nextLine().toLowerCase().trim();
                    boolean foundByName = false;
                    System.out.println("\nKết quả tìm kiếm:");
                    for (int i = 0; i < indexProduct; i++) {
                        if (arrProduct[i].getProductName().toLowerCase().contains(nameSearch)) {
                            arrProduct[i].displayData();
                            foundByName = true;
                        }
                    }
                    if (!foundByName) {
                        System.out.println("Không tìm thấy sản phẩm nào!");
                    }
                    break;
                case 7:
                    float minPrice = inputFloat(scanner, "Nhập giá bắt đầu (a): ");
                    float maxPrice = inputFloat(scanner, "Nhập giá kết thúc (b): ");
                    boolean foundByPrice = false;
                    System.out.println("\nKết quả tìm kiếm trong khoảng giá [" + minPrice + " - " + maxPrice + "]:");
                    for (int i = 0; i < indexProduct; i++) {
                        if (arrProduct[i].getPrice() >= minPrice && arrProduct[i].getPrice() <= maxPrice) {
                            arrProduct[i].displayData();
                            foundByPrice = true;
                        }
                    }
                    if (!foundByPrice) {
                        System.out.println("Không có sản phẩm nào nằm trong khoảng giá này!");
                    }
                    break;
                case 8:
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 8);
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

    public static float inputFloat(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Float.parseFloat(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
            }
        }
    }
}