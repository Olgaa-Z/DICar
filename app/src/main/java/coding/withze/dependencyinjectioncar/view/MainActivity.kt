package coding.withze.dependencyinjectioncar.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import coding.withze.dependencyinjectioncar.R
import coding.withze.dependencyinjectioncar.databinding.ActivityMainBinding
import coding.withze.dependencyinjectioncar.model.ResponseDataCarItem
import coding.withze.dependencyinjectioncar.network.RetrofitClient
import coding.withze.dependencyinjectioncar.viewmodel.ViewModelCar
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var  binding : ActivityMainBinding
    lateinit var carAdapter : CarAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setVmtoAdapter()
    }

    fun setVmtoAdapter(){
        val viewModel = ViewModelProvider(this).get(ViewModelCar::class.java)
        viewModel.callApiCar()
        viewModel.getliveDataCar().observe(this, Observer {
            carAdapter = CarAdapter(it)
            if ( it != null){
                binding.rvCar.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                binding.rvCar.adapter = CarAdapter(it)

            }

        })
    }
}