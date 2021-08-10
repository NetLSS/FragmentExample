package com.lilcode.example.fragmentexample

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.lilcode.example.fragmentexample.databinding.FragmentToolbarBinding
import java.lang.ClassCastException

/**
 * A simple [Fragment] subclass.
 * Use the [ToolbarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ToolbarFragment : Fragment(), SeekBar.OnSeekBarChangeListener {
    private var _binding: FragmentToolbarBinding? = null
    private val binding get() = requireNotNull(_binding)

    var seekValue = 10

    var activityCallback: ToolbarFragment.ToolbarListener? = null

    interface ToolbarListener {
        fun onButtonClick(position: Int, text: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            activityCallback = context as ToolbarListener

        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement ToolbarListener")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.seekBar1.setOnSeekBarChangeListener(this)
        binding.button1.setOnClickListener { v: View -> buttonClicked(v) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentToolbarBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment ToolbarFragment.
         */
        @JvmStatic
        fun newInstance() =
            ToolbarFragment()
    }

    private fun buttonClicked(view: View) {
        activityCallback?.onButtonClick(seekValue, binding.editText1.text.toString())
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        seekValue = progress
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
    }
}