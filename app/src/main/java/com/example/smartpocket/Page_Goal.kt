package com.example.smartpocket

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Icon
import co.yml.charts.axis.AxisData
import co.yml.charts.axis.DataCategoryOptions
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarChartType
import com.example.smartpocket.ui.theme.Black
import com.example.smartpocket.ui.theme.Gray_Container
import com.example.smartpocket.ui.theme.LatoRegular
import com.example.smartpocket.ui.theme.RalewayBold
import com.example.smartpocket.ui.theme.RalewayRegular
import com.example.smartpocket.ui.theme.RalewayThin
import com.example.smartpocket.ui.theme.White
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.regular.ArrowAltCircleRight
import compose.icons.fontawesomeicons.regular.HandPointRight
import compose.icons.fontawesomeicons.solid.AngleRight
import compose.icons.fontawesomeicons.solid.Compass
import compose.icons.fontawesomeicons.solid.Peace
import compose.icons.fontawesomeicons.solid.Pen
import compose.icons.fontawesomeicons.solid.ShoePrints
import kotlinx.coroutines.time.withTimeout
import kotlinx.coroutines.withTimeout
import kotlin.math.absoluteValue
import kotlin.math.floor


@Composable
fun Page_Goal() {
    var selected by remember{mutableStateOf(0)}

    Box(
        Modifier
            .fillMaxSize()
            .background(Black)
            .padding(vertical = 3.dp, horizontal = 10.dp)
    ) {
        Column(
            Modifier
                .fillMaxSize()
        ) {
            Spacer(Modifier.height(20.dp))


            Text(
                text = "Goals",
                fontFamily = RalewayRegular,
                fontWeight = FontWeight.SemiBold,
                fontSize = 30.sp,
                color = White,
            )

            Text(
                text = "Your Financial Targets",
                fontFamily = RalewayRegular,
                fontSize = 20.sp,
                color = White,
            )

            Spacer(Modifier.height(15.dp))


            //Open, Completed Categories
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .clip(RoundedCornerShape(10.dp)),
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(
                    Modifier
                        .background(if(selected==0) White else Gray_Container)
                        .weight(1f)
                        .fillMaxSize()
                        .clickable {
                            selected=0;
                        },
                    contentAlignment = Alignment.Center
                ){
                    Text(text = "In Progress",
                        fontFamily = RalewayRegular,
                        fontSize = 16.sp,
                        color = if(selected==0) Black else White)
                }

                VerticalDivider(color = White, thickness = 1.dp)

                Box(
                    Modifier
                        .background(if(selected==1) White else Gray_Container)
                        .weight(1f)
                        .fillMaxSize()
                        .clickable {
                            selected=1
                        },
                    contentAlignment = Alignment.Center
                ){
                    Text(text = "Completed",
                        fontFamily = RalewayRegular,
                        fontSize = 16.sp,
                        color = if(selected==1) Black else White)
                }
            }


            Spacer(modifier = Modifier.height(30.dp))

            Goal_Container("Vacation", "10 Days Left", FontAwesomeIcons.Solid.Compass)
            Goal_Container("Shoes", "2 Weeks Left", FontAwesomeIcons.Solid.ShoePrints)

        }
    }
}

@Composable
fun Goal_Container(text: String, timeLeft: String, customIcon: ImageVector) {
    Surface(
        Modifier
            .padding(bottom = 15.dp)
            .fillMaxWidth()
            .height(70.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(1.dp, White, RoundedCornerShape(10.dp)),
        color = Black
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Icon(
                customIcon,
                contentDescription = "Right Arrow",
                modifier = Modifier
                    .size(30.dp)
            )

            Spacer(
                Modifier
                    .width(20.dp)
            )

            //Column for item name and the deadline
            Column() {
                Text(
                    color = White,
                    text = text,
                    fontFamily = RalewayRegular,
                    fontSize = 20.sp
                )

                Text(
                    color = White,
                    text = timeLeft,
                    fontFamily = RalewayRegular,
                    fontSize = 16.sp
                )

            }


            //Pushes the arrow to the end
            Spacer(Modifier.weight(1f))

            Icon(
                FontAwesomeIcons.Solid.AngleRight,
                contentDescription = "Right Arrow",
                modifier = Modifier
                    .size(40.dp)
            )
        }
    }
}

