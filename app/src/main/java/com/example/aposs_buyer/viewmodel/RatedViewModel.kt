package com.example.aposs_buyer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.Image
import com.example.aposs_buyer.model.RateImage
import com.example.aposs_buyer.model.RateItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RatedViewModel @Inject constructor(): ViewModel() {

    private val _listRated = MutableLiveData<ArrayList<RateItem>>()
    val listRated:LiveData<ArrayList<RateItem>> get() = _listRated

    init {
        _listRated.value = loadRatedItem()
    }

    fun loadRatedItem(): ArrayList<RateItem>
    {
            val sampleProductRating = ArrayList<RateItem>()
            val imgURl1 =
                "https://www.tennisgearhub.com/wp-content/uploads/2020/09/Wilson-Mens-Hurry-Professional-25-Pickleball-Footwear-Racquetball-BlueWhitePurple-13.jpg"
            val imgURL2 =
                "https://th.bing.com/th/id/OIP.U6PJxFUyX6Nigx3Sv2ObpgHaHa?pid=ImgDet&w=2000&h=2000&rs=1"
            val imgURL3 =
                "https://leep.imgix.net/2021/01/bong-cai-trang-giup-giam-can_001.jpg?auto=compress&fm=pjpg&ixlib=php-1.2.1"
            val imgProduct1 = Image(imgURl1)
            val imgProduct2 = Image(imgURL2)
            val imgProduct3 = Image(imgURL3)
            val imgProduct =
                Image("https://www.logolynx.com/images/logolynx/6b/6b72dfcff9c3a70b632dd47c88e842d9.png")
            val sampleImageRating = ArrayList<RateImage>()
            val sampleImageRating1 = ArrayList<RateImage>()
            sampleImageRating.add(RateImage(1, imgProduct1))
            sampleImageRating.add(RateImage(2, imgProduct2))
            sampleImageRating.add(RateImage(3, imgProduct3))
            sampleImageRating1.add(RateImage(4, imgProduct1))
            sampleProductRating.add(
                RateItem(
                    1,
                    "Peter Vu",
                    "Color: red, size: 30",
                    3,
                    20000,
                    4.1f,
                    "Good, fast delivery",
                    imgProduct,
                    sampleImageRating,
                )
            )
            sampleProductRating.add(
                RateItem(
                    2,
                    "Peter Pham",
                    "Color: red, size: 30",
                    4,
                    55000,
                    3f,
                    "Normal, good delivery",
                    imgProduct,
                    sampleImageRating,
                )
            )
            sampleProductRating.add(
                RateItem(
                    3,
                    "Tomy Teo",
                    "Color: red, size: 30",
                    5,
                    70000,
                    5f,
                    "Wilson's Pro Staff Classic has been tearing up the courts since 1986. Its thickly cushioned upper and anti-shock pad-enhanced heel and forefoot bring the comfort while the midfoot wrap offers extra stability. An all-court Duralast Supreme outsole adds to the life of the shoe.",
                    imgProduct,
                    sampleImageRating1,
                )
            )
            return sampleProductRating
    }
}