package com.oym.binbuddy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.oym.binbuddy.databinding.FragmentAddTipDialogBinding

class AddTipDialogFragment(
    private val onTipAdded: (String) -> Unit // Callback para agregar el tip
) : DialogFragment() {

    private var _binding: FragmentAddTipDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTipDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar botón de agregar
        binding.btnAdd.setOnClickListener {
            val tipContent = binding.etTipContent.text.toString()
            if (tipContent.isNotEmpty()) {
                onTipAdded(tipContent)
                dismiss()
            } else {
                Toast.makeText(requireContext(), "Tip cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }

        // Configurar botón de cancelar
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
