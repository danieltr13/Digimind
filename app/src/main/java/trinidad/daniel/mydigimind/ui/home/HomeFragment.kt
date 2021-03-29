package trinidad.daniel.mydigimind.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import trinidad.daniel.mydigimind.R
import trinidad.daniel.mydigimind.ui.Task

class HomeFragment : Fragment() {
    private var adaptador:AdaptadorTareas?=null
    private lateinit var homeViewModel: HomeViewModel

    companion object{
        var tasks = ArrayList<Task>()
        var first=true
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        if(first){
            fillTask()
            first=false
        }

        adaptador = AdaptadorTareas(root.context, tasks)

        val gridView:GridView = root.findViewById(R.id.gridView)

        gridView.adapter=adaptador

        return root
    }

    fun fillTask(){
        tasks.add(Task("practice1", arrayListOf("Monday","Sunday"),"17:40"))
        tasks.add(Task("practice2", arrayListOf("Tuesday"),"17:30"))
        tasks.add(Task("practice3", arrayListOf("Wednesday"),"14:00"))
        tasks.add(Task("practice4", arrayListOf("Saturday"),"11:00"))
        tasks.add(Task("practice5", arrayListOf("Thursday"),"10:40"))
    }

    private class AdaptadorTareas: BaseAdapter{
        var tasks=ArrayList<Task>()
        var context:Context?=null

        constructor(context:Context, tasks:ArrayList<Task>){
            this.context=context
            this.tasks=tasks
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var task= tasks[position]
            var inflador=LayoutInflater.from(context)
            var vista= inflador.inflate(R.layout.task_view, null)

            var tv_title: TextView = vista.findViewById(R.id.tv_title)
            var tv_time: TextView = vista.findViewById(R.id.tv_time)
            var tv_days: TextView = vista.findViewById(R.id.tv_days)

            tv_title.setText(task.title)
            tv_time.setText(task.time)
            tv_days.setText(task.days.toString())

            return vista
        }

        override fun getItem(position: Int): Any {
            return tasks[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return tasks.size
        }


    }
}