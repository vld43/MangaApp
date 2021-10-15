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
import ru.vld43.mangaapp.data.ApiConstants
import ru.vld43.mangaapp.databinding.FragmentDescriptionBinding
import ru.vld43.mangaapp.domain.DataManga

class DescriptionFragment : Fragment() {

    companion object {
        private const val MANGA_DESCRIPTION = "manga_description"

        fun getNewInstance(args: Bundle?): DescriptionFragment {
            val descriptionFragment = DescriptionFragment()
            descriptionFragment.arguments = args
            return descriptionFragment
        }
    }

    private lateinit var viewModel: DescriptionViewModel
    private lateinit var binding: FragmentDescriptionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[DescriptionViewModel::class.java]
        binding = FragmentDescriptionBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        //val dataManga = activity?.intent?.getSerializableExtra(MANGA_DESCRIPTION) as? DataManga
        val dataManga: DataManga? = arguments?.getSerializable(MANGA_DESCRIPTION) as? DataManga
        Log.i("qqq", "$dataManga")
        if (dataManga != null) {
            Picasso.get()
                .load(
                    ApiConstants.COVER_URL + "/" +
                            dataManga.manga.id + "/" +
                            dataManga.imageName +
                            ApiConstants.COVER_SIZE
                )
                .into(binding.descriptionMangaCoverArt)
            Log.i("qqq", "initViews: $dataManga.manga.id \n $dataManga.imageName")
        }
        Log.i("qqq", "initViews: $dataManga.manga.id \n $dataManga.imageName")
    }
}