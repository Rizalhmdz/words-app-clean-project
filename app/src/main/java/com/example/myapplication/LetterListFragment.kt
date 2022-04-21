package com.example.myapplication

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentLetterListBinding


class LetterListFragment : Fragment() {
    private var _binding : FragmentLetterListBinding? = null
    private val binding get() = _binding!!

    private lateinit var rv : RecyclerView
    private var isLinearLayout = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLetterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv = binding.rv
        chooseLayout()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun chooseLayout(){
        rv.adapter = LetterAdapter()
        if(isLinearLayout){
            rv.layoutManager = LinearLayoutManager(context)
        }
        else {
            rv.layoutManager = GridLayoutManager(context, 4)
        }
        rv.hasFixedSize()
    }

    private fun setIcon(item: MenuItem){
        if(isLinearLayout){
            item.icon = requireContext().getDrawable(R.drawable.ic_linear_list)
        } else {
            item.icon = requireContext().getDrawable(R.drawable.ic_grid_list)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
        val button = menu.findItem(R.id.switch_view)
        setIcon(button)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.switch_view -> {
                isLinearLayout = !isLinearLayout
                chooseLayout()
                setIcon(item)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}