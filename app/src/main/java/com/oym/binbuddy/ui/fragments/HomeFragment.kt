package com.oym.binbuddy.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oym.binbuddy.AddTipDialogFragment
import com.oym.binbuddy.R
import com.oym.binbuddy.application.BinBuddyApp
import com.oym.binbuddy.data.remote.ComingSoonApi
import com.oym.binbuddy.data.remote.RetrofitHelper
import com.oym.binbuddy.data.remote.model.ComingSoonItem
import com.oym.binbuddy.data.remote.model.RecommendationItem
import com.oym.binbuddy.data.remote.model.SerpApiResponse
import com.oym.binbuddy.data.remote.model.Tip
import com.oym.binbuddy.data.repository.ComingSoonRepository
import com.oym.binbuddy.databinding.FragmentHomeBinding
import com.oym.binbuddy.ui.adapters.ComingSoonAdapter
import com.oym.binbuddy.ui.adapters.RecommendationsAdapter
import com.oym.binbuddy.ui.adapters.TipsAdapter
import com.oym.binbuddy.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: ComingSoonAdapter
    private val repositoryComingSoon by lazy {
        (requireActivity().application as BinBuddyApp).comingSoonRepository
    }

    private lateinit var tipsAdapter: TipsAdapter
    private val tipsList = mutableListOf<Tip>()
    private val repositoryTip by lazy {
        (requireActivity().application as BinBuddyApp).tipsRepository
    }

    private lateinit var recommendationsAdapter: RecommendationsAdapter
    private val recommendationsListItems = mutableListOf<RecommendationItem>()
    private val repositoryRecommendation by lazy {
        (requireActivity().application as BinBuddyApp).recommendationsRepository
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvComingSoon.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        loadComingSoonData()

        // Configurar RecyclerView
        tipsAdapter = TipsAdapter(tipsList)
        binding.rvTips.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTips.adapter = tipsAdapter

        // Cargar tips desde la API
        loadTips()

        // Configurar botón "+"
        binding.btnAddTip.setOnClickListener {
            showAddTipDialog()
        }

        // Configurar RecyclerView
        recommendationsAdapter = RecommendationsAdapter(recommendationsListItems)
        binding.rvRecommendations.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRecommendations.adapter = recommendationsAdapter

        // Cargar recomendaciones
        loadRecommendations()
    }

    private fun loadComingSoonData() {
        repositoryComingSoon.getComingSoonItems().enqueue(object : Callback<List<ComingSoonItem>> {
            override fun onResponse(call: Call<List<ComingSoonItem>>, response: Response<List<ComingSoonItem>>) {
                response.body()?.let {
                    adapter = ComingSoonAdapter(it)
                    binding.rvComingSoon.adapter = adapter
                }
            }

            override fun onFailure(call: Call<List<ComingSoonItem>>, t: Throwable) {
                Toast.makeText(requireContext(), "Error al cargar datos", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun loadTips() {
        repositoryTip.getTips().enqueue(object : Callback<List<Tip>> {
            override fun onResponse(call: Call<List<Tip>>, response: Response<List<Tip>>) {
                response.body()?.let {
                    tipsList.clear()
                    tipsList.addAll(it)
                    tipsAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<Tip>>, t: Throwable) {
                Toast.makeText(requireContext(), "Error al cargar tips", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showAddTipDialog() {
        val dialog = AddTipDialogFragment { newTip ->
            addTip(newTip) // Callback para agregar el tip
        }
        dialog.show(parentFragmentManager, "AddTipDialogFragment")
    }


    private fun addTip(content: String) {
        val newTip = Tip(0, content, "2025-01-12T09:45:00Z") // ID y fecha simulados
        repositoryTip.addTip(newTip).enqueue(object : Callback<Tip> {
            override fun onResponse(call: Call<Tip>, response: Response<Tip>) {
                response.body()?.let {
                    tipsList.add(it)
                    tipsAdapter.notifyItemInserted(tipsList.size - 1)
                }
            }

            override fun onFailure(call: Call<Tip>, t: Throwable) {
                Toast.makeText(requireContext(), "Error al agregar tip", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun loadRecommendations() {
        repositoryRecommendation.getRecommendations(Constants.SERPAPI_API_KEY).enqueue(object : Callback<SerpApiResponse> {
            override fun onResponse(call: Call<SerpApiResponse>, response: Response<SerpApiResponse>) {
                if (response.isSuccessful) {
                    val items = response.body()?.organicResults?.flatMap { it.items } ?: emptyList()
                    recommendationsListItems.clear()
                    recommendationsListItems.addAll(items)
                    recommendationsAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(requireContext(), "Error en la respuesta", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SerpApiResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Error al cargar recomendaciones", Toast.LENGTH_SHORT).show()
            }
        })
    }

}
