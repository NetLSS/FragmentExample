package com.lilcode.example.fragmentexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.lilcode.example.fragmentexample.databinding.ActivityMainBinding

class MainActivity : FragmentActivity(), ToolbarFragment.ToolbarListener {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onButtonClick(position: Int, text: String) {
        Toast.makeText(this, "onButtonClick($position: Int, $text: String)", Toast.LENGTH_SHORT).show()
    }
}