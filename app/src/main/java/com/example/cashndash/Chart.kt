package com.example.cashndash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import com.example.cashndash.ui.theme.Black

//We feed data into a pre determined LineChart() function

@Composable
fun Chart(){
    val steps=5
    //Points for the graph (Most likely need to count using ratio)
    val pointsData=listOf(Point(0f, 40f), Point(1f, 90f), Point(2f, 0f), Point(3f, 60f), Point(4f, 10f))


    val xAxisData = AxisData.Builder()
        .axisStepSize(70.dp) //Space between each x point
        .backgroundColor(Color.Transparent)
        .steps(pointsData.size - 1) //How many x do we want
        .labelData { i -> i.toString() } //Label x with the index
        .labelAndAxisLinePadding(15.dp) //Padding between axis and labels
        .axisLineColor(Black)
        .axisLabelColor(Black)
        .build()

    //Step size for Y is automated
    val yAxisData = AxisData.Builder()
        .steps(steps)
        .backgroundColor(Color.Transparent)
        .labelAndAxisLinePadding(20.dp)
        .axisLineColor(Black)
        .axisLabelColor(Black)
        .labelData { i ->
            val yScale = 100 / steps
            (i * yScale).toString() //For each index, we label with a multiplier of 20 (100/5=20)
        }.build()

    //Customisation (Screw Around and Find Out)
    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    LineStyle(),
                    IntersectionPoint(),
                    SelectionHighlightPoint(), //When we pick a point
                    ShadowUnderLine(),
                    SelectionHighlightPopUp()
                )
            ),
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
    )

    LineChart(modifier= Modifier
        .fillMaxSize(),
        lineChartData=lineChartData)


}