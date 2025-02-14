package com.example.cashndash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cashndash.ui.theme.Black
import com.example.cashndash.ui.theme.Gray_Container
import com.example.cashndash.ui.theme.LatoRegular
import com.example.cashndash.ui.theme.RalewayLight
import com.example.cashndash.ui.theme.RalewayRegular
import com.example.cashndash.ui.theme.White

@Composable
fun Page_ExpenseIncome(){

    Column(Modifier.fillMaxSize()
        .background(color= Black)
        .padding(horizontal = 10.dp, vertical = 3.dp)
        .verticalScroll(rememberScrollState())
    ){
        Text(
            text = "Overview",
            fontFamily = RalewayRegular,
            fontSize = 30.sp,
            color = White,
            modifier=Modifier
                .fillMaxWidth()
                .padding(bottom=10.dp),
            textAlign = TextAlign.Start
        )

        //Balance Container
        Box(Modifier
            .height(90.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Gray_Container)){

            //Text Div
                Column(
                    Modifier
                    .padding(horizontal = 10.dp, vertical = 10.dp)
                    .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    Text(text="My Balance",
                        fontFamily = RalewayLight,
                        fontSize = 20.sp,
                        color = White,
                    )

                    Text(text="$2000.00",
                        fontFamily = LatoRegular,
                        fontSize = 35.sp,
                        color = White,
                    )


                }
            }

        Row(modifier =Modifier
            .padding(vertical = 20.dp)
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween){

            //Income Box
            Box(Modifier
                .height(80.dp)
                .width(185.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Gray_Container)
            ){

                Column(
                    Modifier
                        .padding(horizontal = 10.dp, vertical = 10.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    Text(text="Income",
                        fontFamily = RalewayLight,
                        fontSize = 20.sp,
                        color = White,
                    )

                    Text(text="+$2500.00",
                        fontFamily = LatoRegular,
                        fontSize = 25.sp,
                        color = White,
                    )
                }
            }

            //Expense Box
            Box(Modifier
                .height(80.dp)
                .width(185.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Gray_Container)
            ){
                Column(
                    Modifier
                        .padding(horizontal = 10.dp, vertical = 10.dp)
                        .fillMaxSize(),
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
                        color = White,
                    )
                }
            }
        }

        //Transactions
        Box(Modifier
            .padding(top = 10.dp)
            .fillMaxWidth()){

            Column(){
                Text(
                    text="This Month",
                    fontFamily = RalewayLight,
                    fontSize = 20.sp,
                    color = White,
                    modifier=Modifier.padding(bottom = 5.dp)
                )

                TransactionContainer()
                TransactionContainer()
                TransactionContainer()
                TransactionContainer()
                TransactionContainer()
                TransactionContainer()
            }

        }






    }
}

