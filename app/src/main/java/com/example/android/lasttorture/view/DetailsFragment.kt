package com.example.android.lasttorture.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

import com.example.android.lasttorture.R
import com.example.android.lasttorture.databinding.DetailsFragmentBinding
import com.example.android.lasttorture.viewmodel.DetailsViewModel

class DetailsFragment : Fragment() {

    private lateinit var binding: DetailsFragmentBinding

    companion object {
        fun newInstance() = DetailsFragment()
    }

    private lateinit var viewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.details_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(activity as MainActivity).get(DetailsViewModel::class.java)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeUi()
    }

    private fun subscribeUi() {

        val detailsFragmentArgs by navArgs<DetailsFragmentArgs>()

        viewModel.getImage(detailsFragmentArgs)
        viewModel.image.observe(this.viewLifecycleOwner, Observer { url ->
            Glide.with(context!!).load(url).into(binding.image)
        })

        viewModel.getBreed(detailsFragmentArgs)
        viewModel.breed.observe(this.viewLifecycleOwner, Observer { breed ->
            binding.breed.text = "Breed: " + breed
        })

        viewModel.getName(detailsFragmentArgs)
        viewModel.name.observe(this.viewLifecycleOwner, Observer { name ->
            binding.name.text = "Name: " + name
        })

        viewModel.getAge(detailsFragmentArgs)
        viewModel.age.observe(this.viewLifecycleOwner, Observer { age ->
            binding.age.text = "Age: " + age
        })

        viewModel.getDesc(detailsFragmentArgs)
        viewModel.desc.observe(this.viewLifecycleOwner, Observer { desc ->
            binding.desc.text = "Description: " + desc
        })

        binding.backButton.setOnClickListener {
            activity?.onBackPressed()
        }
    }

}