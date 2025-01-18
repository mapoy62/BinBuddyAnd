package com.oym.binbuddy.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.oym.binbuddy.application.BinBuddyApp
import com.oym.binbuddy.data.remote.model.InstagramProfile
import com.oym.binbuddy.data.remote.model.RecyclingCategory
import com.oym.binbuddy.data.remote.model.Tweet
import com.oym.binbuddy.data.remote.model.TwitterResponse
import com.oym.binbuddy.databinding.FragmentExploreBinding
import com.oym.binbuddy.ui.adapters.InstagramProfilesAdapter
import com.oym.binbuddy.ui.adapters.RecyclingCategoriesAdapter
import com.oym.binbuddy.ui.adapters.TweetsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExploreFragment : Fragment() {

    private lateinit var binding: FragmentExploreBinding

    private lateinit var instagramAdapter: InstagramProfilesAdapter
    private val instagramProfiles = mutableListOf<InstagramProfile>()

    private lateinit var tweetsAdapter: TweetsAdapter
    private val tweets = mutableListOf<Tweet>()

    private lateinit var categoriesAdapter: RecyclingCategoriesAdapter
    private val categories = mutableListOf<RecyclingCategory>()

    private val repository by lazy {
        (requireActivity().application as BinBuddyApp).exploreRepository
    }

    private val twitterRepository by lazy {
        (requireActivity().application as BinBuddyApp).twitterRepository
    }

    private val recyclingRepositoryPoints by lazy {
        (requireActivity().application as BinBuddyApp).recyclingRepository
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupInstagramProfilesSection()
        loadInstagramProfiles()

        setupTweetsSection()
        loadTweets()

        setupRecyclingPointsSection()
        loadRecyclingCategories()
    }

    private fun setupInstagramProfilesSection() {
        instagramAdapter = InstagramProfilesAdapter(instagramProfiles)
        binding.rvInstagramProfiles.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvInstagramProfiles.adapter = instagramAdapter
    }

    private fun loadInstagramProfiles() {
        repository.getInstagramProfiles().enqueue(object : Callback<List<InstagramProfile>> {
            override fun onResponse(
                call: Call<List<InstagramProfile>>,
                response: Response<List<InstagramProfile>>
            ) {
                if (response.isSuccessful) {
                    instagramProfiles.clear()
                    instagramProfiles.addAll(response.body() ?: emptyList())
                    instagramAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(requireContext(), "Error al cargar perfiles", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<InstagramProfile>>, t: Throwable) {
                Toast.makeText(requireContext(), "Error al conectar con el servidor", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupTweetsSection() {
        tweetsAdapter = TweetsAdapter(tweets)
        binding.rvTweets.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTweets.adapter = tweetsAdapter
    }

    private fun loadTweets() {
        twitterRepository.fetchTweets("environment").enqueue(object : Callback<TwitterResponse> {
            override fun onResponse(call: Call<TwitterResponse>, response: Response<TwitterResponse>) {
                if (response.isSuccessful) {
                    val newTweets = response.body()?.tweets ?: emptyList()
                    tweets.clear()
                    tweets.addAll(newTweets)
                    tweetsAdapter.notifyDataSetChanged()

                    // Guardar en caché
                    twitterRepository.saveTweetsToCache(newTweets)
                } else {
                    Toast.makeText(requireContext(), "Error al cargar tweets", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<TwitterResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Error al conectar con Twitter", Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun setupRecyclingPointsSection() {
        categoriesAdapter = RecyclingCategoriesAdapter(categories) { category ->
            showRecyclingPointsMap(category)
        }
        binding.rvRecyclingCategories.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRecyclingCategories.adapter = categoriesAdapter
    }

    private fun showRecyclingPointsMap(category: RecyclingCategory) {
        val dialog = RecyclingPointsMapDialog(category.recyclingPoints)
        dialog.show(parentFragmentManager, "RecyclingPointsMapDialog")
    }

    private fun loadRecyclingCategories() {
        recyclingRepositoryPoints.getRecyclingCategories().enqueue(object : Callback<List<RecyclingCategory>> {
            override fun onResponse(
                call: Call<List<RecyclingCategory>>,
                response: Response<List<RecyclingCategory>>
            ) {
                if (response.isSuccessful) {
                    categories.clear()
                    categories.addAll(response.body() ?: emptyList())
                    categoriesAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(requireContext(), "Error al cargar categorías", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<RecyclingCategory>>, t: Throwable) {
                Toast.makeText(requireContext(), "Error de conexión: ${t.localizedMessage}", Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }
        })
    }

}