package com.example.cashndash

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cashndash.ui.theme.Black
import com.example.cashndash.ui.theme.Gray_Container
import com.example.cashndash.ui.theme.LatoRegular
import com.example.cashndash.ui.theme.RalewayLight
import com.example.cashndash.ui.theme.RalewayRegular
import com.example.cashndash.ui.theme.White
import me.bytebeats.views.charts.bar.render.bar.SimpleBarDrawer
import me.bytebeats.views.charts.pie.PieChart
import me.bytebeats.views.charts.pie.PieChartData
import me.bytebeats.views.charts.pie.render.ISliceDrawer
import me.bytebeats.views.charts.pie.render.SimpleSliceDrawer
import me.bytebeats.views.charts.simpleChartAnimation

@Composable
fun Page_Budget(){

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
            modifier=Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )

        //1f is max
        Box(modifier=Modifier
            .align(Alignment.CenterHorizontally)
            .size(300.dp)
            .padding(top=30.dp)){
                PieChart(
                    pieChartData = PieChartData(
                        slices = listOf(
                            PieChartData.Slice(
                                0.5f,
                                Color.Magenta
                            ),
                            PieChartData.Slice(
                                0.4f,
                                Color.Cyan
                            ),
                            PieChartData.Slice(
                                0.1f,
                                Color(0xFFb3ecec)
                            )
                        )
                    ),
                    modifier = Modifier.width(600.dp),
                    animation = simpleChartAnimation(),
                    sliceDrawer= SimpleSliceDrawer()
                )

            //Centre text in the graph
            Column(modifier=Modifier
                .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){

                Text(text="Amount Spent",
                    fontFamily = RalewayLight,
                    fontSize = 20.sp,
                    color = White,
                )

                Text(text="$200000.00",
                    fontFamily = LatoRegular,
                    fontSize = 30.sp,
                    color = White,
                )
            }

        }

        //Amount Spent Section (Middle)
        Row(Modifier
            .padding(top=20.dp)
            .fillMaxWidth()
            .height(30.dp),
            verticalAlignment = Alignment.CenterVertically){

            Box(
                Modifier
                    .size(10.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(Color.Red)
            )

            Text(text="Spent",
                fontFamily = RalewayLight,
                fontSize = 20.sp,
                color = White,
                modifier = Modifier
                    .padding(start=10.dp, end=20.dp)
            )

            Box(
                Modifier
                    .size(10.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(Color.Green)
            )

            Text(text="Remaining",
                fontFamily = RalewayLight,
                fontSize = 20.sp,
                color = White,
                modifier = Modifier
                    .padding(start=10.dp, end=20.dp)
            )

        }



        //Amount Spent Progress Bar
        Box(
            Modifier
                .padding(top=3.dp)
                .fillMaxWidth()
                .height(10.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(White) //Linear Gradient Documentary
                .align(Alignment.CenterHorizontally)
        ){

        }
    }

}
