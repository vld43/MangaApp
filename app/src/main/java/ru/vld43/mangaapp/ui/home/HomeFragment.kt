package ru.vld43.mangaapp.ui.home

import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.os.Bundle
import android.util.Log
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
import ru.vld43.mangaapp.ui.manga_details.MangaDetailsActivity
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class HomeFragment : Fragment() {

    private companion object {
        const val SPAN_COUNT_ORIENTATION_PORTRAIT = 3
        const val SPAN_COUNT_ORIENTATION_LANDSCAPE = 5
    }

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    private val adapter = EasyAdapter()
    private val controller = MangaController {
        openMangaDetailsScreen()
    }

    private val disposables = CompositeDisposable()

    override fun onStart() {
        super.onStart()
        viewModel.onStart()

        disposables.addAll(
            observeMangaList()
        )
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
        disposables.dispose()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        activity?.resources?.configuration?.let { initRecycler(it.orientation) }
        initSearchView()


//        initialization()
    }

    private fun initialization() {
        //sharedPreferences = activity?.getSharedPreferences("test", Context.MODE_PRIVATE)!!
        val string = "test"
        val edit = sharedPreferences.edit()
        edit.putString("save_key", string)
        edit.apply()
        Log.i("qqq", "${sharedPreferences.getString("save_key", "nnnn")}")
    }

    private fun initRecycler(orientation: Int) {
        var spanCount = SPAN_COUNT_ORIENTATION_PORTRAIT
        binding.mangaRv.adapter = adapter

        when (orientation) {
            ORIENTATION_PORTRAIT -> {
                spanCount = SPAN_COUNT_ORIENTATION_PORTRAIT
            }
            ORIENTATION_LANDSCAPE -> {
                spanCount = SPAN_COUNT_ORIENTATION_LANDSCAPE
            }
        }

        binding.mangaRv.layoutManager = GridLayoutManager(activity, spanCount)
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
            .subscribe { viewModel.searchMangaAction.accept(it) })
    }



    private fun observeMangaList() =
        viewModel.mangaListState.subscribe {
            adapter.setData(it, controller)
        }


    private fun openMangaDetailsScreen() {
        val intent = Intent(activity, MangaDetailsActivity::class.java)
        startActivity(intent)
    }
}