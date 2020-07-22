package com.example.testapplication


import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.items_row.*
import kotlinx.android.synthetic.main.items_row.view.*
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment(),OnItemClickListener {

    val listItem:ArrayList<Item> = ArrayList()
    val tempItem:ArrayList<Item> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listItem.clear()
        tempItem.clear()
        fetchData()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText : String?): Boolean {

                if(newText!!.isNotEmpty()){
                    tempItem.clear()
                    val search = newText.toLowerCase(Locale.getDefault())

                    listItem.forEach{
                        if(it.title?.toLowerCase(Locale.getDefault())?.contains(search)!!){
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


        listItem.add(Item("Suicune","https://assets.pokemon.com/assets/cms2/img/pokedex/detail/245.png",245))
        listItem.add(Item("Entei","https://assets.pokemon.com/assets/cms2/img/pokedex/detail/244.png",244))
        listItem.add(Item("Raikou","https://assets.pokemon.com/assets/cms2/img/pokedex/detail/243.png",243))
        listItem.add(Item("Charizard","https://assets.pokemon.com/assets/cms2/img/pokedex/detail/006.png",6))
        listItem.add(Item("Dragonite","https://assets.pokemon.com/assets/cms2/img/pokedex/detail/149.png",149))
        listItem.add(Item("Milotic","https://assets.pokemon.com/assets/cms2/img/pokedex/detail/350.png",350))
        listItem.add(Item("Dialga","https://assets.pokemon.com/assets/cms2/img/pokedex/detail/483.png",483))
        listItem.add(Item("Darkrai","https://assets.pokemon.com/assets/cms2/img/pokedex/detail/491.png",491))
        listItem.add(Item("Arcanine","https://assets.pokemon.com/assets/cms2/img/pokedex/detail/059.png",59))
        listItem.add(Item("Lugia","https://assets.pokemon.com/assets/cms2/img/pokedex/detail/249.png",249))
        listItem.add(Item("Ho-Oh","https://assets.pokemon.com/assets/cms2/img/pokedex/detail/250.png",250))
        listItem.add(Item("Noivern","https://assets.pokemon.com/assets/cms2/img/pokedex/detail/715.png",715))
        listItem.add(Item("Salamence","https://assets.pokemon.com/assets/cms2/img/pokedex/detail/373.png",373))
        listItem.add(Item("Blaziken","https://assets.pokemon.com/assets/cms2/img/pokedex/detail/257.png",257))
        listItem.add(Item("Bulbasaur","https://assets.pokemon.com/assets/cms2/img/pokedex/detail/001.png",1))

        showData(listItem)
        tempItem.addAll(listItem)
    }

    private fun showData(items :List<Item>){
        val gridLayoutManager = GridLayoutManager(requireContext(),2)
        recyclerview.layoutManager = gridLayoutManager
        recyclerview.adapter = ItemsAdapter(tempItem,requireContext(),this)
        recyclerview.apply {
            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }
    }

    override fun onClickedItem(view: View,position: Int) {
        var item_args = tempItem[position]
        val number = tempItem[position].number
        val bundle = bundleOf("item_args" to item_args)
        val extras = FragmentNavigatorExtras(
            view.imageView to "image_$number"
        )
        findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle,null,extras)
//        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment()
//        findNavController().navigate(action)
    }


}
