package org.d3if3071.asesmen1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.widget.TableLayout
import androidx.core.view.get
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import org.d3if3071.asesmen1.databinding.ActivityMainBinding
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var itemId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(android.view.Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        window.setFlags(
            android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,
            android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val taskController = TaskController()
        var type = false;

        val MainAdapter = Adapter();

        with(binding.recyclerView){
            adapter = MainAdapter.apply {
                updater = {
                    binding.recyclerView.adapter = MainAdapter.apply {
                        listType = type
                    }
                }
                controller = taskController
            }
        }

        binding.button.setOnClickListener {
            if (binding.inputTugas.length() > 0) {
                taskController.add(binding.inputTugas.text.toString())
                binding.inputTugas.text = null
                binding.recyclerView.adapter = MainAdapter.apply { listType = type }
            }
        }

        with(binding){
            tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val index = tabLayout.selectedTabPosition
                    type = if (index === 0) false else true
                    recyclerView.adapter = MainAdapter.apply { listType = type }
                    Log.d("Change tab", index.toString())
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }

            })
        }
    }
}