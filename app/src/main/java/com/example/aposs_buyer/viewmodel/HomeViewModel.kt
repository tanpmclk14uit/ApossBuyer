package com.example.aposs_buyer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aposs_buyer.model.Category
import com.example.aposs_buyer.model.HomeProduct
import com.example.aposs_buyer.model.Image
import com.example.aposs_buyer.model.RankingProduct
import com.example.aposs_buyer.model.dto.DetailCategoryDTO
import com.example.aposs_buyer.model.dto.ProductDTO
import com.example.aposs_buyer.model.dto.ProductResponseDTO
import com.example.aposs_buyer.responsitory.CategoryRepository
import com.example.aposs_buyer.responsitory.ProductRepository
import com.example.aposs_buyer.utils.CategoryStatus
import com.example.aposs_buyer.utils.ProductsStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.stream.Collectors
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {

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
    private var _currentProductKind = MutableLiveData<String>()
    val currentProductKind: LiveData<String> get() = _currentProductKind

    // products data
    private var _products = MutableLiveData<List<HomeProduct>>()
    val products: LiveData<List<HomeProduct>> get() = _products

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var currentPage = 1
    private var isLastPage = false
    private var _status = MutableLiveData<ProductsStatus>()
    val status: LiveData<ProductsStatus> get()= _status

    private var _categoryStatus = MutableLiveData<CategoryStatus>()
    val categoryStatus: LiveData<CategoryStatus> get()= _categoryStatus

    init {
        loadCategoriesData()
        if(_categoryStatus.value ==  CategoryStatus.Success)
        {
            setUpDisplayCategory(0)
        }
        _rankingProducts.value = loadRankingData()
        _products.value = ArrayList()
        _status.value = ProductsStatus.Success
        loadProducts()
    }

    fun loadProducts() {
        if (!isLastPage && _status.value!= ProductsStatus.Loading) {
            _status.value = ProductsStatus.Loading
            coroutineScope.launch {
                val getProductDeferred =
                    productRepository.productService.getProductsAsync(currentPage);
                try {
                    val productResponseDTO: ProductResponseDTO = getProductDeferred.await()
                    val productsInCurrentPage = productResponseDTO.content.stream()
                        .map { productDTO -> Converter.convertToHomeProduct(productDTO) }.collect(
                            Collectors.toList()
                        )
                    _products.value = Converter.concatenate(_products.value!!, productsInCurrentPage)
                    if (productResponseDTO.last) {
                        isLastPage = true
                    } else {
                        currentPage++
                    }
                    _status.value = ProductsStatus.Success
                } catch (e: Exception) {
                    Log.d("exception", e.toString())
                    _status.value = ProductsStatus.Fail
                }
            }
        }
    }

    object Converter {
         fun convertToHomeProduct(productDTO: ProductDTO): HomeProduct {
            return HomeProduct(
                id = productDTO.id,
                image = Image(productDTO.image),
                name = productDTO.name,
                rating = productDTO.rating.toFloat(),
                price = productDTO.price,
                purchased = productDTO.purchased
            )
        }

        fun convertDetailCategoryDTOToCategory(categoryDTO: DetailCategoryDTO): Category{
            return Category(
                id = categoryDTO.id,
                name = categoryDTO.name,
                totalProduct = categoryDTO.totalProducts,
                totalPurchase = categoryDTO.totalPurchases,
                rating = categoryDTO.rating,
                mainImage = Image(categoryDTO.images[0])
            )
        }
        fun <T> concatenate(vararg lists: List<T>): List<T> {
            val result: MutableList<T> = ArrayList()
            for (list in lists) {
                result.addAll(list)
            }
            return result
        }

        fun <T> concatenateMutable(vararg lists: MutableList<T>): MutableList<T> {
            val result: MutableList<T> = ArrayList()
            for (list in lists) {
                result.addAll(list)
            }
            return result
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private fun loadCategoriesData() {
        _categoryStatus.value = CategoryStatus.Loading
        viewModelScope.launch {
            val listDetailCategoryDTO = categoryRepository.categoryService.getAllCategory()
            try {
                _categories.value = ArrayList(listDetailCategoryDTO.stream().map {
                    Converter.convertDetailCategoryDTOToCategory(it)
                }.collect(Collectors.toList()))
                _categoryStatus.value = CategoryStatus.Success
            }
            catch (e:java.lang.Exception){
                Log.e("Exception", e.toString())
                _categoryStatus.value = CategoryStatus.Fail
            }
        }
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

    fun setCurrentProductKind(currentPosition: Int) {
        _currentProductKind.value = rankingProducts.value!![currentPosition].kind
    }

    fun addNewFavoriteProduct(productId: Long) {
        // update database and add new favorite product
    }

    fun removeFavoriteProduct(productId: Long) {
        // update database and remove favorite product
    }
}