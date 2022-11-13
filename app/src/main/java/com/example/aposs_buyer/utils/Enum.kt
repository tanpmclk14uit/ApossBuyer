package com.example.aposs_buyer.utils

enum class DialogType{
    CartDialog, CheckOutDialog
}

enum class LoginState{
    Loading, Wait, Success
}

enum class SignUpState{
    Loading, Wait, Success, Verify
}

enum class OrderStatus{
    Pending, Confirmed, Delivering, Success, Cancel;

    fun toShowString(): String{
        return when(this){
            Pending -> "Đang chờ"
            Confirmed -> "Xác nhận"
            Delivering -> "Đang giao"
            Success -> "Thành công"
            Cancel -> "Đã hủy"
        }
    }
}

enum class PaymentStatus{
    Waiting,Pending, Completed;

    fun toShowString(): String{
        return when(this){
            Waiting -> "Chờ thanh toán"
            Pending -> "Chờ xác nhận"
            Completed -> "Thành công"
        }
    }
}

enum class LoadingStatus{
    Loading, Success, Fail, Init
}
enum class Nature(val value: Int) {
    Kim(0), Moc(1), Thuy(2), Hoa(3), Tho(4);

    override fun toString(): String {
        return when (this) {
            Kim -> "Kim"
            Moc -> "Mộc"
            Thuy -> "Thủy"
            Hoa -> "Hỏa"
            Tho -> "Thổ"
        }
    }

}

enum class Destiny(val value: Int){
    Can(0), Doai(0), Can1(4), Khon(4), Chan(1), Ton(1), Kham(2), Ly(3);

    override fun toString(): String {
        return when (this) {
            Can -> "Càn Kim"
            Doai -> "Đoài Kim"
            Can1 -> "Cấn Thổ"
            Khon -> "Khôn Thổ "
            Chan -> "Chấn Mộc"
            Ton -> "Tốn Mộc"
            Kham -> "Khảm Thủy"
            Ly -> "Ly Hỏa"
        }
    }
    fun getMessage(): String{
        return when (this) {
            Can, Doai -> "Cung Càn mang ý nghĩa là băng tuyết, đá, vàng và cung Đoài có nghĩa là trăng non, huyết nguyệt, tinh tú thuộc hành Kim chủ quản."
            Can1, Khon -> "Cung Cấn cấn có nghĩa là núi và cung Khôn có ý nghĩa là đất nên 2 cung này thuộc hành Thổ."
            Chan, Ton -> "Cung Chấn có nghĩa là là sấm sét, cây cỏ còn cung Tốn có nghĩa là phong. Chủ quản 2 cung này là hành Mộc."
            Kham -> "Trong dịch lý, cung Khảm có nghĩa là nước mưa, rượu, mặt trăng, tuyết rơi. Cung Khảm thuộc hành Thủy chủ quản."
            Ly -> "Ly tức là Hỏa, chính vì vậy cung này thuộc chủ quản của của hành Hỏa."
        }
    }
}
enum class SuitableLever(val value: Int){
    TuongSinh(4), TuongUng(4), CheNgu(3), KhongTuongTac(2), TuongKhac(1);

    override fun toString(): String {
        return when (this){
            TuongSinh -> "Tương sinh"
            TuongUng -> "Tương ứng"
            CheNgu -> "Chế ngự"
            KhongTuongTac -> "Không tương tác"
            TuongKhac -> "Tương khắc"
        }
    }
    fun getStringValue(): String{
        return value.toString()
    }
    fun getRecommendMessage(): String{
        return when (this){
            TuongSinh -> "Sản phẩm này rất phù hợp với bạn"
            TuongUng -> "Sản phẩm này rất phù hợp với bạn"
            CheNgu -> "Sản phẩm này phù hợp với bạn"
            KhongTuongTac -> "Bạn có thể dùng sản phẩm này, nó hầu như không có tương tác với mệnh của bạn."
            TuongKhac -> "Bạn không nên dùng sản phẩm này, hãy tìm thêm những sản phẩm khác của chúng tôi nhé."
        }
    }
}


