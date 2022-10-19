package coding.withze.dependencyinjectioncar.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import coding.withze.dependencyinjectioncar.R
import coding.withze.dependencyinjectioncar.databinding.ActivityMainBinding
import coding.withze.dependencyinjectioncar.model.ResponseDataCarItem
import coding.withze.dependencyinjectioncar.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var  binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataCar()
    }

    fun getDataCar(){
        RetrofitClient.instance.getAllCar()
            .enqueue(object : Callback<List<ResponseDataCarItem>> {
                override fun onResponse(
                    call: Call<List<ResponseDataCarItem>>,
                    response: Response<List<ResponseDataCarItem>>
                ) {
                    if (response.isSuccessful){
                        var dataCar = response.body()
                        binding.rvCar.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                        binding.rvCar.adapter = CarAdapter(dataCar!!)
                    }else{
                        Toast.makeText(this@MainActivity, "Failed to Load Data", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<ResponseDataCarItem>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Something Wrong", Toast.LENGTH_SHORT).show()
                }

            })
    }

}