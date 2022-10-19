package coding.withze.dependencyinjectioncar.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import coding.withze.dependencyinjectioncar.model.ResponseDataCarItem
import coding.withze.dependencyinjectioncar.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class ViewModelCar : ViewModel(){

    lateinit var liveDataCar: MutableLiveData<List<ResponseDataCarItem>>


    init {
        liveDataCar = MutableLiveData()
    }

    fun getliveDataCar() :MutableLiveData<List<ResponseDataCarItem>>{
        return  liveDataCar
    }

    fun callApiCar(){
        RetrofitClient.instance.getAllCar()
            .enqueue(object :  Callback<List<ResponseDataCarItem>>{
                override fun onResponse(
                    call: Call<List<ResponseDataCarItem>>,
                    response: Response<List<ResponseDataCarItem>>
                ) {
                    if (response.isSuccessful){
                        liveDataCar.postValue(response.body())
                    }else{
                        liveDataCar.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<ResponseDataCarItem>>, t: Throwable) {
                    liveDataCar.postValue(null)
                }

            })
    }

}