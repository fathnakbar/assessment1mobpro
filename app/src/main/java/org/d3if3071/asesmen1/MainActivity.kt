package org.d3if3071.asesmen1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if3071.asesmen1.databinding.ActivityMainBinding
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var itemId = 0
    var tasks: List<Task> = listOf<Task>();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setState();

        binding.button.setOnClickListener {
            tasks += Task(binding.inputTugas.text.toString(), false, itemId++)
            setState()
            binding.inputTugas.text = null
        }
    }

    fun setState(){
        Log.d("setState()", tasks.toString());
        with(binding.recyclerView) {
            adapter = Adapter(tasks) {
                //Delete function
                id -> run {
                    tasks = tasks.filter { task -> task.id !==  id}
                    setState()
                }
            }
            setHasFixedSize(true)
        }
    }


}