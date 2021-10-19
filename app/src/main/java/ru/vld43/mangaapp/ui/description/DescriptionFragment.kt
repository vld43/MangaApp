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
import io.reactivex.disposables.CompositeDisposable
import ru.vld43.mangaapp.App
import ru.vld43.mangaapp.data.ApiConstants
import ru.vld43.mangaapp.data.MangaStore
import ru.vld43.mangaapp.databinding.FragmentDescriptionBinding
import ru.vld43.mangaapp.domain.DataManga
import javax.inject.Inject

class DescriptionFragment : Fragment() {

    private lateinit var viewModel: DescriptionViewModel
    private lateinit var binding: FragmentDescriptionBinding

    private val disposables = CompositeDisposable()

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
        disposables.addAll(
            initViews()
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

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
    }

    private fun initViews() =
        viewModel.mangaState.subscribe { dataManga ->
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