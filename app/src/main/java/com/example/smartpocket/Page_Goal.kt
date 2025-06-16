package com.example.smartpocket

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.draw.shadow
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
import com.example.smartpocket.ui.theme.Purple
import com.example.smartpocket.ui.theme.RalewayBold
import com.example.smartpocket.ui.theme.RalewayRegular
import com.example.smartpocket.ui.theme.RalewayThin
import com.example.smartpocket.ui.theme.White
import compose.icons.FeatherIcons
import compose.icons.FontAwesomeIcons
import compose.icons.feathericons.Plus
import compose.icons.feathericons.PlusCircle
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.regular.ArrowAltCircleRight
import compose.icons.fontawesomeicons.regular.HandPointRight
import compose.icons.fontawesomeicons.solid.AngleRight
import compose.icons.fontawesomeicons.solid.Compass
import compose.icons.fontawesomeicons.solid.Peace
import compose.icons.fontawesomeicons.solid.Pen
import compose.icons.fontawesomeicons.solid.PlusCircle
import compose.icons.fontawesomeicons.solid.ShoePrints
import kotlinx.coroutines.time.withTimeout
import kotlinx.coroutines.withTimeout
import kotlin.math.absoluteValue
import kotlin.math.ceil
import kotlin.math.floor


@Composable
fun Page_Goal() {
    var selected by remember { mutableStateOf(0) }

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

            Spacer(Modifier.height(30.dp))


            //Open, Completed Categories
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(1.dp, White, RoundedCornerShape(10.dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    Modifier
                        .background(if (selected == 0) White else Gray_Container)
                        .weight(1f)
                        .fillMaxSize()
                        .clickable {
                            selected = 0;
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "In Progress",
                        fontFamily = RalewayRegular,
                        fontSize = 16.sp,
                        color = if (selected == 0) Black else White
                    )
                }

                VerticalDivider(color = White, thickness = 1.dp)

                Box(
                    Modifier
                        .background(if (selected == 1) White else Gray_Container)
                        .weight(1f)
                        .fillMaxSize()
                        .clickable {
                            selected = 1
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Completed",
                        fontFamily = RalewayRegular,
                        fontSize = 16.sp,
                        color = if (selected == 1) Black else White
                    )
                }
            }


            Spacer(modifier = Modifier.height(30.dp))
            val scrollState = rememberScrollState()

            //Goal Cards
            Box(
                Modifier.fillMaxSize()
            ) {

                Column(
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Goal_Container(
                        "Vacation",
                        "10 Days Left",
                        FontAwesomeIcons.Solid.Compass,
                        300f,
                        200f
                    )
                    Goal_Container(
                        "Vacation",
                        "10 Days Left",
                        FontAwesomeIcons.Solid.Compass,
                        300f,
                        200f
                    )
                    Goal_Container(
                        "Vacation",
                        "10 Days Left",
                        FontAwesomeIcons.Solid.Compass,
                        300f,
                        200f
                    )
                    Goal_Container(
                        "Vacation",
                        "10 Days Left",
                        FontAwesomeIcons.Solid.Compass,
                        300f,
                        200f
                    )
                    Goal_Container(
                        "Vacation",
                        "10 Days Left",
                        FontAwesomeIcons.Solid.Compass,
                        300f,
                        200f
                    )
                }

                //Add Button
                Surface(
                    Modifier
                        .align(Alignment.BottomCenter)
                        .padding(10.dp)
                        .size(50.dp)
                        .clip(RoundedCornerShape(90.dp)),
                    color = Purple
                ){
                    Icon(
                        FeatherIcons.Plus,
                        contentDescription = "Add Symbol",

                    )
                }
            }


        }
    }
}

@Composable
fun Goal_Container(
    text: String,
    timeLeft: String,
    customIcon: ImageVector,
    totalAmt: Float,
    currAmt: Float
) {
    val difference = totalAmt - currAmt
    val formattedDifference = "%.2f".format(difference)
    Surface(
        Modifier
            .padding(bottom = 15.dp)
            .fillMaxWidth()
            .height(120.dp)
            .shadow(15.dp, RoundedCornerShape(10.dp), ambientColor = White, spotColor = White)
            .clip(RoundedCornerShape(10.dp)),
        color = Black
    ) {

        Row(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,

            ) {


            Spacer(
                Modifier
                    .width(5.dp)
            )

            //Column for item name and the deadline
            Column(
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.8f)
            ) {
                Spacer(Modifier.height(15.dp))
                Text(
                    color = White,
                    text = text,
                    fontFamily = RalewayRegular,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    color = White,
                    text = "RM$formattedDifference Remaining",
                    fontFamily = RalewayRegular,
                    fontSize = 14.sp
                )

                //Progress Bar
                Box(
                    Modifier
                        .padding(top = 10.dp)
                        .height(10.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(White)
                ) {
                    Box(
                        Modifier
                            .height(10.dp)
                            .fillMaxWidth(currAmt / totalAmt)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Purple)
                    )
                }


            }


            //Pushes the arrow to the end
            Spacer(Modifier.weight(1f))

            Icon(
                customIcon,
                contentDescription = "Custom Icon",
                modifier = Modifier
                    .size(40.dp)
            )
        }
    }
}

