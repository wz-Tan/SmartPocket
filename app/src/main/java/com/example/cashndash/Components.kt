package com.example.cashndash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.example.cashndash.ui.theme.Gray_Container

@Composable
fun GrayContainer(width:Dp, height:Dp, contents: @Composable()() -> Unit){
    Box(Modifier.width(width)
        .height(height)
        .background(Gray_Container)){
        contents()
    }
}