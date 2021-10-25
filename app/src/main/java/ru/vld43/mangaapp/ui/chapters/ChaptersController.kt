package ru.vld43.mangaapp.ui.chapters

import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder
import ru.vld43.mangaapp.R
import ru.vld43.mangaapp.domain.Chapter
import ru.vld43.mangaapp.ui.home.MangaController

class ChaptersController(
    val onChapterClick: (Chapter) -> Unit
) : BindableItemController<Chapter, ChaptersController.Holder>() {

    private companion object {
        const val MAX_TEXT_SIZE = 25
        const val START_TITLE_TRANSFORM_INDEX = 0
        const val ELLIPSIS = "..."
        const val START_CHAPTER_TITLE = "Chapter"
    }

    inner class Holder(parent: ViewGroup) :
        BindableViewHolder<Chapter>(parent, R.layout.item_chapter) {

        private val card: CardView = itemView.findViewById(R.id.chapter_cv)
        private val title: TextView = itemView.findViewById(R.id.chapter_tittle_tv)

        lateinit var chapter: Chapter

        init {
            card.setOnClickListener {
                onChapterClick(chapter)
            }
        }

        override fun bind(chapter: Chapter) {
            this.chapter = chapter
            title.text = chapter.title

            if (chapter.title.length > MAX_TEXT_SIZE) {
                val viewTitle =
                    chapter.title.substring(
                        START_TITLE_TRANSFORM_INDEX,
                        MAX_TEXT_SIZE
                    ) + ELLIPSIS
                title.text = viewTitle
            } else {
                title.text = chapter.title
            }

        }
    }

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)

    override fun getItemId(chapter: Chapter) = chapter.id
}