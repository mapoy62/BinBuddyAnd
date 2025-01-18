package com.oym.binbuddy.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.oym.binbuddy.application.BinBuddyApp
import com.oym.binbuddy.data.remote.model.Reward
import com.oym.binbuddy.databinding.FragmentRewardsBinding
import com.oym.binbuddy.ui.adapters.RewardsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RewardsFragment : Fragment() {

    private lateinit var binding: FragmentRewardsBinding
    private lateinit var rewardsAdapter: RewardsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRewardsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        loadRewards()
    }

    private fun setupRecyclerView() {
        rewardsAdapter = RewardsAdapter()
        binding.rewardsRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rewardsRecyclerView.adapter = rewardsAdapter
    }

    private fun loadRewards() {
        (requireActivity().application as BinBuddyApp).rewardsRepository.getRewards().enqueue(object :
            Callback<List<Reward>> {
            override fun onResponse(
                call: Call<List<Reward>>,
                response: Response<List<Reward>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { rewardsAdapter.submitList(it) }
                } else {
                    Toast.makeText(requireContext(), "Error fetching rewards", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<List<Reward>>, t: Throwable) {
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}