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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cashndash.ui.theme.Black
import com.example.cashndash.ui.theme.Gray_Container
import com.example.cashndash.ui.theme.LatoRegular
import com.example.cashndash.ui.theme.LightGray
import com.example.cashndash.ui.theme.RalewayBold
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

        //Donut Chart (1f is max)
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(300.dp)
                .padding(top = 30.dp)
        ) {
            PieChart(
                pieChartData = PieChartData(
                    slices = listOf(
                        PieChartData.Slice(
                            0.5f,
                            Color.Green
                        ),
                        PieChartData.Slice(
                            0.4f,
                            Color.Yellow
                        ),
                        PieChartData.Slice(
                            0.1f,
                            Color(0xFFb3ecec)
                        )
                    )
                ),
                modifier = Modifier.width(600.dp),
                animation = simpleChartAnimation(),
                sliceDrawer = SimpleSliceDrawer()
            )

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

        //Middle to Bottom Section (Use a Box to Set Them On the Same Level- Allows Overlapping)
        Box(
            Modifier
                .padding(top = 20.dp)
                .fillMaxSize()
        ){
            //*The Progress Bar Was Originally Here

            //Bottom Details
            Box(Modifier
                .fillMaxSize()
            ){

                Column(Modifier
                    .fillMaxSize()
                ){
                    Text(
                        text = "Limits",
                        fontFamily = RalewayRegular,
                        fontSize = 25.sp,
                        color = White,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )
                    DetailBar()

                }
            }
        }
    }
}

@Composable
fun DetailBar(
    label:String="Shopping",
    amtSpent:Float=10f,
    totalAmount:Float=100f,
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
            var spentBarWidth by remember { mutableStateOf(60.dp) }

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
                        .background(Color.Yellow)
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
                    text = ((amtSpent/totalAmount)*100).toString()+ "%",
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
