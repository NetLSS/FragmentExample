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

    // ToolbarFragment.ToolbarListener 로 툴바 프레그먼트의 버튼이 눌리면 호출됨(프레그먼트가 액티비티를 호출)
    override fun onButtonClick(fontSize: Int, text: String) {
        val textFragment = supportFragmentManager.findFragmentById(binding.textFragment.id) as TextFragment
        // 액티비티에서 프래그먼트의 참조를 얻고 해당 프레그먼트의 public 함수를 호출할 수 있음
        textFragment.changeTextProperties(fontSize, text)
    }
}