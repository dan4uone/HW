package com.example.hw.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.R
import androidx.navigation.fragment.findNavController
import com.example.hw.News
import com.example.hw.NewsAdapter
import com.example.hw.databinding.FragmentHomeBinding
class HomeFragment : Fragment() {
    private var adapter = NewsAdapter()
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fap.setOnClickListener {
            findNavController().navigate(R.id.secondFragment)
        }
        parentFragmentManager.setFragmentResultListener(
            "rk_news",
            viewLifecycleOwner
        ) { _, bundle ->
            val news = bundle.getSerializable("news") as News
            Log.e("Home", "text - $news")
            adapter.addItem(news)
            binding.recycleView.adapter = adapter
        }
        binding.recycleView.adapter = adapter

        rename()
        alert()

    }

    private fun alert() {

        adapter.onLongClick = {

            val dialog = AlertDialog.Builder(context)
            dialog.setTitle("Delet project list")
            dialog.setMessage("You want to delete project?")
            dialog.setPositiveButton("Yes") { _, _ ->

                adapter.deleteItemsAndNotifyAdapter(it)
                binding.recycleView.adapter = adapter
                //Delete items in RecyclerView**

            }
            dialog.setNegativeButton("Cancel") {dialog, _ -> dialog.cancel()}
            dialog.show()
        }
    }


    private fun rename() {
        adapter.onClick = {
            val bundle = Bundle()
            bundle.putString("key1", it.title)
            findNavController().navigate(R.id.secondFragment, bundle)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
