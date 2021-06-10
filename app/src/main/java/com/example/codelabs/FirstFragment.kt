package com.example.codelabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.codelabs.databinding.FragmentFirstBinding

const val COUNT_KEY = "com.example.codelabs.COUNT_KEY"

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment: Fragment() {

    private var count : Int = 1

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState != null)
            if (savedInstanceState.containsKey(COUNT_KEY))
                count = savedInstanceState[COUNT_KEY] as Int
            else
                count = 1
        else
            count = 1

        _binding?.tvCount?.text = count.toString()
        _binding?.btCount?.setOnClickListener {
            ++count
            _binding?.tvCount?.text = count.toString()
        }

        _binding?.btToast?.setOnClickListener {
            onSaveInstanceState(savedInstanceState as Bundle)
            Toast.makeText(view.context, "This is a toast", Toast.LENGTH_LONG).show()
        }

        _binding?.btRandom?.setOnClickListener {
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(count)
            findNavController().navigate(action)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(COUNT_KEY, count)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}