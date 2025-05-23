package com.example.cashndash

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.axis.AxisData
import co.yml.charts.axis.DataCategoryOptions
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarChartType
import com.example.cashndash.ui.theme.Black
import com.example.cashndash.ui.theme.Gray_Container
import com.example.cashndash.ui.theme.LatoRegular
import com.example.cashndash.ui.theme.RalewayBold
import com.example.cashndash.ui.theme.RalewayRegular
import com.example.cashndash.ui.theme.RalewayThin
import com.example.cashndash.ui.theme.White
import kotlinx.coroutines.time.withTimeout
import kotlinx.coroutines.withTimeout
import kotlin.math.floor

@Composable
fun Page_Goal(){
    Box(
        Modifier
            .fillMaxSize()
            .background(Black)
            .padding(vertical=3.dp,horizontal=10.dp)
    ){
        Column(
            Modifier
                .fillMaxSize()
        ){
            Text(
                text = "Vacation",
                fontFamily = RalewayRegular,
                fontSize = 30.sp,
                color = White,
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Start
            )

            //Donut Graph Box
            Box(
                Modifier
                    .fillMaxWidth()
                    .border(2.dp,White)
            ){
                Column(
                ){
                    Row{
                        Donut(
                            size = 150,
                            color = Color.Green,
                            text = "Saved Of",
                            goal = 4000.00,
                            curr = 3400.00,
                            date=false
                        )

                        Donut(
                            size = 150,
                            color = Color.Red,
                            text = "Days Left",
                            goal = 40.00,
                            curr = 30.00,
                            date=true
                        )
                    }

                    Row{
                        Donut(
                            size = 150,
                            color = Color.Yellow,
                            text = "Weekly",
                            goal = 500.00,
                            curr = 300.00,
                            date=false
                        )

                        Donut(
                            size = 150,
                            color = Color.Blue,
                            text = "Monthly",
                            goal = 4000.00,
                            curr = 530.00,
                            date=false
                        )
                    }
                }
            }

            //Bottom Bar Chart
            BarChart()
        }
    }
}

@Composable
fun Donut(size:Int,
          color: Color,
          text:String,
          goal:Double,
          curr:Double,
          date:Boolean)
{

    val angleValue=(curr/goal)*360f
    var referenceAngle by remember{ mutableFloatStateOf(0f) }
    val drawnAngle by animateFloatAsState(
        targetValue = referenceAngle,
        animationSpec = tween(
            durationMillis = 1500
        )
    )

    LaunchedEffect(Unit) {
        referenceAngle= angleValue.toFloat()
    }

    //Box for Canvas
    Box(
        Modifier
            .padding(20.dp)
            .size(size.dp)
            .drawBehind {
                Path().apply {
                    drawCircle(
                        color= Gray_Container,
                        style = Stroke(30f, cap = StrokeCap.Round)
                    )

                    drawArc(
                        color=color,
                        startAngle = 270f,
                        sweepAngle = drawnAngle,
                        useCenter = false,
                        style = Stroke(30f, cap = StrokeCap.Round),
                        size = Size(size.dp.toPx(),size.dp.toPx())
                    )
                }
            }
    ){
        //Description
        Column(
            Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = if(!date) "$"+"%.2f".format(curr) else (goal-curr).toInt().toString(),
                fontFamily = LatoRegular,
                fontSize = 20.sp,
                color = White,
            )

            Text(
                text = text,
                fontFamily = RalewayRegular,
                fontSize = 18.sp,
                color = White,
                modifier=Modifier.padding(bottom = 10.dp)
            )

            if (!date){
                Text(
                    text = "$"+"%.2f".format(goal),
                    fontFamily = LatoRegular,
                    fontSize = 18.sp,
                    color = White,
                )
            }
        }
    }
}

@Composable
fun BarChart(){
    val barChartInfo = DataUtils.getBarChartData(
        listSize = 7,
        maxRange = 7,
        BarChartType.HORIZONTAL,
        DataCategoryOptions(true)
    )


}