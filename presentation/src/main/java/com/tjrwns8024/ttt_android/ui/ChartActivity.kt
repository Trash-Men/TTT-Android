package com.tjrwns8024.ttt_android.ui

import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.tjrwns8024.ttt_android.R
import com.tjrwns8024.ttt_android.base.BaseActivity
import com.tjrwns8024.ttt_android.databinding.ActivityChartBinding
import com.tjrwns8024.ttt_android.viewmodel.ChartViewModel
import com.tjrwns8024.ttt_android.viewmodel.factory.ChartViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import splitties.resources.color
import splitties.resources.colorSL
import javax.inject.Inject

@AndroidEntryPoint
class ChartActivity : BaseActivity<ActivityChartBinding>() {

    @Inject
    lateinit var factory: ChartViewModelFactory

    override val layoutId: Int = R.layout.activity_chart

    override val viewModel by lazy {
        ViewModelProvider(this, factory).get(ChartViewModel::class.java)
    }

    private val trashList = mutableListOf<BarEntry>()
    private val trashCanList = mutableListOf<BarEntry>()
    private val trashLabels = mutableListOf<String>()
    private val trashCanLabels = mutableListOf<String>()

    override fun observeEvent() {
        with(viewModel) {
            getTrashEvent.observe(this@ChartActivity, {
                addTrashData(viewModel.trashMap)
            })
            getTrashCanEvent.observe(this@ChartActivity, {
                addTrashCanData(viewModel.trashCanMap)
            })
        }
    }

    private fun addTrashData(map: HashMap<String, Int>) {
        var cnt = 0f
        val iterator = map.iterator()

        while (iterator.hasNext()) {

            val a = iterator.next()
            trashList.add(BarEntry(cnt, a.value.toFloat()))
            trashLabels.add(a.key)
            cnt++
        }

        val barDataSet = BarDataSet(trashList, "????????? ??????")
        barDataSet.color = Color.parseColor("#FF4B40")


        val barData = BarData(barDataSet)
        barData.barWidth = 0.5f

        val barChart = binding.trashBarChart
        barChart.data = barData

        barChart.setGridBackgroundColor(R.color.mainColor)
        barChart.animateXY(1000, 1000)
        barChart.invalidate()
        barChart.setPinchZoom(false)

        barChart.xAxis.run {
            position = XAxis.XAxisPosition.BOTTOM//X?????? ??????????????? ??????.
            granularity = 1f // 1 ???????????? ?????? ??????
            setDrawAxisLine(true) // ??? ??????
            setDrawGridLines(false) // ??????
            textColor = ContextCompat.getColor(applicationContext, R.color.mainColor) //?????? ??????
            valueFormatter = TrashFormatter() // ??? ?????? ??? ???????????? ??????
            textSize = 10f // ????????? ??????
        }

    }

    private fun addTrashCanData(map: HashMap<String, Int>) {
        var cnt = 0f
        val iterator = map.iterator()

        while (iterator.hasNext()) {
            val a = iterator.next()
            trashCanList.add(BarEntry(cnt, a.value.toFloat()))
            trashCanLabels.add(a.key)
            cnt++
        }

        val barDataSet = BarDataSet(trashCanList, "???????????? ??????")
        barDataSet.color = Color.parseColor("#FF4B40")

        val barData = BarData(barDataSet)
        barData.barWidth = 0.5f

        val barChart = binding.trashCanBarChart
        barChart.data = barData

        barChart.animateXY(1000, 1000)
        barChart.invalidate()
        barChart.setPinchZoom(false)

        barChart.xAxis.run {
            position = XAxis.XAxisPosition.BOTTOM//X?????? ??????????????? ??????.
            granularity = 1f // 1 ???????????? ?????? ??????
            setDrawAxisLine(true) // ??? ??????
            setDrawGridLines(false) // ??????
            textColor = ContextCompat.getColor(applicationContext, R.color.mainColor) //?????? ??????
            valueFormatter = TrashCanFormatter() // ??? ?????? ??? ???????????? ??????
            textSize = 10f // ????????? ??????
        }

    }

    inner class TrashFormatter : ValueFormatter() {
        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            return trashLabels.getOrNull(value.toInt()) ?: value.toString()
        }
    }

    inner class TrashCanFormatter : ValueFormatter() {
        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            return trashCanLabels.getOrNull(value.toInt()) ?: value.toString()
        }
    }
}