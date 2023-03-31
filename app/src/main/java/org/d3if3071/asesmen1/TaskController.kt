package org.d3if3071.asesmen1

import android.util.Log

class TaskController {
    private var tasks: MutableList<Task> = mutableListOf();
    private var done: MutableList<Task> = mutableListOf();

    var taskId = 0;
    fun add(name: String) {
        tasks.add(Task(name, false, taskId++))
        Log.d("Add task", name)
    }

    fun delete(id: Int, status: Boolean) {
        val list = if(status) done else tasks;
        list.find { it.id === id }
            ?.let {
                tasks.remove<Task>(it)
            }
    }

    fun get(type: Boolean): MutableList<Task> {
        return if (type) done else tasks
    }

    fun update(id: Int, data: Task) {
        var list: List<MutableList<Task>>

        data.run{
            list = if (status) listOf(tasks, done) else listOf(done, tasks)
        }

        list[0].find { it.id === id }?.let {
            val index = list[0].indexOf(it)
            list[0].removeAt(index)
            list[1].add(it)
        }

        Log.d("List updated", list[0].toString())

    }
}
