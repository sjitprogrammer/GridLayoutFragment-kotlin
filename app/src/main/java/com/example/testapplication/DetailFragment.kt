package com.example.testapplication


import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.testapplication.internet.database.Pokemon
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_home.*


class DetailFragment : Fragment() {
    val listItems: ArrayList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val transition =
            TransitionInflater.from(activity).inflateTransition(R.transition.change_bounds)
        sharedElementEnterTransition = transition
        sharedElementReturnTransition = transition
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var item: Pokemon = arguments?.getParcelable("item_args")!!
        val pokemonType = item.types;
        val abilities = item.abilities
        imageView_icon.transitionName = "image_${item.id}"
        val imageUrl: String = "https://pokeres.bastionbot.org/images/pokemon/${item.id}.png"
        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.placeholder_image)
            .into(imageView_icon);
        textView_number.text = "Number : " + item.id.toString()
        textView_name.text = item.name
        text_height.text = item.height.toString()+"\""
        text_weight.text = item.weight.toString()+" lbs"
        text_category.text = "-"
        abilities.forEach {
            val row = it.ability.name
            text_abilities.append("${row} ")
        }
        pokemonType.forEach {
            val slot = it.type;
            listItems.add(slot.name)
            Log.e("Detailfragment",""+listItems.size)
            recyclerview_type.adapter?.notifyDataSetChanged()
        }
        Log.e("Detailfragment",""+pokemonType.size)
        setType()
        imageView_back.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
            postponeEnterTransition()
        }
    }

    private fun setType() {
        val linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL ,false)
        recyclerview_type.layoutManager = linearLayoutManager
        val adapter = TypeAdapter(requireContext(), listItems)
        recyclerview_type.adapter = adapter
    }
}
