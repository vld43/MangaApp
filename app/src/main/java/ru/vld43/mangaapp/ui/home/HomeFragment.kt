package ru.vld43.mangaapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.vld43.mangaapp.databinding.FragmentHomeBinding
import java.util.concurrent.TimeUnit

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    private val adapter = EasyAdapter()
    private val controller = MangaController {
        Toast.makeText(activity, it.manga.title, Toast.LENGTH_SHORT).show()
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

        initRecycler()
        initSearchView()
        observeViewModel()
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }

    private fun initRecycler() {
        binding.mangaRv.adapter = adapter
        binding.mangaRv.layoutManager = GridLayoutManager(activity, 3)

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
}