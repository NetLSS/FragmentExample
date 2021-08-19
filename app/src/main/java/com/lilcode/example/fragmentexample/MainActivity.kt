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

    // 프레그먼트 끼리는 직접적으로 통신하지 않기 때문에 모든 통신은 프래그먼트를 포함하고있는 액티비티를 끼고 이루어져야한다. 그래서 위와 같은 매커니즘 사용
}