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

enum class ProductsStatus{
    Loading, Success, Fail
}

enum class CategoryStatus{
    Loading, Success, Fail
}

enum class KindStatus{
    Loading, Success, Fail
}


enum class DeliveryAddressStatus{
    Loading, Success, Fail
}