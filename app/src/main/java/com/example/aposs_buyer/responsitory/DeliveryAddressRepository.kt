package com.example.aposs_buyer.responsitory

import com.example.aposs_buyer.model.dto.DeliveryAddressDTO
import com.example.aposs_buyer.model.dto.DistrictDTO
import com.example.aposs_buyer.model.dto.ProvinceDTO
import com.example.aposs_buyer.model.dto.WardDTO
import com.example.aposs_buyer.responsitory.webservice.*
import retrofit2.Response
import javax.inject.Inject

class DeliveryAddressRepository @Inject constructor() {

    private val deliveryAddressService : DeliveryAddressService by lazy {
        RetrofitInstance.retrofit.create(DeliveryAddressService::class.java)
    }
    private val provinceService: ProvinceService by lazy {
        RetrofitInstance.retrofit.create(ProvinceService::class.java)
    }
    private val wardService: WardService by lazy {
        RetrofitInstance.retrofit.create(WardService::class.java)
    }
    private val districtService: DistrictService by lazy {
        RetrofitInstance.retrofit.create(DistrictService::class.java)
    }
    suspend fun getAllUserAddressByAccessToken(token: String): Response<List<DeliveryAddressDTO>>{
        return deliveryAddressService.getAllDeliveryAddressService(token)
    }
    suspend fun getUserDefaultAddress(token: String):Response<DeliveryAddressDTO>{
        return deliveryAddressService.getDefaultAddress(token)
    }

    suspend fun getAllProvince():Response<List<ProvinceDTO>>{
        return provinceService.getAllProvince()
    }
    suspend fun getAllDistrictOfProvinceById(provinceId: Long):Response<List<DistrictDTO>>{
        return districtService.getAllDistrictById(provinceId)
    }

    suspend fun getAllWardsOfDistrictById(districtId: Long): Response<List<WardDTO>>{
        return wardService.getAllWardById(districtId)
    }



}