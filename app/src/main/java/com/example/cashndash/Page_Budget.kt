package com.example.cashndash

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cashndash.ui.theme.Black
import com.example.cashndash.ui.theme.RalewayLight
import com.example.cashndash.ui.theme.RalewayRegular
import com.example.cashndash.ui.theme.White

@Composable
fun Page_Budget(){
    Column(
        Modifier
            .background(Black)
            .fillMaxSize()
            .padding(3.dp)
    ) {
        Text(
            text = "Budget",
            fontFamily = RalewayRegular,
            fontSize = 30.sp,
            color = White,
            modifier=Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Box(modifier=Modifier
            .padding(top=10.dp)
            .fillMaxWidth()
            .height(400.dp)
            .border(3.dp, White)){


        }
    }

}
