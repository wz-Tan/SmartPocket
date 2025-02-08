package com.example.cashndash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cashndash.ui.theme.Black
import com.example.cashndash.ui.theme.FontRaleway
import com.example.cashndash.ui.theme.White

@Composable
fun ExpenseIncomePage(){
    Column(Modifier.fillMaxSize()
        .padding(horizontal=16.dp)
        .background(color= Black)){

        Box(Modifier.width(200.dp)
            .height(90.dp)
            .padding(10.dp)
            ){
            Text("Balance", Modifier.align(Alignment.CenterStart))
        }
    }
}