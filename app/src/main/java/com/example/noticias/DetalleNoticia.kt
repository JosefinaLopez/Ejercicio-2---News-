package com.example.noticias

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.noticias.databinding.ActivityDetalleNoticiaBinding
import com.squareup.picasso.Picasso

class DetalleNoticia : AppCompatActivity() {
    private lateinit var binding: ActivityDetalleNoticiaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //Se inicializa el binding
        binding = ActivityDetalleNoticiaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Obtener extras del Intent
        val extras = intent.extras

        if (extras != null) {
            val img = extras.getString("Imagen")
            val titulo = extras.getString("Titulo")
            val descripcion = extras.getString("Descripcion")
            val link = extras.getString("Link")
            val aa  = "Ver mas <a href='${link}'>${link}</a>."
            binding.TextTitle.text = titulo
            binding.TextDescrip.text = descripcion
            Picasso.get().load(img).into(binding.imageView2)
            binding.TextLink.text = Html.fromHtml(aa)
            binding.TextLink.movementMethod = LinkMovementMethod.getInstance()


        }

    }
}