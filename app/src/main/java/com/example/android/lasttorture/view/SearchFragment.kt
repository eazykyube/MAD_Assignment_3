package com.example.android.lasttorture.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.android.lasttorture.R
import com.example.android.lasttorture.databinding.SearchFragmentBinding
import com.example.android.lasttorture.view.adapter.AnimalItemAdapter
import com.example.android.lasttorture.view.adapter.VerticalItemDecorator
import com.example.android.lasttorture.viewmodel.SearchViewModel
import com.example.android.lasttorture.data.db.AnimalDbModel
import javax.inject.Inject
import kotlin.collections.ArrayList

class SearchFragment : Fragment() {

    private lateinit var binding: SearchFragmentBinding

    companion object {
        fun newInstance() = SearchFragment()
    }

    @Inject
    lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.search_fragment,
            container,
            false
        )

        val adapter = AnimalItemAdapter()

        binding.recyclerView.addItemDecoration(
            VerticalItemDecorator(
                5,
                true
            )
        )

        viewModel = (activity as MainActivity).viewModel

        binding.recyclerView.layoutManager = LinearLayoutManager(context);

        binding.recyclerView.adapter = adapter

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeUi()
    }

    private fun subscribeUi() {
//        val animalKindSpinnerTypesAdapter = createAdapter()
//        binding.animalKindSpinner.adapter = animalKindSpinnerTypesAdapter
//
//        val breedSpinnerTypesAdapter = createAdapter()
//        binding.breedSpinner.adapter = breedSpinnerTypesAdapter


        viewModel.allAnimals.observe(this.viewLifecycleOwner, Observer { list ->
            setAnimals(list)
        })

        viewModel.allTypes.observe(this.viewLifecycleOwner, Observer { types ->
            val animalKindSpinnerTypesAdapter = createAdapter(types)
            binding.animalKindSpinner.adapter = animalKindSpinnerTypesAdapter
           //Add binding logic
        })

       viewModel.breeds.observe(this.viewLifecycleOwner, Observer { breeds ->
           val breedSpinnerTypesAdapter = createAdapter(breeds)
           binding.breedSpinner.adapter = breedSpinnerTypesAdapter
           //Add binding logic
       })


        viewModel.networkActive.observe(this.viewLifecycleOwner, Observer { isActive ->
            if (!isActive) {
                Toast.makeText(
                    activity!!,
                    "There is no internet.", Toast.LENGTH_LONG
                ).show()

            }
        })

        binding.animalKindSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //Implement logic
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                   //Implement logic
                    if (parent?.selectedItem.toString() == "Any") {
                        viewModel.findAnimals(null, null)
                    }
                    else{
                        viewModel.findAnimals(parent?.selectedItem.toString(), null)
                    }
                    viewModel.selectType(parent?.selectedItem.toString())
                }
            }

        binding.breedSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //Implement logic
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (parent?.selectedItem.toString() != "Any") {
                    viewModel.findAnimals(binding.animalKindSpinner.selectedItem.toString(), parent?.selectedItem.toString())
                }
            }

        }
    }

    fun createAdapter(array: List<String>): ArrayAdapter<String> {
        val adapter = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_spinner_item,
                array
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        return adapter
    }

    fun setAnimals(animalDbModels: List<AnimalDbModel>) {
        (binding.recyclerView.adapter as AnimalItemAdapter).setAnimalList(animalDbModels)
    }

}
