package com.example.smartpocket

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun PhoneIcon(){
    Icon(
        imageVector = Icons.Outlined.Phone,
        contentDescription = "Phone Icon",
        tint = Color.White
    )
}