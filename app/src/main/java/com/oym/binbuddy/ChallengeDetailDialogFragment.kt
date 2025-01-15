package com.oym.binbuddy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.oym.binbuddy.data.remote.model.Challenge
import com.oym.binbuddy.databinding.FragmentChallengeDetailDialogBinding

class ChallengeDetailDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentChallengeDetailDialogBinding? = null
    private val binding get() = _binding!!

    private lateinit var challenge: Challenge

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChallengeDetailDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtener datos del desafío
        challenge = arguments?.getParcelable("challenge")!!

        // Configurar datos
        Glide.with(requireContext())
            .load(challenge.imgUrl)
            .circleCrop()
            .into(binding.ivChallengeImage)

        binding.tvChallengeTitle.text = challenge.name
        binding.tvChallengeDescription.text = challenge.description

        // Configurar barra de progreso
        binding.progressBar.progress = 50 // Progreso de ejemplo

        // Configurar botones de compartir
        binding.btnShareFacebook.setOnClickListener {
            shareChallenge("facebook", challenge.name)
        }

        binding.btnShareInstagram.setOnClickListener {
            shareChallenge("instagram", challenge.name)
        }
    }

    private fun shareChallenge(platform: String, challengeName: String) {
        // Implementar lógica para compartir
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(challenge: Challenge): ChallengeDetailDialogFragment {
            val fragment = ChallengeDetailDialogFragment()
            val args = Bundle()
            args.putParcelable("challenge", challenge)
            fragment.arguments = args
            return fragment
        }
    }
}
