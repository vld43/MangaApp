package ru.vld43.mangaapp.ui.description

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import com.squareup.picasso.Picasso
import ru.vld43.mangaapp.App
import ru.vld43.mangaapp.data.ApiConstants
import ru.vld43.mangaapp.data.MangaStore
import ru.vld43.mangaapp.databinding.FragmentDescriptionBinding
import ru.vld43.mangaapp.domain.DataManga
import javax.inject.Inject

class DescriptionFragment : Fragment() {

    @Inject
    lateinit var mangaStore: MangaStore

    init {
        App.appComponent.inject(this)
    }

    private lateinit var viewModel: DescriptionViewModel
    private lateinit var binding: FragmentDescriptionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDescriptionBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[DescriptionViewModel::class.java]


        Log.i("qqq", "onViewCreated: ${mangaStore.getManga()}")
        initViews()
    }

    private fun initViews() {
        val dataManga: DataManga = mangaStore.getManga()
        Picasso.get()
            .load(
                ApiConstants.COVER_URL + "/" +
                        dataManga.manga.id + "/" +
                        dataManga.imageName
            )
            .into(binding.coverArtIv)
        binding.mangaTitleTv.text = dataManga.manga.title
        binding.mangaDescriptionTv.text = dataManga.manga.description
    }
}