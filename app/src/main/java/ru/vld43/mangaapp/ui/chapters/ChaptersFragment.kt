package ru.vld43.mangaapp.ui.chapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.disposables.CompositeDisposable
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.vld43.mangaapp.R
import ru.vld43.mangaapp.databinding.FragmentChaptersBinding

class ChaptersFragment : Fragment() {

    private lateinit var viewModel: ChaptersViewModel
    private lateinit var binding: FragmentChaptersBinding

    private val adapter = EasyAdapter()
    private val controller by lazy {
        ChaptersController {
            Toast.makeText(activity, it.title, Toast.LENGTH_SHORT).show()
        }
    }

    private val disposables = CompositeDisposable()

    override fun onStart() {
        super.onStart()
        viewModel.onStart()

        disposables.addAll(
            observeChapters()
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
        binding = FragmentChaptersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ChaptersViewModel::class.java]

        binding.chapterRv.adapter = adapter
        binding.chapterRv.layoutManager = LinearLayoutManager(activity)
    }

    private fun observeChapters() =
        viewModel.chaptersState.subscribe {
            adapter.setData(it, controller)
        }
}