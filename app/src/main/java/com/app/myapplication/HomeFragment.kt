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
import com.app.myapplication.R
import com.app.myapplication.adapter.ListMathAdapter
import java.lang.Math.random
import kotlin.random.Random

class HomeFragment : Fragment(), OnClickListener {
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
    var kq = 0
    var mCount = 1
    var one = 0
    var two = 0
    var mPoint = 0
    var randomList: List<Int>? = ArrayList()
    var listMath: ArrayList<String>? = ArrayList()
    var mCalculation = ""
    var mType = ""
    var listMathAdapter: ListMathAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_number_one = view.findViewById<TextView>(R.id.tv_number_one)
        tv_calculation = view.findViewById<TextView>(R.id.tv_calculation)
        tv_number_two = view.findViewById<TextView>(R.id.tv_number_two)
        tv_value = view.findViewById<TextView>(R.id.tv_value)
        tv_a = view.findViewById<TextView>(R.id.tv_a)
        tv_b = view.findViewById<TextView>(R.id.tv_b)
        tv_c = view.findViewById<TextView>(R.id.tv_c)
        tv_d = view.findViewById<TextView>(R.id.tv_d)
        tv_count = view.findViewById<TextView>(R.id.tv_count)
        tv_point = view.findViewById<TextView>(R.id.tv_point)
        img_next = view.findViewById<TextView>(R.id.img_next)
        img_view = view.findViewById<TextView>(R.id.img_view)
        img_home = view.findViewById<TextView>(R.id.img_home)
        img_back = view.findViewById<ImageView>(R.id.img_back)

        mType = arguments?.getString("calculation") as String
        init()
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
        val calculation = ('a'..'b').random()
        val number1 = (1 until 10).random()
        val number2 = (1 until 10).random()


        if (number1 > number2) {
            one = number1
            two = number2
        } else {
            one = number2
            two = number1
        }

        if (calculation == 'a') {
            mCalculation = if (mType == "1") "+" else "x"
            kq = if (mType == "1") one + two else one * two
        } else {

            mCalculation = if (mType == "1") "-" else ":"
            kq = if (mType == "1") one - two else generateQuestion()
        }
        tv_calculation?.setText(mCalculation)
        tv_number_one?.setText(" ${one} ")
        tv_number_two?.setText(" ${two} ")

        tv_a?.text = (kq + randomList!![0]).toString()
        tv_b?.text = ((kq + randomList!![1]).toString())
        tv_c?.text = ((kq + randomList!![2]).toString())
        tv_d?.text = ((kq + randomList!![3]).toString())

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
        tv_count?.text = "$mCount/10"
        tv_value?.text = "?"
    }

    fun onBackPressed() {
        Toast.makeText(requireContext(), "Home", Toast.LENGTH_SHORT).show()
    }

    private fun generateQuestion(): Int {
        val first = (Math.random() * 20 + 1).toInt()
        val firstNumDivisors = getDivisors(first)
        val divisorsRandIndex = (Math.random() * firstNumDivisors.size).toInt()
        val second = firstNumDivisors[divisorsRandIndex]
        //  Log.e("generateQuestion","$first $second")
        one = first
        two = second
        return one / two
        // Create your question from first and second numbers here
    }

    private fun getDivisors(number: Int): List<Int> {
        val divisors: MutableList<Int> = ArrayList()
        for (i in 1..number)
            if (number % i == 0) divisors.add(i)
        return divisors
    }

    override fun onClick(p0: View?) {
        when (p0) {
            tv_a -> {
                if (checkCorrect(kq + randomList!![0])) {
                    mPoint += 10
                    tv_a?.setBackground(resources.getDrawable(R.drawable.border_button_correct))
                } else {
                    tv_a?.setBackground(resources.getDrawable(R.drawable.border_button_wrong))
                }
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
            listMath?.add("$one $mCalculation $two = $number")
        } else listMath?.add("$one $mCalculation $two = $number (correct: $kq)")
        return check
    }

    private fun setDisable() {
        tv_a?.setOnClickListener(null)
        tv_b?.setOnClickListener(null)
        tv_c?.setOnClickListener(null)
        tv_d?.setOnClickListener(null)
        if (mCount < 10)
            img_next?.visibility = View.VISIBLE
        else {
            Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()
            for (i in 0 until listMath!!.size) {
                Log.d("listMath", listMath!![i])
            }
            img_view?.visibility = View.VISIBLE
            img_home?.visibility = View.VISIBLE
        }
        tv_point?.text = "$mPoint/100"
        tv_value?.text = kq.toString()
    }


    private fun customDiaLog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_dialog)
        val list_math = dialog.findViewById(R.id.list_math) as RecyclerView
        val img_close = dialog.findViewById(R.id.img_close) as ImageView
        listMathAdapter = ListMathAdapter(listMath!!)
        val layout = LinearLayoutManager(requireContext())
        list_math.layoutManager = layout
        list_math.adapter = listMathAdapter

        img_close.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}