package com.example.cashndash

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import com.example.cashndash.ui.theme.Black
import com.example.cashndash.ui.theme.Gray_Container
import com.example.cashndash.ui.theme.LatoRegular
import com.example.cashndash.ui.theme.LightGray
import com.example.cashndash.ui.theme.RalewayLight
import com.example.cashndash.ui.theme.RalewayRegular
import com.example.cashndash.ui.theme.White


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Page_Analysis() {
    var filters = listOf("Income", "Expenses", "Income/Expenses")
    var filterText by remember { mutableStateOf(filters[0]) }
    var menuExpanded by remember { mutableStateOf(false) }

    Column(
        Modifier
            .background(Black)
            .fillMaxSize()
            .padding(horizontal = 10.dp, vertical = 3.dp)
    ) {
        Text(
            text = "Analysis",
            fontFamily = RalewayRegular,
            fontSize = 30.sp,
            color = White,
            modifier=Modifier
                .fillMaxWidth()
                .padding(bottom=10.dp),
            textAlign = TextAlign.Start
        )

        Text(
            text = "July 2025",
            fontFamily = RalewayRegular,
            fontSize = 25.sp,
            color = White,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            //Income Box
            Column(
                Modifier
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Text(
                    text = "Income",
                    fontFamily = RalewayLight,
                    fontSize = 20.sp,
                    color = White,
                )

                Text(
                    text = "+$2500.00",
                    fontFamily = LatoRegular,
                    fontSize = 25.sp,
                    color = Color.Green,
                )
            }


            //Expense
            Column(
                Modifier
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Text(
                    text = "Expenses",
                    fontFamily = RalewayLight,
                    fontSize = 20.sp,
                    color = White,
                )

                Text(
                    text = "-$500.00",
                    fontFamily = LatoRegular,
                    fontSize = 25.sp,
                    color = Color.Red,
                )
            }
        }

        //Content Box
        Box(
            Modifier
                .fillMaxSize()
                .padding(top = 20.dp)
                .clip(RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp))
                .background(Color(0xFFF7F9FF)) //Matches background colour of graph
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {

                //Widget Row
                Row(
                    Modifier.height(60.dp)
                ) {

                    //Drop Down Menu
                    ExposedDropdownMenuBox(
                        expanded = menuExpanded,
                        onExpandedChange = { menuExpanded = false },
                        Modifier
                            .padding(10.dp)
                            .width(120.dp)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(10.dp))
                            .background(LightGray)
                            .clickable {
                                menuExpanded = true
                            }
                    ) {

                        Row(
                            Modifier
                                .fillMaxSize()
                                .menuAnchor(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Stats",
                                textAlign = TextAlign.Center,
                                lineHeight = 40.sp,
                                fontFamily = RalewayRegular,
                                fontSize = 20.sp,
                                color = Black,
                                modifier = Modifier
                                    .padding(start = 5.dp)
                                    .fillMaxHeight()
                                    .fillMaxWidth(0.7f)
                            )

                            Icon(
                                Icons.Default.KeyboardArrowDown,
                                contentDescription = "Down Arrow",
                                Modifier.padding(end = 5.dp)
                            )
                        }



                        ExposedDropdownMenu(
                            expanded = menuExpanded,
                            onDismissRequest = { menuExpanded = false }
                        ) {

                            filters.forEach { it ->
                                DropdownMenuItem(
                                    text = { Text(it) },
                                    onClick = {
                                        filterText = it
                                        menuExpanded = false
                                    }
                                )
                            }

                        }
                    }

                    //Time Period Selection

                    var selectedBox by remember{ mutableIntStateOf(1) }
                    Box(
                        Modifier
                            .padding(10.dp)
                            .fillMaxHeight()
                            .width(250.dp)
                            .clip(RoundedCornerShape(10.dp))
                    ) {

                        //Row of Boxes for Customisation
                        Row(
                            Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {

                            Box(
                                Modifier
                                    .fillMaxHeight()
                                    .width(70.dp)
                                    .padding(vertical = 2.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(
                                        if(selectedBox==1) Black else Transparent)
                                    .clickable{
                                        selectedBox=1
                                    }
                            ) {
                                Text(
                                    text = "Month",
                                    textAlign = TextAlign.Center,
                                    lineHeight = 36.sp,
                                    fontFamily = RalewayRegular,
                                    fontSize = 20.sp,
                                    color = if(selectedBox==1) White else Black,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }

                            Box(
                                Modifier
                                    .fillMaxHeight()
                                    .width(70.dp)
                                    .padding(vertical = 2.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(
                                        if(selectedBox==2) Black else Transparent)
                                    .clickable{
                                        selectedBox=2
                                    }
                            ) {
                                Text(
                                    text = "Week",
                                    textAlign = TextAlign.Center,
                                    lineHeight = 36.sp,
                                    fontFamily = RalewayRegular,
                                    fontSize = 20.sp,
                                    color = if(selectedBox==2) White else Black,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                            Box(
                                Modifier
                                    .fillMaxHeight()
                                    .width(70.dp)
                                    .padding(vertical = 2.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(
                                        if(selectedBox==3) Black else Transparent)
                                    .clickable{
                                        selectedBox=3
                                    }
                            ) {
                                Text(
                                    text = "Day",
                                    textAlign = TextAlign.Center,
                                    lineHeight = 36.sp,
                                    fontFamily = RalewayRegular,
                                    fontSize = 20.sp,
                                    color = if(selectedBox==3) White else Black,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }


                        }
                    }

                }

                Text(
                    text = filterText,
                    textAlign = TextAlign.Center,
                    fontFamily = RalewayLight,
                    fontSize = 30.sp,
                    color = Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                )

                //Graph Area
                Box(Modifier
                    .fillMaxSize()){

                    val steps=5
                    //Points for the graph (Most likely need to count using ratio)
                    val pointsData=listOf(Point(0f, 40f), Point(1f, 90f), Point(2f, 0f), Point(3f, 60f), Point(4f, 10f))


                    val xAxisData = AxisData.Builder()
                        .axisStepSize(70.dp) //Space between each x point
                        .steps(pointsData.size - 1) //How many x do we want
                        .labelData { i -> i.toString() } //Label x with the index
                        .labelAndAxisLinePadding(15.dp) //Padding between axis and labels
                        .axisLineColor(Transparent)
                        .axisLabelColor(Black)
                        .build()

                    //Step size for Y is automated
                    val yAxisData = AxisData.Builder()
                        .steps(steps)
                        .labelAndAxisLinePadding(20.dp)
                        .axisLineColor(Transparent)
                        .axisLabelColor(Black)
                        .labelData { i ->
                            val yScale = 100 / steps  //max/how many y values do you want
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
                                    ShadowUnderLine(
                                        brush= Brush.verticalGradient(listOf(Color.Red, White))
                                    ),
                                    SelectionHighlightPopUp()
                                )
                            ),
                        ),
                        xAxisData = xAxisData,
                        yAxisData = yAxisData,
                        backgroundColor=Color(0xFFF7F9FF),
                    )

                    LineChart(modifier= Modifier
                        .fillMaxSize(),
                        lineChartData=lineChartData)

                }


            }

        }


    }


}


