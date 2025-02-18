package com.example.cashndash

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cashndash.ui.theme.Black
import com.example.cashndash.ui.theme.Gray_Container
import com.example.cashndash.ui.theme.Purple
import com.example.cashndash.ui.theme.RalewayLight
import com.example.cashndash.ui.theme.RalewayRegular
import com.example.cashndash.ui.theme.White
import compose.icons.FeatherIcons
import compose.icons.feathericons.Mic
import compose.icons.feathericons.Send
import kotlinx.coroutines.launch
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState

data class TextBoxData(
    val innerText:String,
    val sender:String
)
@Composable
fun Page_Chat() {
    Column(
        Modifier
            .fillMaxSize()
            .background(color = Black)
            .padding(horizontal = 10.dp, vertical = 3.dp)
    ) {
        Text(
            text = "Chat",
            fontFamily = RalewayRegular,
            fontSize = 30.sp,
            color = White,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Start
        )

        //Box containing Chat and Bottom Insert Textbox
        Box(
            Modifier
                .padding(top=10.dp)
                .fillMaxSize()

        ){

            var listState = rememberLazyListState() //Similar to a scroll state for lazy columns
            val coroutineScope= rememberCoroutineScope()
            var textBoxList = remember{mutableStateListOf(
                TextBoxData("This is the first message","Bot"),
                TextBoxData("This is the second message","User"),
                TextBoxData("This is the first message","Bot"),
                TextBoxData("This is the second message","User"),
                TextBoxData("This is the first message","Bot"),
                TextBoxData("This is the second message","User"),
                TextBoxData("This is the first message","Bot"),
                TextBoxData("This is the second message","User"),
                TextBoxData("This is the first message","Bot"),
                TextBoxData("This is the second message","User"),
                TextBoxData("This is the first message","Bot"),
                TextBoxData("This is the second message","User"))
            }

            //Column for chat
            //LazyColumn to speed up performance because it only renders necessary frames->
            // Has its own scroll state as well
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(bottom=50.dp),
                state = listState
            ) {
                //For Each, Map
                items(textBoxList){
                    item->
                    TextBox(item)
                }

            }

            //Bottom Input Bar
            Box(
                Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Black)
                    .border(2.dp, White, RoundedCornerShape(20.dp))
            ){
                Row(
                    Modifier
                        .fillMaxSize(),
                ){
                    Icon(
                        imageVector = FeatherIcons.Mic,
                        "Microphone Icon",
                        tint=White,
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(start=10.dp)
                    )

                    //Text Field Here
                    var inputText by remember{mutableStateOf("Text Here")}
                    TextField(
                        value=inputText,

                        onValueChange = {
                            inputText=it
                        },


                        textStyle = TextStyle(
                            color = White,
                            fontSize = 16.sp,
                            fontFamily = RalewayRegular,
                            textAlign = TextAlign.Start,
                        ),

                        modifier = Modifier
                            .padding(start = 10.dp)
                            .width(290.dp)
                            .fillMaxHeight()
                            .border(1.dp,White)
                            .horizontalScroll(rememberScrollState()),

                        colors = TextFieldDefaults.colors(
                            focusedTextColor = White,
                            unfocusedTextColor = White,
                            unfocusedContainerColor = Black,
                            focusedContainerColor=Black
                        )
                    )


                    Icon(
                        imageVector = FeatherIcons.Send,
                        contentDescription = "Send button",
                        tint = White,
                        modifier=Modifier
                            .fillMaxHeight()
                            .padding(start=10.dp)
                            .clickable{
                                if (inputText!=""){
                                    textBoxList.add(TextBoxData(inputText,"User"))
                                    inputText=""

                                    //Use Coroutine Scope to Scroll
                                    //LazyColumn needs items(textBoxList) to scroll to the item index
                                    coroutineScope.launch{
                                        listState.scrollToItem(textBoxList.size-1)
                                    }
                                }
                            }
                    )
                }
            }
        }


    }
}

@Composable
fun TextBox(textBoxData: TextBoxData) {
    var textWidth: Dp = measureTextWidth(textBoxData.innerText, TextStyle(
        color = White,
        fontSize = 20.sp,
        fontFamily = RalewayRegular
    ))+20.dp

    //Box that takes up the entire space
    Box(
        Modifier
            .padding(bottom=20.dp)
            .fillMaxWidth()
    ) {
        //Container for Text
        Box(
            Modifier
                .width(
                    if (textWidth>=240.dp) 240.dp else textWidth
                )
                .clip(RoundedCornerShape(15.dp))
                .background(
                    if (textBoxData.sender == "Bot") Purple
                    else Gray_Container
                )
                .align(
                    if (textBoxData.sender == "Bot") Alignment.CenterStart
                    else Alignment.CenterEnd
                )
        ) {
            Text(
                text = textBoxData.innerText,
                textAlign = TextAlign.Start,
                fontFamily = RalewayRegular,
                fontSize = 16.sp,
                color = White,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            )
        }
    }
}

//Used to calculate width for text Box (Calculates pixel size in text box, then extract width)
@Composable
fun measureTextWidth(inputText:String,style: TextStyle): Dp {
    val measurer= rememberTextMeasurer()
    val widthInPixel=measurer.measure(inputText,style).size.width
    return with(LocalDensity.current){widthInPixel.toDp()}
}

