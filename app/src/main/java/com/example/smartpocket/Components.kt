package com.example.smartpocket

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.axis.DataCategoryOptions
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.barchart.models.BarChartType
import com.example.smartpocket.ui.theme.Black
import com.example.smartpocket.ui.theme.Gray_Container
import com.example.smartpocket.ui.theme.LatoRegular
import com.example.smartpocket.ui.theme.LightGray
import com.example.smartpocket.ui.theme.RalewayLight
import com.example.smartpocket.ui.theme.RalewayRegular
import com.example.smartpocket.ui.theme.White

@Composable
fun TransactionContainer(){
    Box(Modifier
        .padding(vertical = 5.dp)
        .height(80.dp)
        .fillMaxWidth()
        .clip(RoundedCornerShape(10.dp))
        .background(Gray_Container)){


        //Contents
        Row(Modifier
            .fillMaxSize()
            .padding(vertical=10.dp, horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween){

            //Icon Box
            Box(Modifier
                .width(50.dp)
                .height(50.dp)
                .border(2.dp, White, CircleShape),
                contentAlignment = Alignment.Center){
                PhoneIcon()
            }

            //Description
            Column() {
                Text(
                    text = "Phone Bill",
                    fontFamily = RalewayLight,
                    fontSize = 20.sp,
                    color = White,
                )

                Text(
                    text = "10 a.m. June 23 2024",
                    fontFamily = RalewayLight,
                    fontSize = 15.sp,
                    color = White,
                )
            }

            //Price
            Text(
                text = "-$500.00",
                fontFamily = LatoRegular,
                fontSize = 20.sp,
                color = White,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenu() {
    var options = listOf("Option 1", "Option 2", "Option 3")
    var displayedText by remember { mutableStateOf(options[0]) }
    var isExpanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = {
            isExpanded = it
        },
        modifier = Modifier
            .padding(vertical = 8.dp)
            .width(150.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(White.copy(0.7f))

    ) {
        Row(Modifier.fillMaxSize()){
            Text(text=displayedText,
                fontFamily = RalewayLight,
                fontSize = 20.sp,
                color = Black,
                modifier=Modifier
                    .menuAnchor()
                    .clickable {
                        Log.i("Menu","I am Clicked")
                        isExpanded=true
                    }
            )
        }


        //Actual Menu Contents
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        displayedText = option
                        isExpanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun BudgetProgressBar(){
    Column(
        Modifier
            .fillMaxWidth()
            .height(100.dp)
    ){
        //Amount Spent Section (Middle-Text Only)
        Row(
            Modifier
                .fillMaxWidth()
                .height(30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                Modifier
                    .size(10.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(Color.Red)
            )

            Text(
                text = "Spent",
                fontFamily = RalewayLight,
                fontSize = 20.sp,
                color = White,
                modifier = Modifier
                    .padding(start = 10.dp, end = 20.dp)
            )

            Box(
                Modifier
                    .size(10.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(LightGray)
            )

            Text(
                text = "Remaining",
                fontFamily = RalewayLight,
                fontSize = 20.sp,
                color = White,
                modifier = Modifier
                    .padding(start = 10.dp, end = 20.dp)
            )

        }


        //Box to Contain Both Progress Bar and the Drop Down
        Box(
            Modifier
                .padding(top = 3.dp)
                .fillMaxWidth()
                .height(60.dp)
        ) {
            var showPopup by remember { mutableStateOf(false) }
            var spentBarWidth by remember { mutableStateOf(60.dp) }
            var clickedBarWidth by remember { mutableStateOf(0.dp) }

            //Convert DP to Pixels for Canvas Usage (Customise PopUp Box)
            var radius= with(LocalDensity.current){10.dp.toPx()}

            //Progress Bar, Remaining is the Parent, Child is the Amount Spent
            Box(
                Modifier
                    .width(400.dp)
                    .height(10.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(LightGray)
                    .clickable {
                        showPopup = !showPopup
                        clickedBarWidth = 400.dp - spentBarWidth
                    }
            ) {

                //Amount Spent Bar
                Box(
                    Modifier
                        .fillMaxHeight()
                        .width(spentBarWidth)
                        .clip(RoundedCornerShape(50.dp))
                        .background(Color.Red)
                        .clickable {
                            showPopup = !showPopup
                            clickedBarWidth = spentBarWidth
                        }
                )
            }

            //PopUp Box
            if (showPopup) {
                Box(
                    Modifier
                        .height(80.dp)
                        .width(120.dp)
                        .offset(clickedBarWidth / 2, 5.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .drawBehind {

                            val path = Path().apply {
                                addRoundRect(
                                    RoundRect(
                                        left = 0f,
                                        right = size.width,
                                        top = 30f,
                                        bottom = size.height,
                                        radiusX = radius,
                                        radiusY = radius
                                    )
                                )

                                //Tail (Start, Left, Right)
                                moveTo(x=0f,y=0f)
                                lineTo(x=0f,y=80f)
                                lineTo(x=50f,y=60f)
                                close()
                            }

                            drawPath(
                                path = path,
                                color = White
                            )

                        }
                        .clickable {
                            showPopup = false
                        },
                ) {
                    Column(
                        Modifier
                            .padding(top=10.dp, start=15.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        //Actual Box Contents
                        Text(
                            text = "60% Spent",
                            fontFamily = LatoRegular,
                            fontSize = 15.sp,
                            color = Black,
                        )

                        Text(
                            text = "$8000",
                            fontFamily = LatoRegular,
                            fontSize = 15.sp,
                            color = Black
                        )
                    }
                }
            }
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

