package com.example.cashndash

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cashndash.ui.theme.Black
import com.example.cashndash.ui.theme.Gray_Container
import com.example.cashndash.ui.theme.LatoRegular
import com.example.cashndash.ui.theme.RalewayLight
import com.example.cashndash.ui.theme.RalewayRegular
import com.example.cashndash.ui.theme.White

@Composable
fun Page_Analysis() {
    Column(
        Modifier
            .background(Black)
            .fillMaxSize()
            .padding(3.dp)
    ) {

        Text(
            text = "Analysis",
            fontFamily = RalewayRegular,
            fontSize = 30.sp,
            color = White,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Row(
            modifier = Modifier
                .padding(top=10.dp)
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
                .padding(top=20.dp)
                .clip(RoundedCornerShape(20.dp,20.dp,0.dp,0.dp))
                .background(White)
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                //Widget Row
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    var filterText by remember{mutableStateOf("")}
                    var menuExpanded by remember {mutableStateOf(false)}
                    //Drop Down Menu
                    Box(Modifier
                        .padding()
                        .width(200.dp)
                        .height(40.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .border(3.dp,Color.Red)){

                        Text(text=filterText,
                            fontFamily = RalewayLight,
                            fontSize = 20.sp,
                            color = Black,
                            modifier=Modifier
                                .menuAnchor()
                                .clickable {
                                    Log.i("Menu","I am Clicked")
                                    menuExpanded=true
                                })


                    }

                }

            }

        }



    }





}
