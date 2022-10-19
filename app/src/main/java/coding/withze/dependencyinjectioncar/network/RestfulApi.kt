package coding.withze.dependencyinjectioncar.network

import coding.withze.dependencyinjectioncar.model.ResponseDataCarItem
import retrofit2.Call
import retrofit2.http.*

interface RestfulApi {

    @GET("admin/car")
    fun getAllCar(): Call<List<ResponseDataCarItem>>

    @GET("admin/car/{id}")
    fun getDetailCar(@Path("id") id : Int): Call<List<ResponseDataCarItem>>

    @DELETE("admin/car/{id}")
    fun deleteCar(@Path("id") id : Int) : Call<Int>
}