package ru.vld43.mangaapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.vld43.mangaapp.databinding.FragmentMainBinding
import java.util.concurrent.TimeUnit

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding

    private val adapter = EasyAdapter()
    private val controller = MangaController {
        Toast.makeText(activity, it.manga.title, Toast.LENGTH_SHORT)
    }

    private val disposables = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        observeViewModel()
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }

    private fun initRecycler() {
        binding.mangaRv.adapter = adapter
        binding.mangaRv.layoutManager = LinearLayoutManager(activity)
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
            .filter {it.isNotEmpty()}
            .debounce (300, TimeUnit.MILLISECONDS)
            .subscribe {

            })
    }

    private fun observeViewModel() {
        viewModel.liveData.observe(viewLifecycleOwner) {
            adapter.setData(it, controller)
        }
    }
}