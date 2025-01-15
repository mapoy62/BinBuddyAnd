package com.oym.binbuddy.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.oym.binbuddy.ChallengeDetailDialogFragment
import com.oym.binbuddy.application.BinBuddyApp
import com.oym.binbuddy.data.remote.model.Challenge
import com.oym.binbuddy.databinding.FragmentChallengesBinding
import com.oym.binbuddy.ui.adapters.ChallengesAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChallengesFragment : Fragment() {

    private lateinit var binding: FragmentChallengesBinding
    private lateinit var challengesAdapter: ChallengesAdapter
    private val challengesList = mutableListOf<Challenge>()
    private val repository by lazy {
        (requireActivity().application as BinBuddyApp).challengesRepository
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChallengesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar RecyclerView
        challengesAdapter = ChallengesAdapter(challengesList) { challenge ->
            showChallengeDetailDialog(challenge)
        }
        binding.rvChallenges.layoutManager = LinearLayoutManager(requireContext())
        binding.rvChallenges.adapter = challengesAdapter

        // Cargar desafíos
        loadChallenges()
    }

    private fun loadChallenges() {
        repository.getChallenges().enqueue(object : Callback<List<Challenge>> {
            override fun onResponse(call: Call<List<Challenge>>, response: Response<List<Challenge>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        challengesList.clear()
                        challengesList.addAll(it)
                        challengesAdapter.notifyDataSetChanged()
                    }
                } else {
                    Toast.makeText(requireContext(), "Error en la respuesta", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Challenge>>, t: Throwable) {
                Toast.makeText(requireContext(), "Error al cargar desafíos", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showChallengeDetailDialog(challenge: Challenge) {
        // Abre un DialogFragment con los detalles del desafío
        val dialog = ChallengeDetailDialogFragment.newInstance(challenge)
        dialog.show(parentFragmentManager, "ChallengeDetailDialogFragment")
    }
}
