package ru.vld43.mangaapp.ui.main

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.squareup.picasso.Picasso
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder
import ru.vld43.mangaapp.R
import ru.vld43.mangaapp.data.ApiConstants.COVER_SIZE
import ru.vld43.mangaapp.data.ApiConstants.COVER_URL
import ru.vld43.mangaapp.domain.Cover
import ru.vld43.mangaapp.domain.DataManga
import ru.vld43.mangaapp.domain.Manga

class MangaController(
    private val onClickListener: (DataManga) -> Unit
) : BindableItemController<DataManga, MangaController.Holder>() {

    inner class Holder(parent: ViewGroup) :
        BindableViewHolder<DataManga>(parent, R.layout.item_manga) {

        private val card: CardView = itemView.findViewById(R.id.manga_cv)
        private val coverArt: ImageView = itemView.findViewById(R.id.manga_cover_art_iv)
        private val title: TextView = itemView.findViewById(R.id.manga_title_tv)
        private val description: TextView = itemView.findViewById(R.id.manga_description_tv)

        lateinit var dataManga: DataManga

        init {
            card.setOnClickListener {
                onClickListener(dataManga)
            }
        }

        override fun bind(dataManga: DataManga) {
            this.dataManga = dataManga
            if (dataManga.manga.coverId != null) {
                Picasso.get()
                    .load(COVER_URL + "/" + dataManga.manga.id + "/" + dataManga.imageName)
                    .into(coverArt)
            }

            title.text = dataManga.manga.title
            description.text = dataManga.manga.description
        }
    }

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)

    override fun getItemId(dataManga: DataManga) = dataManga.manga.id


}