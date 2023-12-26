package com.example.myapplication

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.app.myapplication.Question
import com.app.myapplication.R
import com.app.myapplication.adapter.ListCountNumberAdapter
import com.app.myapplication.adapter.ListMathAdapter
import java.lang.Math.random
import kotlin.random.Random

class CountNumberFragment : Fragment(), OnClickListener {
    var tv_number_one: TextView? = null
    var tv_calculation: TextView? = null
    var tv_number_two: TextView? = null
    var tv_value: TextView? = null
    var img_next: TextView? = null
    var img_home: TextView? = null
    var img_view: TextView? = null
    var img_back: ImageView? = null
    var tv_a: TextView? = null
    var tv_b: TextView? = null
    var tv_c: TextView? = null
    var tv_d: TextView? = null
    var tv_count: TextView? = null
    var tv_point: TextView? = null
    var tv_question: TextView? = null
    var imgView: ImageView? = null
    var kq = 0
    var question :Question?=null
    var mCount = 1
    var one = 0
    var two = 0
    var mPoint = 0
    var randomList: List<Int>? = ArrayList()
    var listMath: ArrayList<String>? = ArrayList()
    var listQuestion: ArrayList<Question>? = ArrayList()
    var mCalculation = ""
    var mType = ""
    var listCountNumberAdapter: ListCountNumberAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_count_number, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        tv_number_one = view.findViewById<TextView>(R.id.tv_number_one)
//        tv_calculation = view.findViewById<TextView>(R.id.tv_calculation)
//        tv_number_two = view.findViewById<TextView>(R.id.tv_number_two)
//        tv_value = view.findViewById<TextView>(R.id.tv_value)
        tv_point = view.findViewById<TextView>(R.id.tv_point)
        tv_a = view.findViewById<TextView>(R.id.tv_a)
        tv_b = view.findViewById<TextView>(R.id.tv_b)
        tv_c = view.findViewById<TextView>(R.id.tv_c)
        tv_d = view.findViewById<TextView>(R.id.tv_d)
        tv_count = view.findViewById<TextView>(R.id.tv_count)
        tv_question = view.findViewById<TextView>(R.id.tv_question)
        img_next = view.findViewById<TextView>(R.id.img_next)
        img_view = view.findViewById<TextView>(R.id.img_view)
        img_home = view.findViewById<TextView>(R.id.img_home)
        img_back = view.findViewById<ImageView>(R.id.img_back)
        imgView = view.findViewById<ImageView>(R.id.imgView)
        setData()
        init()
    }

    fun setData() {
        listQuestion!!.add(Question("How many dairy cows are there in the picture??", 1, R.drawable.conbo))
        listQuestion!!.add(Question("How many chickens are there in the picture?", 5, R.drawable.ic_conga))
        listQuestion!!.add(Question("How many deer are there in the picture?", 4, R.drawable.conhuu))
        listQuestion!!.add(Question("How many bees are there in the picture?", 3, R.drawable.conong))
        listQuestion!!.add(Question("How many elephants are there in the picture?", 6, R.drawable.convoi))
     //   listQuestion!!.add(Question("Hỏi có bao nhiêu con gà trong hình?", 6, R.drawable.hinh1))
        listQuestion!!.add(Question("How many ducks are there in the picture?", 7, R.drawable.hinh2))
        listQuestion!!.add(Question("How many storks are there in the picture?", 10, R.drawable.hinh3))
        listQuestion!!.add(Question("How many ducks are there in the picture?", 8, R.drawable.hinh4))
        listQuestion!!.add(Question("How many red-crowned cranes are there in the picture?", 4, R.drawable.hinh5))
        listQuestion!!.add(Question("How many camels are there in the picture?", 9, R.drawable.lacda))
        listQuestion!!.add(Question("How many mammoths are there in the picture?", 3, R.drawable.voimamut))
        listQuestion!!.add(Question("How many rhinos are there in the picture?", 9, R.drawable.tegiac))
        listQuestion!!.add(Question("How many cars are there in the picture?", 9, R.drawable.xeoto))
        listQuestion!!.add(Question("How many cars are there in the picture?", 10, R.drawable.xeoto2))

    }

    fun random(n: Int) = (Math.random() * n).toInt()
    fun random(from: Int, to: Int) = (Math.random() * (to - from) + from).toInt()
    fun random(pair: Pair<Int, Int>) = random(pair.first, pair.second)

    private fun init() {
        val s: MutableSet<Int> = mutableSetOf()
        while (s.size < 4) {
            s.add((0..3).random())
        }
        randomList = s.toList()

        val index = (1 until listQuestion!!.size).random()
        question = listQuestion!![index]
        kq = question!!.number
        tv_question!!.text = question!!.mQuestion
        imgView?.setImageDrawable(resources.getDrawable(question!!.image))
        tv_a?.setText((kq + randomList!![0]).toString())
        tv_b?.setText((kq + randomList!![1]).toString())
        tv_c?.setText((kq + randomList!![2]).toString())
        tv_d?.setText((kq + randomList!![3]).toString())

        tv_a?.setOnClickListener(this)
        tv_b?.setOnClickListener(this)
        tv_c?.setOnClickListener(this)
        tv_d?.setOnClickListener(this)
        img_next?.setOnClickListener(this)
        img_view?.setOnClickListener(this)
        img_home?.setOnClickListener(this)
        img_back?.setOnClickListener(this)
        img_view?.visibility = View.GONE
        img_next?.visibility = View.GONE
        img_home?.visibility = View.GONE
        tv_a?.setBackground(resources.getDrawable(R.drawable.border_button))
        tv_b?.setBackground(resources.getDrawable(R.drawable.border_button))
        tv_c?.setBackground(resources.getDrawable(R.drawable.border_button))
        tv_d?.setBackground(resources.getDrawable(R.drawable.border_button))
        tv_count?.text = "$mCount/5"
    }

    fun onBackPressed() {
        Toast.makeText(requireContext(), "Home", Toast.LENGTH_SHORT).show()
    }


    override fun onClick(p0: View?) {
        when (p0) {
            tv_a -> {
                if (checkCorrect(kq + randomList!![0])) {
                    mPoint += 10
                    tv_a?.setBackground(resources.getDrawable(R.drawable.border_button_correct))
                } else tv_a?.setBackground(resources.getDrawable(R.drawable.border_button_wrong))
                setDisable()
            }

            tv_b -> {
                if (checkCorrect(kq + randomList!![1])) {
                    mPoint += 10
                    tv_b?.setBackground(resources.getDrawable(R.drawable.border_button_correct))
                } else tv_b?.setBackground(resources.getDrawable(R.drawable.border_button_wrong))
                setDisable()
            }

            tv_c -> {
                if (checkCorrect(kq + randomList!![2])) {
                    mPoint += 10
                    tv_c?.setBackground(resources.getDrawable(R.drawable.border_button_correct))
                } else tv_c?.setBackground(resources.getDrawable(R.drawable.border_button_wrong))
                setDisable()
            }

            tv_d -> {
                if (checkCorrect(kq + randomList!![3])) {
                    mPoint += 10
                    tv_d?.setBackground(resources.getDrawable(R.drawable.border_button_correct))
                } else tv_d?.setBackground(resources.getDrawable(R.drawable.border_button_wrong))
                setDisable()
            }

            img_next -> {
                mCount++
                init()
            }

            img_view -> {
                customDiaLog()
            }

            img_home -> {
                (activity as MainActivity).onBackPressed()
            }

            img_back -> {
                (activity as MainActivity).onBackPressed()
            }
        }
    }

    private fun checkCorrect(number: Int): Boolean {
        val check = number == kq
        if (check) {
            listMath?.add("${question!!.image},$number")
        } else listMath?.add("${question!!.image},$number (correct: $kq)")
        return check
    }

    private fun setDisable() {
        tv_a?.setOnClickListener(null)
        tv_b?.setOnClickListener(null)
        tv_c?.setOnClickListener(null)
        tv_d?.setOnClickListener(null)
        if (mCount < 5)
            img_next?.visibility = View.VISIBLE
        else {
            Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()
            for (i in 0 until listMath!!.size) {
                Log.d("listMath", listMath!![i])
            }
            img_view?.visibility = View.VISIBLE
            img_home?.visibility = View.VISIBLE
        }

        tv_point?.text = "$mPoint/50"
    }


    private fun customDiaLog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_dialog)
        val list_math = dialog.findViewById(R.id.list_math) as RecyclerView
        val img_close = dialog.findViewById(R.id.img_close) as ImageView
        listCountNumberAdapter = ListCountNumberAdapter(requireContext(),listMath!!)
        val layout = LinearLayoutManager(requireContext())
        list_math.layoutManager = layout
        list_math.adapter = listCountNumberAdapter

        img_close.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}