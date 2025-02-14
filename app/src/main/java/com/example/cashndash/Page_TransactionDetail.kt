package com.example.cashndash

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import com.example.cashndash.ui.theme.Black
import com.example.cashndash.ui.theme.LatoRegular
import com.example.cashndash.ui.theme.RalewayLight
import com.example.cashndash.ui.theme.RalewayRegular
import com.example.cashndash.ui.theme.White
import kotlin.math.roundToInt

@Composable
fun Page_TransactionDetail() {
    Column(
        Modifier
            .fillMaxSize()
            .background(color = Black)
            .padding(horizontal = 10.dp, vertical = 3.dp)
    ) {

        //Title and Amount


        Text(
            text = "Add Transaction",
            fontFamily = RalewayRegular,
            fontSize = 30.sp,
            color = White,
            textAlign = TextAlign.Start,
            modifier=Modifier.padding(bottom=20.dp)
        )

        Text(
            text = "$200.00",
            fontFamily = LatoRegular,
            fontSize = 60.sp,
            color = White,
        )


        //Details Box
        Box(
            Modifier
                .fillMaxWidth()
                .height(450.dp)
                .padding(top = 40.dp)
        ) {

            Column(
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 15.dp)
            ) {
                //Click to show more selections
                Row(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .height(50.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Category",
                        fontFamily = RalewayRegular,
                        fontSize = 25.sp,
                        color = White,
                    )

                    Text(
                        text = "Expenses",
                        fontFamily = RalewayRegular,
                        fontSize = 25.sp,
                        color = White,
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .height(50.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Title",
                        fontFamily = RalewayRegular,
                        fontSize = 25.sp,
                        color = White,
                    )

                    Text(
                        text = "Bills",
                        fontFamily = RalewayRegular,
                        fontSize = 25.sp,
                        color = White,
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .height(50.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Mood",
                        fontFamily = RalewayRegular,
                        fontSize = 25.sp,
                        color = White,
                    )

                    Text(
                        text = "Neutral",
                        fontFamily = RalewayRegular,
                        fontSize = 25.sp,
                        color = White,
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .height(50.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Date",
                        fontFamily = RalewayRegular,
                        fontSize = 25.sp,
                        color = White,
                    )

                    Text(
                        text = "24/03/2025",
                        fontFamily = RalewayRegular,
                        fontSize = 25.sp,
                        color = White,
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .height(50.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Time",
                        fontFamily = RalewayRegular,
                        fontSize = 25.sp,
                        color = White,
                    )

                    Text(
                        text = "10.45 a.m.",
                        fontFamily = RalewayRegular,
                        fontSize = 25.sp,
                        color = White,
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .height(50.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Remark",
                        fontFamily = RalewayRegular,
                        fontSize = 25.sp,
                        color = White,
                    )

                    Text(
                        text = "Rent",
                        fontFamily = RalewayRegular,
                        fontSize = 25.sp,
                        color = White,
                    )
                }

            }
        }

        //Confirm Transaction Slider
        var offsetX by remember {mutableFloatStateOf(0f)}
        val density= LocalDensity.current
        val maxOffset= with(density){290.dp.toPx()}
        var sliderWidth by remember{mutableStateOf(60.dp)}

        Box(
            Modifier
                .padding(top = 40.dp)
                .width(350.dp)
                .height(60.dp)
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(45.dp))
                .background(White.copy(0.4f)
                )
        ) {
            Box(Modifier
                .fillMaxSize()
                .draggable(
                    orientation = Orientation.Horizontal,
                    onDragStopped = {
                        offsetX=0f
                        sliderWidth=60.dp
                    },
                    //offset is In pixels, because it is added via pixels, then we convert to dp

                    state = rememberDraggableState { delta ->
                        offsetX = (offsetX+delta).coerceIn(0f,maxOffset)
                        sliderWidth=(60+(offsetX/maxOffset)*290).dp
                    })
            ){
                    Box(
                        Modifier
                            .height(60.dp)
                            .width(sliderWidth)
                            .padding(6.dp)
                            .clip(RoundedCornerShape(45.dp))
                            .background(White)
                    )
                }


            Text(
                text = "Record Transaction",
                fontFamily = RalewayRegular,
                fontSize = 20.sp,
                color = White,
                lineHeight = 60.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 5.dp)
            )

        }
    }
}

//Look Into Swipe State
@Composable
fun DraggableSlider(){
    //The offset is determined with a fraction value
    var offsetX by remember {mutableFloatStateOf(0f)}
    Box(
        Modifier
            .fillMaxHeight()
            .width(60.dp)
            .padding(6.dp)
            .clip(RoundedCornerShape(45.dp))
            .background(White)

            //Set the offset when scrolled
            .offset { IntOffset(offsetX.roundToInt(), 0) }
            .draggable(
                orientation = Orientation.Horizontal,
                state = rememberDraggableState { delta ->
                    offsetX += delta
                })

    )
}

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun SwiperSample(){
    val width = 96.dp
    val squareSize = 48.dp

    val swipeableState = rememberSwipeableState(0)
    val sizePx = with(LocalDensity.current) { squareSize.toPx() }
    val anchors = mapOf(0f to 0, sizePx to 1) // Maps anchor points (in px) to states

    Box(
        modifier = Modifier
            .width(width)
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Orientation.Horizontal
            )
            .padding(top = 40.dp)
            .fillMaxWidth(0.8f)
            .height(60.dp)
            .clip(RoundedCornerShape(45.dp))
            .background(White.copy(0.6f))
    ) {
        Box(
            Modifier
                .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
                .size(squareSize)
                .background(Color.DarkGray)
        )
    }
}