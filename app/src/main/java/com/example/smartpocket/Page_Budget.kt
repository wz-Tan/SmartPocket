package com.example.smartpocket

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.charts.DonutPieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData
import com.example.smartpocket.ui.theme.Black
import com.example.smartpocket.ui.theme.Gray_Container
import com.example.smartpocket.ui.theme.LatoRegular
import com.example.smartpocket.ui.theme.LightGray
import com.example.smartpocket.ui.theme.RalewayLight
import com.example.smartpocket.ui.theme.RalewayRegular
import com.example.smartpocket.ui.theme.White
import kotlin.math.floor

@Composable
fun Page_Budget() {
    Column(
        Modifier
            .background(Black)
            .fillMaxSize()
            .padding(horizontal = 10.dp, vertical = 3.dp)
    ) {
        Text(
            text = "Budget",
            fontFamily = RalewayRegular,
            fontSize = 30.sp,
            color = White,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )

        //Container For Graph
        Box(
            Modifier
                .padding(top = 30.dp)
                .height(300.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)){

            //Graph->Can be canvas actually (Read On The Calculations and Clickable)
            Box(
                Modifier
                    .size(300.dp)
                    .align(Alignment.Center)
                    .drawBehind{
                        Path().apply{
                            drawArc(
                                Color.Red,
                                0f, //Angle starts from where (0f is the 90deg)
                                90f, //The angle of the arc (Visualise with useCentre)
                                useCenter = false, //Is the arc connected to the centre
                                style = Stroke(70f, cap = StrokeCap.Butt), //How to draw the graph and how it ends (this determines the looks_
                                size = Size(300.dp.toPx(),300.dp.toPx()),
                                topLeft = Offset(10f,10f) //Push the arc by how much from the ori angle (Helps in adjusting the stroke position as excess might change the visual effect)
                            )

                            drawArc(
                                Color.Green,
                                90f,
                                90f,
                                useCenter = false,
                                style = Stroke(70f, cap = StrokeCap.Butt),  //May cause it to go oversize
                                size = Size(300.dp.toPx(),300.dp.toPx()),
                                topLeft = Offset(10f,8f)
                            )

                            drawArc(
                                Color.Yellow,
                                180f,
                                180f,
                                useCenter = false,
                                style = Stroke(70f, cap = StrokeCap.Butt),  //May cause it to go oversize
                                size = Size(300.dp.toPx(),300.dp.toPx()),
                                topLeft = Offset(10f,8f)
                            )
                        }
                    }
            ){
                //Labels for spent and remaining
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Amount Spent",
                        fontFamily = RalewayLight,
                        fontSize = 20.sp,
                        color = White,
                    )

                    Text(
                        text = "$200000.00",
                        fontFamily = LatoRegular,
                        fontSize = 30.sp,
                        color = White,
                    )
                }

            }
        }

        //Middle to Bottom Section (Use a Box to Set Them On the Same Level- Allows Overlapping)
        Box(
            Modifier
                .padding(top = 20.dp)
                .fillMaxSize()
        ) {
            //*The Progress Bar Was Originally Here

            //Bottom Details
            Box(
                Modifier
                    .fillMaxSize()
            ) {

                Column(
                    Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        text = "Limits",
                        fontFamily = RalewayRegular,
                        fontSize = 25.sp,
                        color = White,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )

                    DetailBar(
                        label = "Food",
                        amtSpent = 20.00f,
                        totalAmount = 30.00f,
                        color = Color.Yellow

                    )

                    DetailBar()

                    DetailBar(
                        label = "Bills",
                        amtSpent = 200.00f,
                        totalAmount = 3000.00f,
                        color = Color.Red
                    )

                }
            }
        }
    }
}

@Composable
fun DetailBar(
    label:String="Shopping",
    amtSpent:Float=10.00f,
    totalAmount:Float=100.00f,
    color: Color=Color.Green){

    Box(
        Modifier
            .padding(top=10.dp)
            .height(100.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Gray_Container)
    ){
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.Center

        ){
            //First Row- Icon and Title
            Row(Modifier
                .fillMaxWidth()
                .height(30.dp) ,
                verticalAlignment = Alignment.CenterVertically
            ){

                Text(
                    text = label,
                    fontFamily = RalewayLight,
                    fontSize = 20.sp,
                    color = White
                )

                Icon(
                    Icons.Default.ShoppingCart,
                    "Icon",
                    tint = White,
                    modifier=Modifier
                        .padding(start=5.dp)

                )
            }


            //Progress Bar
            var spentRatio= (amtSpent/totalAmount)
            var spentBarWidth= (spentRatio*400).dp
            val roundedRatio= (floor(spentRatio*10000))/100

            Box(
                Modifier
                    .padding(top=10.dp)
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(LightGray)

            ) {
                //Amount Spent Bar
                Box(
                    Modifier
                        .fillMaxHeight()
                        .width(spentBarWidth)
                        .clip(RoundedCornerShape(3.dp))
                        .background(color)
                )
            }

            //Bottom Text (Amount Spent over Max)
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top=10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "$roundedRatio%",
                    fontFamily = LatoRegular,
                    fontSize = 20.sp,
                    color = White,
                )

                Text(
                    text = "$amtSpent of $$totalAmount",
                    fontFamily = LatoRegular,
                    fontSize = 20.sp,
                    color = White,
                )
            }
        }
    }
}

@Composable
fun customDonutChart(){
    //font to use for the text in the centre
    //The sums are added to get the percentage
    val donutChartData = PieChartData(
        slices = listOf(
            PieChartData.Slice("hehehea", 20f, Color.Green,{"Descir[tipnm"}),
            PieChartData.Slice("Dell", 30f, Color.Yellow),
            PieChartData.Slice("Lenovo", 50f,Color.Red)
        ),
        plotType = PlotType.Donut
    )

    val donutChartConfig =
        PieChartConfig(
            labelVisible = true,
            strokeWidth =70f,
            labelColor =White,
            backgroundColor = Black,
            activeSliceAlpha = .9f,
            isAnimationEnable = true,
            chartPadding = 25,
            labelFontSize = 25.sp
        )

    DonutPieChart(
        modifier=Modifier
            .fillMaxSize() ,pieChartData = donutChartData,
        pieChartConfig = donutChartConfig
    )
}