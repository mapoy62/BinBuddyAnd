package com.oym.binbuddy.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.oym.binbuddy.R
import com.oym.binbuddy.application.BinBuddyApp
import com.oym.binbuddy.data.remote.model.ChallengeDTO
import com.oym.binbuddy.data.repository.ChallengeRepository
import com.oym.binbuddy.databinding.FragmentChallengesBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChallengesFragment : Fragment() {

    private var _binding: FragmentChallengesBinding?= null
    private val binding get() = _binding!!

    private lateinit var repository: ChallengeRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChallengesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repository = (requireActivity().application as BinBuddyApp).repository

        val call : Call<MutableList<ChallengeDTO>> = repository.getChallenges()

        call.enqueue(object : Callback<MutableList<ChallengeDTO>> {
            override fun onResponse(
                call: Call<MutableList<ChallengeDTO>>,
                response: Response<MutableList<ChallengeDTO>>
            ) {
                //binding.pbLoading.visibility = View.GONE

                response.body()?.let { challenges ->
                    binding.rvChallenges.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = ChallengesAdapter(challenges){ challenge ->
                            challenge.id?.let { id ->
                                requireActivity().supportFragmentManager.beginTransaction()
                                    .replace(R.id.fr)
                            }
                        }
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}