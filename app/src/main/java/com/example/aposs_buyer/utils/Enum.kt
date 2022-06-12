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
    Pending, Confirmed, Delivering, Success, Cancel
}

enum class LoadingStatus{
    Loading, Success, Fail, Init
}
enum class Hanh(val value: Int) {
    Kim(2), Moc(4), Thuy(1), Hoa(3), Tho(0);

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
enum class Destiny{
    Can, Doai, Can1, Khon, Chan, Ton, Kham, Ly;

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
}
