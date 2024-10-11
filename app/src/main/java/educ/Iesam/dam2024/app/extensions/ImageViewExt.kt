package educ.Iesam.dam2024.app.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

//metodo de extensión
fun ImageView.loadUrl(url: String) {
    //Aqui es donde usaremos las librerias de terceros, en este caso Glide
    Glide.with(this).load(url).into(this)
    //this hace referencia a la vista en si, osea el ImageView
}