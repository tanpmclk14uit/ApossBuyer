package com.example.aposs_buyer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.Image
import com.example.aposs_buyer.model.Order
import com.example.aposs_buyer.model.OrderBillingItem
import com.example.aposs_buyer.model.OrderDeliveringState
import com.example.aposs_buyer.utils.OrderStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


@HiltViewModel
class OrderDetailViewModel @Inject constructor() : ViewModel() {

    private var _detailOrder: MutableLiveData<Order> = MutableLiveData()
    val detailOrder: LiveData<Order> get() = _detailOrder

    private var _orderDeliveringState: MutableLiveData<ArrayList<OrderDeliveringState>> =
        MutableLiveData()
    val orderDeliveringState: LiveData<ArrayList<OrderDeliveringState>> get() = _orderDeliveringState

    fun setCurrentOrder(order: Order) {
        _detailOrder.value = order
        _orderDeliveringState.value = loadOrderDeliveringState(order.id)

    }

    fun setDetailOrderById(id: Long) {
        _detailOrder.value = loadDetailOrderById(id)
        _orderDeliveringState.value = loadOrderDeliveringState(id)
        Log.d("OrderDetailViewModel", "Order: $id")
    }

    private fun loadDetailOrderById(id: Long): Order {
        val sampleBillingItems: ArrayList<OrderBillingItem> = ArrayList()
        val imgURl1 =
            "https://www.tennisgearhub.com/wp-content/uploads/2020/09/Wilson-Mens-Hurry-Professional-25-Pickleball-Footwear-Racquetball-BlueWhitePurple-13.jpg"
        val imgURL2 =
            "https://th.bing.com/th/id/OIP.U6PJxFUyX6Nigx3Sv2ObpgHaHa?pid=ImgDet&w=2000&h=2000&rs=1"
        val imgURL3 =
            "https://leep.imgix.net/2021/01/bong-cai-trang-giup-giam-can_001.jpg?auto=compress&fm=pjpg&ixlib=php-1.2.1"
        val imgBillingItem1 = Image(imgURl1)
        val imgBillingItem2 = Image(imgURL2)
        val imgBillingItem3 = Image(imgURL3)

        sampleBillingItems.add(
            OrderBillingItem(
                1,
                imgBillingItem1,
                "Item 1",
                20000,
                1,
                "Color: Red, Size: 30"
            )
        )
        sampleBillingItems.add(
            OrderBillingItem(
                2,
                imgBillingItem2,
                "Item 2",
                50000,
                1,
                "Color: Blue, Size: 30"
            )
        )
        sampleBillingItems.add(
            OrderBillingItem(
                3,
                imgBillingItem3,
                "Item 3",
                70000,
                1,
                "Color: Pink, Size: 30"
            )
        )
        return Order(
            id,
            Date(),
            OrderStatus.Pending,
            "Mr Pham Minh Tan, 0343027600, 696 Hang Bai khu pho 6, Phuong linh trung, Thu Duc, Ho Chi Minh",
            sampleBillingItems,
            140000
        )
    }

    private fun loadOrderDeliveringState(id: Long): ArrayList<OrderDeliveringState> {
        val sample: ArrayList<OrderDeliveringState> = ArrayList()
        sample.add(OrderDeliveringState(1, "Placed order", Date()))
        sample.add(OrderDeliveringState(2, "Delivery to shipping units", Date()))
        sample.add(OrderDeliveringState(3, "Orders are being shipped", Date()))
        sample.add(OrderDeliveringState(4, "Orders are being shipped", Date()))
        return sample
    }
}