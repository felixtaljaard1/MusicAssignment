package com.example.musicmvvm.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicmvvm.R
import com.example.musicmvvm.databinding.FragmentHomeBinding
import com.example.musicmvvm.utils.Resource
import com.example.musicmvvm.viewmodel.rocklist.RockAdapter
import com.example.musicmvvm.viewmodel.rocklist.RockListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val viewModel: RockListViewModel by viewModels()
    private lateinit var adapter: RockAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(RockListViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        //return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        startObservers()
        adapter = RockAdapter(RockAdapter.OnClickListener{
            Toast.makeText(activity, "${it.artistId}", Toast.LENGTH_LONG).show()
            val bundle = Bundle()
            bundle.putInt("ID", it.artistId)

            findNavController().navigate(androidx.appcompat.R.id.action_bar, bundle)
        })

        _binding?.recyclerViewRock?.adapter = adapter
        _binding?.recyclerViewRock?.layoutManager = LinearLayoutManager(activity)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun startObservers(){
        viewModel.repository.observe(viewLifecycleOwner){
            when(it.status){
                Resource.Status.SUCCESS ->{
                    Log.i("Data", ""+ (it.data))
                    adapter.submitList(it.data)
                }
                Resource.Status.ERROR ->{
                    Log.i("Error", it.message.toString())
                }
                Resource.Status.LOADING ->{

                }
            }
        }
    }
}