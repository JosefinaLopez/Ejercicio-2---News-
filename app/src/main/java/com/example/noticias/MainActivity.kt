package com.example.noticias

import NoticiaAdapter
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noticias.databinding.ActivityMainBinding
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.coroutines.awaitString
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    //se define el binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //Se inicializa el binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Extra xd
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        GetNoticias()

    }

    private fun GetNoticias(){
        GlobalScope.launch (Dispatchers.IO){
            try {
                //Funcion Fuel, de la API (seria como el fetch en JavaScript
                val response = Fuel.get("https://api.thenewsapi.com/v1/news/top?api_token=LOnWtubhByUnRzrMFazjbrgT0EwbpbvswQXVT2De&locale=us&limit=10").awaitString()
                withContext(Dispatchers.Main) {
                    val gson = Gson()

                    val noticia = gson.fromJson(response, Noticia::class.java)
                    //binding.TextHello.text = noticia.data[1].title
                    // Instanciar el adaptador con la lista de noticias
                    val adapter = NoticiaAdapter(noticia.data, this@MainActivity)
                    binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    // Asignar el adaptador al RecyclerView
                    binding.recyclerView.adapter = adapter
                }

            }catch (ex:Exception){
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@MainActivity,
                        "Error: ${ex.message}",
                        Toast.LENGTH_LONG
                    ).show()
                    binding.TextHello.text = "Error: ${ex.message}"

                }
            }
        }
    }
}