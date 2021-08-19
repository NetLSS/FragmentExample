package com.lilcode.example.fragmentexample

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.MutableLiveData
import com.lilcode.example.fragmentexample.databinding.FragmentToolbarBinding
import java.lang.ClassCastException

/**
 * A simple [Fragment] subclass.
 * Use the [ToolbarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ToolbarFragment : Fragment() {
    private var _binding: FragmentToolbarBinding? = null
    private val binding get() = requireNotNull(_binding)

    var seekValue = MutableLiveData<Int>(10)
    var text = MutableLiveData<String>("")

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

        // seek value, text 변경 될 때마다 콜백함수 호출 (액티비티에 있는 콜백호출되어 텍스트 프래그먼트로 해당값 전달 함)
        seekValue.observe(this){
            activityCallback?.onButtonClick(it, requireNotNull(text.value))
        }

        text.observe(this){
            activityCallback?.onButtonClick(requireNotNull(seekValue.value), it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.seekBar1.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                seekValue.postValue(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
        binding.button1.setOnClickListener { v: View -> buttonClicked(v) }

        // 텍스트 변경 시 마다 LiveData 업데이트 해줌
        binding.editText1.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                text.postValue(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
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
        activityCallback?.onButtonClick(requireNotNull(seekValue.value), binding.editText1.text.toString())
    }

}