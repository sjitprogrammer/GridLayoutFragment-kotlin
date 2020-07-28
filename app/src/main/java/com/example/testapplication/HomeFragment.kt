package com.example.testapplication


import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.transition.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import android.widget.FrameLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testapplication.internet.database.Pokemon
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.items_row.view.*
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : Fragment(),OnItemClickListener,HomeListener {

    val listItem:ArrayList<Pokemon> = ArrayList()
    val tempItem:ArrayList<Pokemon> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.homeListener = this
        if(tempItem.size==0) {
            viewModel.fetchAllPokemon()
        }
        showData(tempItem)
        refreshLayout.setOnRefreshListener {
            listItem.clear()
            tempItem.clear()
            viewModel.fetchAllPokemon()
        }
        viewModel._pokemonList.observe(viewLifecycleOwner, androidx.lifecycle.Observer {

            if(it.size>=151){
                viewModel.LoadPokemonFinish()
                listItem.clear()
                tempItem.clear()
                listItem.addAll(it)
                tempItem.addAll(it)
                recyclerview.adapter?.notifyDataSetChanged()
            }
        })
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText : String?): Boolean {

                if(newText!!.isNotEmpty()){
                    tempItem.clear()
                    val search = newText.toLowerCase(Locale.getDefault())

                    listItem.forEach{
                        if(it.name?.toLowerCase(Locale.getDefault())?.contains(search)!!){
                            tempItem.add(it)
                        }
                    }
                    recyclerview.adapter?.notifyDataSetChanged()
                }else{
                    tempItem.clear()
                    tempItem.addAll(listItem)
                    recyclerview.adapter?.notifyDataSetChanged()
                }
                return true
            }

        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    private fun fetchData(){


//        listItem.add(Item("Suicune","https://assets.pokemon.com/assets/cms2/img/pokedex/detail/245.png",245))
//        listItem.add(Item("Entei","https://assets.pokemon.com/assets/cms2/img/pokedex/detail/244.png",244))
//        listItem.add(Item("Raikou","https://assets.pokemon.com/assets/cms2/img/pokedex/detail/243.png",243))
//        listItem.add(Item("Charizard","https://assets.pokemon.com/assets/cms2/img/pokedex/detail/006.png",6))
//        listItem.add(Item("Dragonite","https://assets.pokemon.com/assets/cms2/img/pokedex/detail/149.png",149))
//        listItem.add(Item("Milotic","https://assets.pokemon.com/assets/cms2/img/pokedex/detail/350.png",350))
//        listItem.add(Item("Dialga","https://assets.pokemon.com/assets/cms2/img/pokedex/detail/483.png",483))
//        listItem.add(Item("Darkrai","https://assets.pokemon.com/assets/cms2/img/pokedex/detail/491.png",491))
//        listItem.add(Item("Arcanine","https://assets.pokemon.com/assets/cms2/img/pokedex/detail/059.png",59))
//        listItem.add(Item("Lugia","https://assets.pokemon.com/assets/cms2/img/pokedex/detail/249.png",249))
//        listItem.add(Item("Ho-Oh","https://assets.pokemon.com/assets/cms2/img/pokedex/detail/250.png",250))
//        listItem.add(Item("Noivern","https://assets.pokemon.com/assets/cms2/img/pokedex/detail/715.png",715))
//        listItem.add(Item("Salamence","https://assets.pokemon.com/assets/cms2/img/pokedex/detail/373.png",373))
//        listItem.add(Item("Blaziken","https://assets.pokemon.com/assets/cms2/img/pokedex/detail/257.png",257))
//        listItem.add(Item("Bulbasaur","https://assets.pokemon.com/assets/cms2/img/pokedex/detail/001.png",1))
//
//        showData(listItem)
//        tempItem.addAll(listItem)
    }

    private fun showData(item:List<Pokemon>){
        val gridLayoutManager = GridLayoutManager(requireContext(),2)
        recyclerview.layoutManager = gridLayoutManager
        recyclerview.adapter = ItemsAdapter(item,requireContext(),this)
        recyclerview.apply {
            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }
        recyclerview.addOnScrollListener(object : HidingScrollListener() {
            override fun onHide() {
                hideViews()
            }

            override fun onShow() {
                showViews()
            }
        })
    }

    private fun hideViews() {

        val removeheight = imageView2.height+50
        imageView2.animate().translationY((-removeheight).toFloat())
            .setDuration(500)
            .setInterpolator(AccelerateInterpolator(2F))
            .setListener(object: Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {
                }

                override fun onAnimationEnd(animation: Animator?) {
                    imageView2.visibility = View.GONE
                }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationStart(animation: Animator?) {
                }

            }).start()
//        searchView.animate().translationY((-removeheight).toFloat())
//            .setDuration(500)
//            .setInterpolator(AccelerateInterpolator(2F)).start()
//        recyclerview.animate().translationY((-removeheight).toFloat())
//            .setInterpolator(AccelerateInterpolator(2F))

        //        imageView2.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.slide_up)


    }

    private fun showViews() {
//        imageView2.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.slide_down)
        imageView2.animate().translationY(0F)
            .setDuration(500)
            .setInterpolator(DecelerateInterpolator(2F))
            .setListener(object: Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                imageView2.visibility = View.VISIBLE
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
//                imageView2.visibility = View.INVISIBLE
                imageView2.visibility = View.VISIBLE
            }

        }).start()
        searchView.animate().translationY(0F).setDuration(500).setInterpolator(DecelerateInterpolator(2F)).start()
//        imageView2.visibility = View.VISIBLE
    }

    override fun onClickedItem(view: View,id:Int,position: Int) {
        var item_args = tempItem[position]
        val number = tempItem[position].id
        val bundle = bundleOf("item_args" to item_args)
//        Log.e("HomeFragment","image_$number")
        val extras = FragmentNavigatorExtras(
            view.imageView to "image_$number"
        )
        findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle,null,extras)
    }

    override fun fetchAllPokemon() {
        refreshLayout.isRefreshing = true
//        progressBar.show()
    }

    override fun onSuccess() {
//        progressBar.hide()
        refreshLayout.isRefreshing = false
    }

    override fun onFailure(message: String) {
//        progressBar.hide()
        refreshLayout.isRefreshing = false
        Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show()
    }


}
