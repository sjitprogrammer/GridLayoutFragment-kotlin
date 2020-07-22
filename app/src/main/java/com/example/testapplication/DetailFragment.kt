package com.example.testapplication


import android.os.Bundle
import android.os.Parcelable
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val transition = TransitionInflater.from(activity).inflateTransition(R.transition.change_bounds)
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
        var item:Item = arguments?.getParcelable("item_args")!!
        imageView_icon.transitionName = "image_${item.number}"
        Glide.with(this)
            .load(item.imageUrl)
            .placeholder(R.drawable.placeholder_image)
            .into(imageView_icon);
        textView_number.text = "Number : "+item.number.toString()
        textView_name.text = "Name : "+item.title

        imageView_back.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
        }
    }
}
