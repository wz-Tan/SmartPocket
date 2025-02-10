package com.example.cashndash

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cashndash.ui.theme.Black
import com.example.cashndash.ui.theme.Gray_Container
import com.example.cashndash.ui.theme.LatoRegular
import com.example.cashndash.ui.theme.RalewayLight
import com.example.cashndash.ui.theme.RalewayThin
import com.example.cashndash.ui.theme.White

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
    //Semantic Tag to Hold the Text Field and CLicking Functionalities
    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = {
            Log.i("transaction", "DropDown Is Clicked")
            isExpanded = it
        },
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(0.7f)
            .height(50.dp)
//            .clip(RoundedCornerShape(15.dp))
            .background(White.copy(0.7f))

    ) {
        TextField(
            modifier = Modifier
                .menuAnchor(), //This makes it the anchor for the bottom menu
            readOnly = true,
            value = displayedText,
            onValueChange = {},
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            textStyle = TextStyle(
                fontFamily = RalewayLight,
                fontSize = 18.sp,
                color = Black
            )
        )

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

