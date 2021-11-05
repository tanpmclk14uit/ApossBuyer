package com.example.aposs_buyer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.Category
import com.example.aposs_buyer.model.HomeProduct
import com.example.aposs_buyer.model.Image
import com.example.aposs_buyer.model.RankingProduct
import com.example.aposs_buyer.model.dto.ProductDTO
import com.example.aposs_buyer.responsitory.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository
)  : ViewModel() {

    //category data
    private var _categories = MutableLiveData<ArrayList<Category>>()
    val categories: LiveData<ArrayList<Category>> get() = _categories
    private var _displayCategory = MutableLiveData<Category>()
    val displayCategory: LiveData<Category> get() = _displayCategory
    var displayCategoryPurchase = MutableLiveData<String>()
    var displayCategoryProducts = MutableLiveData<String>()

    //ranking data
    private var _rankingProducts = MutableLiveData<ArrayList<RankingProduct>>()
    val rankingProducts: LiveData<ArrayList<RankingProduct>> get() = _rankingProducts
    private var _currentProductKind= MutableLiveData<String>()
    val currentProductKind: LiveData<String> get() = _currentProductKind
    // products data
    private var _products = MutableLiveData<ArrayList<HomeProduct>>()
    val products: LiveData<ArrayList<HomeProduct>> get() = _products

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        _categories.value = loadCategoriesData()
        setUpDisplayCategory(0)
        _rankingProducts.value = loadRankingData()
        loadProducts()
    }
    private fun loadProducts(){
        coroutineScope.launch {
            val getProductDeferred = productRepository.productService.getProductsAsync();
            try {
                val productDTO: ProductDTO = getProductDeferred.await()
                _products.value = ArrayList()
                Log.d("tag", productDTO.pageSize.toString())
            }
            catch (e: Exception){
                Log.d("exception", e.toString())
                _products.value = ArrayList()
            }
        }
    }
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
    private fun loadCategoriesData(): ArrayList<Category> {
        val imgURl1 = "https://i.pinimg.com/originals/58/f3/0a/58f30a4b7fc165af50e7052725b8bc09.jpg"
        val imgURL2 =
            "https://th.bing.com/th/id/R.380e118b26177f38ab0036ecb5014a0b?rik=coxm1X%2f%2bYh9klw&pid=ImgRaw&r=0"
        val imgURL3 = "https://www.hartehanks.com/wp-content/uploads/2021/09/MarTech.jpg"
        val imgCategory1 = Image(imgURl1)
        val imgCategory2 = Image(imgURL2)
        val imgCategory3 = Image(imgURL3)
        val sampleCategories = ArrayList<Category>()
        sampleCategories.add(Category(1, "Sport Clothes", 1147, 2172, 3.5f, imgCategory1))
        sampleCategories.add(Category(2, "Healthy Food", 2194, 1258, 4f, imgCategory2))
        sampleCategories.add(Category(3, "Laptop", 3153, 1917, 4.5f, imgCategory3))
        return sampleCategories
    }

    private fun loadRankingData(): ArrayList<RankingProduct> {
        val sampleRankingProducts = ArrayList<RankingProduct>()
        val imgURl1 =
            "https://www.tennisgearhub.com/wp-content/uploads/2020/09/Wilson-Mens-Hurry-Professional-25-Pickleball-Footwear-Racquetball-BlueWhitePurple-13.jpg"
        val imgURL2 =
            "https://th.bing.com/th/id/OIP.U6PJxFUyX6Nigx3Sv2ObpgHaHa?pid=ImgDet&w=2000&h=2000&rs=1"
        val imgURL3 =
            "https://leep.imgix.net/2021/01/bong-cai-trang-giup-giam-can_001.jpg?auto=compress&fm=pjpg&ixlib=php-1.2.1"
        val imgURL4 = "https://api.duniagames.co.id/api/content/upload/file/9607962621588584775.JPG"
        val imgRankingProduct1 = Image(imgURl1)
        val imgRankingProduct2 = Image(imgURL2)
        val imgRankingProduct3 = Image(imgURL3)
        val imgRankingProduct4 = Image(imgURL4)
        sampleRankingProducts.add(
            RankingProduct(
                1,
                imgRankingProduct1,
                "Wilson Mens Hurry Professional",
                958000,
                4f,
                true,
                1080,
                "Sport Shoe"
            )
        )
        sampleRankingProducts.add(
            RankingProduct(
                2,
                imgRankingProduct3,
                "White broccoli",
                46000,
                4.5f,
                false,
                1572,
                "Diet food"
            )
        )
        sampleRankingProducts.add(
            RankingProduct(
                3,
                imgRankingProduct2,
                "Wilson Mens shirt",
                582000,
                5f,
                false,
                1452,
                "Sport Shirt"
            )
        )
        sampleRankingProducts.add(
            RankingProduct(
                4,
                imgRankingProduct4,
                "Laptop asus Vivo Book",
                1958000,
                4.5f,
                true,
                1312,
                "Laptop Asus"
            )
        )
        return sampleRankingProducts
    }

    fun setUpDisplayCategory(currentPosition: Int) {
        val currentCategory: Category = _categories.value!![currentPosition]
        _displayCategory.value = currentCategory
        displayCategoryPurchase.value = String.format("%s purchased", currentCategory.totalPurchase)
        displayCategoryProducts.value = String.format("%s products", currentCategory.totalProduct)
    }
    fun setCurrentProductKind(currentPosition: Int){
        _currentProductKind.value = rankingProducts.value!![currentPosition].kind
    }

    fun addNewFavoriteProduct(productId: Long){
        // update database and add new favorite product
    }

    fun removeFavoriteProduct(productId: Long){
        // update database and remove favorite product
    }
}