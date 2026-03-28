👟 Android App - Bán Giày Thể Thao (Sneaker Store)
📌 Giới thiệu

Dự án Android Bán giày thể thao được xây dựng nhằm mục tiêu học tập và thực hành các kiến thức:

Gọi API (RESTful API)
Xử lý JSON
Sử dụng thư viện: Retrofit, Gson, Glide
Áp dụng mô hình kiến trúc MVP (Model - View - Presenter)
Thiết kế UI với Fragment + ViewPager + Bottom Navigation
🧱 Công nghệ sử dụng
Java / Kotlin (khuyến nghị Java nếu đang học cơ bản)
Android Studio
Retrofit (Call API)
Gson (Parse JSON)
Glide (Load ảnh)
RecyclerView (Hiển thị danh sách)
ViewPager + Fragment (Điều hướng màn hình)
MVP Architecture
📂 Cấu trúc thư mục (MVP)
com.example.sneakerstore
│
├── model
│   ├── Product.java
│   ├── Category.java
│
├── network
│   ├── ApiService.java
│   ├── RetrofitClient.java
│
├── presenter
│   ├── home
│   ├── cart
│   ├── product_detail
│
├── view
│   ├── activity
│   │   ├── MainActivity.java
│   │   ├── ProductDetailActivity.java
│   │
│   ├── fragment
│   │   ├── HomeFragment.java
│   │   ├── CartFragment.java
│   │   ├── AboutFragment.java
│
├── adapter
│   ├── ProductAdapter.java
│   ├── CategoryAdapter.java
│
├── utils
│   ├── CartManager.java
📱 Chức năng chính
1. 🏠 Màn hình chính (MainActivity)
Sử dụng ViewPager + Bottom Navigation
Có 3 tab:
Home
Cart
About
2. 🏠 FragmentHome

Hiển thị:

Thanh tìm kiếm
Danh sách Category
Danh sách Product

👉 Khi click:

Product → mở ProductDetailActivity
Category → lọc sản phẩm theo category_id
3. 🛒 FragmentCart

Hiển thị:

Danh sách sản phẩm trong giỏ hàng

Chức năng:

➕ Tăng số lượng
➖ Giảm số lượng
❌ Xóa sản phẩm
4. 👤 FragmentAbout

Hiển thị:

Thông tin người dùng (mock data)
5. 📄 Product Detail (ProductDetailActivity)

Hiển thị:

Hình ảnh (Glide)
Tên
Mô tả
Giá
Nút Thêm vào giỏ hàng
🌐 REST API
📦 Product API
[
  {
    "id": 1,
    "name": "Nike Air Zoom Pegasus 39",
    "description": "Giày chạy bộ nhẹ, êm ái",
    "thumbnail": "https://images.unsplash.com/photo-1542291026-7eec264c27ff",
    "price": 120.0,
    "quantity": 20,
    "category_id": 1
  }
]
📂 Category API
[
  {
    "id": 1,
    "name": "Running",
    "description": "Giày chạy bộ",
    "thumbnail": "https://images.unsplash.com/photo-1542291026-7eec264c27ff"
  }
]
🔌 Cấu hình Retrofit
Dependency
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
implementation 'com.github.bumptech.glide:glide:4.16.0'
annotationProcessor 'com.github.bumptech.glide:compiler:4.16.0'
ApiService
public interface ApiService {
    @GET("products")
    Call<List<Product>> getProducts();

    @GET("categories")
    Call<List<Category>> getCategories();
}
🧠 Mô hình MVP
1. Model
Chứa class dữ liệu (Product, Category)
2. View
Activity / Fragment
Chỉ hiển thị UI
Không xử lý logic
3. Presenter
Xử lý logic
Gọi API
Trả dữ liệu về View
🛒 Quản lý giỏ hàng

Tạo class:

CartManager.java

Chức năng:

Thêm sản phẩm
Xóa sản phẩm
Cập nhật số lượng
Lưu tạm (List hoặc SQLite nếu nâng cao)
🔄 Luồng hoạt động
App mở → gọi API → hiển thị Home
Click Product → mở chi tiết
Thêm vào giỏ hàng
Vào Cart:
Sửa số lượng
Xóa sản phẩm
Click Category → lọc sản phẩm
🚀 Hướng phát triển thêm
🔐 Đăng nhập / đăng ký
💳 Thanh toán (Payment)
❤️ Wishlist (yêu thích)
🔍 Tìm kiếm nâng cao
🌐 Kết nối API thật (Firebase / NodeJS)
🗄️ Lưu giỏ hàng bằng SQLite / Room
📱 UI đẹp hơn với Material Design
▶️ Hướng dẫn chạy project
Mở Android Studio
Chọn New Project
Copy source code vào project
Thêm dependencies
Cấu hình base URL trong Retrofit
Run trên Emulator hoặc điện thoại thật
📌 Ghi chú
Có thể dùng Postman để test API trước
API có thể mock bằng:
json-server
MockAPI
Firebase
👨‍💻 Tác giả

Sinh viên IT - Dự án học tập Android cơ bản + nâng cao MVP 🚀

⭐ Kết luận

Dự án giúp bạn:

Nắm rõ cách tổ chức code Android chuyên nghiệp
Hiểu cách tách logic bằng MVP
Biết cách làm app thực tế có API

🔥 Gợi ý: Nếu bạn làm tốt bài này → có thể nâng cấp thành đồ án lớn hoặc portfolio xin việc Android!