package com.example.smartpocket

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import com.example.smartpocket.ui.theme.Black
import com.example.smartpocket.ui.theme.Purple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLayout(){
    Scaffold(
        topBar= {
            TopAppBar(
                colors = TopAppBarColors(
                    containerColor = Black,
                    titleContentColor = White,
                    scrolledContainerColor = Purple,
                    navigationIconContentColor = White,
                    actionIconContentColor = White
                ),
                title = { Text("") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            )
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                containerColor = Black,
                content = { Text("") }
            )
        }
    ) { padding->
        Column(modifier = Modifier
            .padding(padding)
            .fillMaxSize()){
            Page_Goal()
        }
    }
}