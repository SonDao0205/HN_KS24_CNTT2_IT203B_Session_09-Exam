import entity.Product;
import factory.ProductFactory;
import repository.ProductDatabase;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductDatabase db = ProductDatabase.getInstance();

        while (true) {
            System.out.println("\n---------------------- QUẢN LÝ SẢN PHẨM ----------------------");
            System.out.println("1. Thêm mới sản phẩm");
            System.out.println("2. Xem danh sách sản phẩm");
            System.out.println("3. Cập nhật thông tin sản phẩm");
            System.out.println("4. Xoá sản phẩm");
            System.out.println("5. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());
                if (choice == 5) break;

                switch (choice) {
                    case 1:
                        System.out.print("Chọn loại (1. PhysicalProduct, 2. DigitalProduct): ");
                        int type = Integer.parseInt(sc.nextLine());
                        System.out.print("Nhập ID: ");
                        String id = sc.nextLine();
                        System.out.print("Nhập tên: ");
                        String name = sc.nextLine();
                        System.out.print("Nhập giá: ");
                        double price = Double.parseDouble(sc.nextLine());

                        double otherValue;
                        if (type == 1) {
                            System.out.print("Nhập trọng lượng (kg): ");
                        } else {
                            System.out.print("Nhập dung lượng (MB): ");
                        }
                        otherValue = Double.parseDouble(sc.nextLine());

                        Product newProd = ProductFactory.createProduct(type, id, name, price, otherValue);
                        if (newProd != null) {
                            db.addProduct(newProd);
                            System.out.println("Thêm thành công!");
                        }
                        break;

                    case 2:
                        if (db.getAll().isEmpty()) {
                            System.out.println("Kho hàng trống!");
                        } else {
                            db.getAll().forEach(Product::displayInfo);
                        }
                        break;

                    case 3:
                        System.out.print("Nhập ID sản phẩm cần cập nhật: ");
                        String upId = sc.nextLine();
                        Product found = db.findById(upId);
                        if (found != null) {
                            System.out.print("Tên mới: ");
                            found.setName(sc.nextLine());
                            System.out.print("Giá mới: ");
                            found.setPrice(Double.parseDouble(sc.nextLine()));
                            System.out.println("Cập nhật thành công!");
                        } else {
                            System.out.println("Không tìm thấy ID!");
                        }
                        break;

                    case 4:
                        System.out.print("Nhập ID cần xóa: ");
                        if (db.deleteProduct(sc.nextLine())) {
                            System.out.println("Xóa thành công!");
                        } else {
                            System.out.println("Không tìm thấy ID!");
                        }
                        break;
                    default:
                        System.out.println("Lựa chọn không phù hợp");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Lỗi nhập");
            }
        }
    }
}