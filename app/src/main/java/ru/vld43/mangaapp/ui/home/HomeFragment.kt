package ru.vld43.mangaapp.ui.home

import android.content.Intent
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.vld43.mangaapp.databinding.FragmentHomeBinding
import ru.vld43.mangaapp.domain.DataManga
import ru.vld43.mangaapp.ui.manga_details.MangaDetailsActivity
import java.util.concurrent.TimeUnit

class HomeFragment : Fragment() {

    private companion object {
        const val MANGA_EXTRA = "manga"
    }

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    private val adapter = EasyAdapter()
    private val controller = MangaController {
        openMangaDetailsScreen(it)
    }

    private val disposables = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.resources?.configuration?.let { initRecycler(it.orientation) }
        initSearchView()
        observeViewModel()
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }

    private fun initRecycler(orientation: Int) {
        binding.mangaRv.adapter = adapter

        when (orientation) {
            ORIENTATION_PORTRAIT ->
                binding.mangaRv.layoutManager = GridLayoutManager(activity, 3)
            ORIENTATION_LANDSCAPE ->
                binding.mangaRv.layoutManager = GridLayoutManager(activity, 5)
        }
    }

    private fun initSearchView() {
        disposables.add(Observable.create<String> {
            binding.mangaSv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(text: String) = false

                override fun onQueryTextChange(text: String): Boolean {
                    it.onNext(text)
                    return false
                }
            })
        }
            .debounce(300, TimeUnit.MILLISECONDS)
            .subscribe { viewModel.searchManga(it) })
    }

    private fun observeViewModel() {
        viewModel.liveData.observe(viewLifecycleOwner) {
            adapter.setData(it, controller)
        }
    }

    private fun openMangaDetailsScreen(dataManga: DataManga) {
        val intent = Intent(activity, MangaDetailsActivity::class.java)
        intent.putExtra(MANGA_EXTRA, dataManga)
        startActivity(intent)
    }
}