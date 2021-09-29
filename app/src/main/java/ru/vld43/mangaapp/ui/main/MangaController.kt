package ru.vld43.mangaapp.ui.main

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder
import ru.vld43.mangaapp.R
import ru.vld43.mangaapp.domain.Manga

class MangaController(
    private val onClickListener: (Manga) -> Unit
) : BindableItemController<Manga, MangaController.Holder>() {

    inner class Holder(parent: ViewGroup) : BindableViewHolder<Manga>(parent, R.layout.item_manga) {

        private val card: CardView = itemView.findViewById(R.id.manga_cv)
        private val coverArt: ImageView = itemView.findViewById(R.id.manga_cover_art_iv)
        private val title: TextView = itemView.findViewById(R.id.manga_title_tv)
        private val description: TextView = itemView.findViewById(R.id.manga_description_tv)

        lateinit var manga: Manga

        init {
            card.setOnClickListener {
                onClickListener(manga)
            }
        }

        override fun bind(manga: Manga) {
            this.manga = manga
            if (manga.coverArtId != null) {
                TODO()
            }

            title.text = manga.title
            description.text = manga.description
        }
    }

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)

    override fun getItemId(manga: Manga) = manga.id


}