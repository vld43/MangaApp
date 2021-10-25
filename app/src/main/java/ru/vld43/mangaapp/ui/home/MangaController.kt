package ru.vld43.mangaapp.ui.home

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.jakewharton.rxrelay2.PublishRelay
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder
import ru.vld43.mangaapp.R
import ru.vld43.mangaapp.data.ApiConstants.COVER_SIZE
import ru.vld43.mangaapp.data.ApiConstants.COVER_URL
import ru.vld43.mangaapp.domain.DataManga

class MangaController(
    val onMangaClick: (DataManga) -> Unit
) : BindableItemController<DataManga, MangaController.Holder>() {

    private companion object {
        const val MAX_TEXT_SIZE = 20
        const val START_INDEX = 0
        const val ELLIPSIS = "..."
    }

    inner class Holder(parent: ViewGroup) :
        BindableViewHolder<DataManga>(parent, R.layout.item_manga) {

        private val card: CardView = itemView.findViewById(R.id.manga_cv)
        private val coverArt: ImageView = itemView.findViewById(R.id.manga_cover_art_iv)
        private val title: TextView = itemView.findViewById(R.id.manga_title_tv)

        lateinit var dataManga: DataManga

        init {
            card.setOnClickListener {
                onMangaClick(dataManga)
            }
        }

        override fun bind(dataManga: DataManga) {
            this.dataManga = dataManga
            if (dataManga.manga.coverId != null) {
                Picasso.get()
                    .load("$COVER_URL/${dataManga.manga.id}/${dataManga.imageName}$COVER_SIZE")
                    .into(coverArt)
            }

            if (dataManga.manga.title != null) {

                if (dataManga.manga.title.length > MAX_TEXT_SIZE) {
                    val viewTitle =
                        dataManga.manga.title.substring(START_INDEX, MAX_TEXT_SIZE) + ELLIPSIS
                    title.text = viewTitle
                } else {
                    title.text = dataManga.manga.title
                }
            } else title.text = ""
        }
    }

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)

    override fun getItemId(dataManga: DataManga) = dataManga.manga.id


}