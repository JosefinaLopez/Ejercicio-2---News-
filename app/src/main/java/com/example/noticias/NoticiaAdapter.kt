import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.noticias.DetalleNoticia
import com.example.noticias.Notice
import com.example.noticias.R
import com.squareup.picasso.Picasso
import java.time.LocalDate

class NoticiaAdapter(private val noticias: List<Notice>, private val contexto: Context) : RecyclerView.Adapter<NoticiaAdapter.NoticiaViewHolder>() {

    inner class NoticiaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val TextTitular: TextView = itemView.findViewById(R.id.TextTitulo)
        //private val TextDescrip: TextView = itemView.findViewById(R.id.TextDescription)
        private val TextDate: TextView = itemView.findViewById(R.id.TextDate)
        private val Img: ImageView = itemView.findViewById(R.id.imageView)
        private val Card: CardView = itemView.findViewById(R.id.cardview)
        fun bind(noti: Notice) {
                TextTitular.text = noti.title.substring(0,45)
                TextDate.text = noti.published_at.substring(0,10)
                Picasso.get().load(noti.image_url).into(Img)
                Card.setOnClickListener{
                    val intent = Intent(contexto,DetalleNoticia::class.java)
                    intent.putExtra("Imagen",noti.image_url)
                    intent.putExtra("Titulo",noti.title)
                    intent.putExtra("Descripcion",noti.description)
                    intent.putExtra("Link",noti.url)
                    contexto.startActivity(intent)
                }

            }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticiaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_noticia,parent,false)
        return NoticiaViewHolder(view)
    }

    override fun getItemCount(): Int {
       return noticias.size
    }

    override fun onBindViewHolder(holder: NoticiaViewHolder, position: Int) {
        val nt = noticias[position]
        holder.bind(nt)
    }
}


