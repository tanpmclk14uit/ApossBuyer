package com.example.aposs_buyer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.Image
import com.example.aposs_buyer.model.Order
import com.example.aposs_buyer.model.OrderBillingItem
import com.example.aposs_buyer.responsitory.DeliveryAddressRepository
import com.example.aposs_buyer.responsitory.OrderRepository
import com.example.aposs_buyer.utils.OrderStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class OrderViewModel @Inject constructor() : ViewModel() {

    private var _currentListOrder: MutableLiveData<ArrayList<Order>> = MutableLiveData()
    val currentListOrder: LiveData<ArrayList<Order>> get() = _currentListOrder

    private var _currentOrder: Order? = null
    val currentOrder: Order? get() = _currentOrder

    init {
        setCurrentOrders(loadPendingOrder())
    }

    fun setCurrentOrder(order: Order){
        _currentOrder = order
    }


    fun setCurrentOrders(currentOrder: ArrayList<Order>){
        _currentListOrder.value = currentOrder
    }

    fun loadPendingOrder(): ArrayList<Order> {
        val samplePendingOrders: ArrayList<Order> = ArrayList()
        val sampleBillingItems: ArrayList<OrderBillingItem> = ArrayList()
        val sampleBillingItems2: ArrayList<OrderBillingItem> = ArrayList()
        val imgURl1 =
            "https://www.tennisgearhub.com/wp-content/uploads/2020/09/Wilson-Mens-Hurry-Professional-25-Pickleball-Footwear-Racquetball-BlueWhitePurple-13.jpg"
        val imgURL2 =
            "https://th.bing.com/th/id/OIP.U6PJxFUyX6Nigx3Sv2ObpgHaHa?pid=ImgDet&w=2000&h=2000&rs=1"
        val imgURL3 =
            "https://leep.imgix.net/2021/01/bong-cai-trang-giup-giam-can_001.jpg?auto=compress&fm=pjpg&ixlib=php-1.2.1"
        val imgURL4 = "https://api.duniagames.co.id/api/content/upload/file/9607962621588584775.JPG"
        val imgBillingItem1 = Image(imgURl1)
        val imgBillingItem2 = Image(imgURL2)
        val imgBillingItem3 = Image(imgURL3)
        val imgBillingItem4 = Image(imgURL4)

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
        sampleBillingItems2.add(
            OrderBillingItem(
                4,
                imgBillingItem4,
                "Item 4",
                80000,
                1,
                "Color: Black, Size: 30"
            )
        )

        samplePendingOrders.add(
            Order(
                1, Date(), OrderStatus.Pending, "Mr Pham Minh Tan, 0343027600, 696 Hang Bai khu pho 6, Phuong linh trung, Thu Duc, Ho Chi Minh", sampleBillingItems, 140000
            )
        )
        samplePendingOrders.add(
            Order(
                2, Date(), OrderStatus.Pending, "Mr Pham Minh Tan, 0343025877, 696 Hang Bai khu pho 1, Phuong linh trung, Thu Duc, Ho Chi Minh", sampleBillingItems2, 80000
            )
        )
        return samplePendingOrders
    }
    fun loadConfirmedOrder(): ArrayList<Order> {
        return ArrayList()
    }
    fun loadDeliveringOrder(): ArrayList<Order> {
        val samplePendingOrders: ArrayList<Order> = ArrayList()
        val sampleBillingItems: ArrayList<OrderBillingItem> = ArrayList()
        val sampleBillingItems2: ArrayList<OrderBillingItem> = ArrayList()
        val imgURl1 =
            "https://www.tennisgearhub.com/wp-content/uploads/2020/09/Wilson-Mens-Hurry-Professional-25-Pickleball-Footwear-Racquetball-BlueWhitePurple-13.jpg"
        val imgURL2 =
            "https://th.bing.com/th/id/OIP.U6PJxFUyX6Nigx3Sv2ObpgHaHa?pid=ImgDet&w=2000&h=2000&rs=1"
        val imgURL3 =
            "https://leep.imgix.net/2021/01/bong-cai-trang-giup-giam-can_001.jpg?auto=compress&fm=pjpg&ixlib=php-1.2.1"
        val imgURL4 = "https://api.duniagames.co.id/api/content/upload/file/9607962621588584775.JPG"
        val imgBillingItem1 = Image(imgURl1)
        val imgBillingItem2 = Image(imgURL2)
        val imgBillingItem3 = Image(imgURL3)
        val imgBillingItem4 = Image(imgURL4)

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

        sampleBillingItems2.add(
            OrderBillingItem(
                4,
                imgBillingItem4,
                "Item 4",
                80000,
                1,
                "Color: Black, Size: 30"
            )
        )

        samplePendingOrders.add(
            Order(
                1, Date(), OrderStatus.Delivering, "Mr Pham Minh Tan, 0343027600, 696 Hang Bai khu pho 6, Phuong linh trung, Thu Duc, Ho Chi Minh", sampleBillingItems, 140000
            )
        )
        samplePendingOrders.add(
            Order(
                2, Date(), OrderStatus.Delivering, "Mr Pham Minh Tan, 0343025877, 696 Hang Bai khu pho 1, Phuong linh trung, Thu Duc, Ho Chi Minh", sampleBillingItems2, 80000
            )
        )
        return samplePendingOrders
    }
    fun loadSuccessOrder(): ArrayList<Order> {
        val samplePendingOrders: ArrayList<Order> = ArrayList()
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
                "Keychron k3v2 blue switch",
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


        samplePendingOrders.add(
            Order(
                1, Date(), OrderStatus.Success, "Mr Pham Minh Tan, 0343027600, 696 Hang Bai khu pho 6, Phuong linh trung, Thu Duc, Ho Chi Minh", sampleBillingItems, 140000
            )
        )
        return samplePendingOrders
    }
    fun loadCancelOrder(): ArrayList<Order> {
        val samplePendingOrders: ArrayList<Order> = ArrayList()
        val sampleBillingItems2: ArrayList<OrderBillingItem> = ArrayList()
        val imgURL4 = "https://api.duniagames.co.id/api/content/upload/file/9607962621588584775.JPG"

        val imgBillingItem4 = Image(imgURL4)


        sampleBillingItems2.add(
            OrderBillingItem(
                4,
                imgBillingItem4,
                "Item 4",
                80000,
                1,
                "Color: Black, Size: 30"
            )
        )


        samplePendingOrders.add(
            Order(
                2, Date(), OrderStatus.Cancel, "Mr Pham Minh Tan, 0343025877, 696 Hang Bai khu pho 1, Phuong linh trung, Thu Duc, Ho Chi Minh", sampleBillingItems2, 80000
            )
        )
        return samplePendingOrders
    }
}