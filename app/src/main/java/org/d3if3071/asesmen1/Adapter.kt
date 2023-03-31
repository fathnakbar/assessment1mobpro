package org.d3if3071.asesmen1

import android.animation.Animator
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.d3if3071.asesmen1.databinding.ListItemBinding


class Adapter() :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    lateinit var controller: TaskController
    var listType = false;
    lateinit var updater: () -> Unit;


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task, updater: () -> Unit, controller: TaskController) = with(binding) {
//            checkBox.text = task.name
//            checkBox.isChecked = task.status

            animationView.setFailureListener { }

            var clicked = false;

            animationView.addAnimatorListener(object : android.animation.Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {

                }

                override fun onAnimationEnd(animation: Animator) {
                    if ((task.status && clicked) || !task.status) {
                        controller.update(task.id, task.apply {
                            status = !task.status
                        })
                        updater();
                    }
                }

                override fun onAnimationCancel(animation: Animator) {

                }

                override fun onAnimationRepeat(animation: Animator) {

                }

            })

            if (task.status) {
                radioButton.visibility = GONE
                animationView.visibility = VISIBLE
                animationView.playAnimation()
            }

            animationView.setOnClickListener {
                animationView.speed = -1.5f
                clicked = true
                animationView.playAnimation()
            }

            radioButton.setOnClickListener {
                radioButton.visibility = GONE
                animationView.visibility = VISIBLE
                animationView.speed = 1.5f
                animationView.playAnimation()
            }

            text.text = task.name

//
//
//            checkBox
//
//            checkBox.setOnClickListener {

//            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false);
        return ViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(controller.get(listType)[position], updater, controller)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = controller.get(listType).size

    fun setTaskController(controller: TaskController){
        this.controller = controller
    }

}

