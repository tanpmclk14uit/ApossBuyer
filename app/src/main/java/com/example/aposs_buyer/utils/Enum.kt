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
    Loading, Success, Fail
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
