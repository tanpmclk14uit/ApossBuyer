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
