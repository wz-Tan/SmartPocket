package com.example.cashndash

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.view.WindowCompat
import com.example.cashndash.ui.theme.Black
import com.example.cashndash.ui.theme.Gray_Container
import com.example.cashndash.ui.theme.Purple
import com.example.cashndash.ui.theme.RalewayRegular
import com.example.cashndash.ui.theme.White
import compose.icons.FeatherIcons
import compose.icons.feathericons.Mic
import compose.icons.feathericons.Send
import kotlinx.coroutines.launch

data class TextBoxData(
    val innerText: String, val sender: String
)

//How to Scroll the LazyColumn?
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Page_Chat() {
    //Context is DOM, Activity is the Current <HTML> Tag, Window is the Viewport
    //Set the Window to NOT push the elements when the keyboard is called
    val context= LocalContext.current
    val activity=context as? Activity
    val window= activity?.window
    if (window != null) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
    val keyboardHeight=with(LocalDensity.current){
        WindowInsets.ime.getBottom(LocalDensity.current).toDp()-80.dp //80.dp is the height of the bottom app bar
    }
    Box(
        Modifier
            .fillMaxSize()
            .background(color = Black)
            .padding(horizontal = 10.dp)
            //Keeps the Content Within the Padding (Without this the window will go beyond top bar)
    ) {
        Column(
            Modifier
                .fillMaxSize()
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .zIndex(1f)
                    .background(Black)
            ){
                Text(
                    text = "Chat",
                    fontFamily = RalewayRegular,
                    fontSize = 30.sp,
                    color = White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top=3.dp),
                    textAlign = TextAlign.Start
                )
            }


            //Box containing Chat and Bottom Insert Textbox
            Box(
                Modifier
                    .padding(top = 10.dp)
                    .fillMaxSize()
                    .offset(x=0.dp,y= if(WindowInsets.isImeVisible) -keyboardHeight else 0.dp)
                    .zIndex(0.5f)
            ) {

                var listState = rememberLazyListState()
                val coroutineScope = rememberCoroutineScope()
                var textBoxList = remember {
                    mutableStateListOf(
                        TextBoxData("This is the first message", "Bot"),
                        TextBoxData("This is the second message", "User"),
                        TextBoxData("This is the first message", "Bot"),
                        TextBoxData("This is the second message", "User"),
                        TextBoxData("This is the first message", "Bot"),
                        TextBoxData("This is the second message", "User"),
                        TextBoxData("This is the first message", "Bot"),
                        TextBoxData("This is the second message", "User"),
                        TextBoxData("This is the first message", "Bot"),
                        TextBoxData("This is the second message", "User"),
                        TextBoxData("This is the first message", "Bot"),
                        TextBoxData("This is the second message", "User"),
                        TextBoxData("This is the first message", "Bot"),
                        TextBoxData("This is the last message", "User"),

                    )
                }


                //lazyColumn for chat
                LazyColumn(
                    Modifier
                        .fillMaxSize()
                        .padding(bottom = 50.dp),
                    state = listState
                ) {
                    //For Each, Map
                    items(textBoxList) { item ->
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
                ) {
                    Row(
                        Modifier
                            .fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = FeatherIcons.Mic,
                            "Microphone Icon",
                            tint = White,
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(start = 10.dp)
                        )

                        //Text Field Here
                        var inputText by remember { mutableStateOf("") }
                        var textFieldScrollState = rememberScrollState(10)
                        var lineWidth = 255.dp
                        var textWidth = measureTextWidth(
                            inputText,
                            TextStyle(
                                fontSize = 16.sp,
                                fontFamily = RalewayRegular
                            )
                        )
                        //Configure the Alignment and Scroll State
                        TextField(
                            value = inputText,

                            onValueChange = {
                                inputText = it

                                val currLine = (textWidth / lineWidth).toInt()
                                coroutineScope.launch {
                                    textFieldScrollState.scrollTo(
                                        //Dont scroll if on first line (Default is 0)
                                        if (currLine == 0) 5 else textFieldScrollState.maxValue - 5
                                    )

                                    Log.i("TextSize",
                                        listState.layoutInfo.viewportEndOffset.toString()
                                    )
                                }



                            },

                            placeholder = {
                                Text(
                                    "Type Something...",
                                    color = White.copy(0.7f),
                                    fontSize = 16.sp,
                                    lineHeight = 16.sp,
                                    fontFamily = RalewayRegular
                                )
                            },


                            textStyle = TextStyle(
                                color = White,
                                fontSize = 16.sp,
                                fontFamily = RalewayRegular,
                                textAlign = TextAlign.Start,
                            ),

                            modifier = Modifier
                                .width(290.dp)
                                .fillMaxHeight()
                                .padding(start = 10.dp)
                                .verticalScroll(textFieldScrollState),

                            colors = TextFieldDefaults.colors(
                                focusedTextColor = White,
                                unfocusedTextColor = White,
                                unfocusedContainerColor = Black,
                                focusedContainerColor = Black
                            )
                        )

                        val regex_space =
                            Regex("^\\s*\$") // \\s* means unlimited number of empty (\\s)
                        Icon(imageVector = FeatherIcons.Send,
                            contentDescription = "Send button",
                            tint = White,
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(start = 10.dp)
                                .clickable {
                                    if (!inputText.matches(regex_space)) {
                                        textBoxList.add(TextBoxData(inputText, "User"))
                                        inputText = ""


                                        //Use Coroutine Scope to Scroll
                                        //LazyColumn needs items(textBoxList) to scroll to the item index
                                        coroutineScope.launch {
                                            listState.scrollToItem(textBoxList.size - 1)
                                        }
                                    }
                                })
                    }
                }
            }
        }
    }
}

@Composable
fun TextBox(textBoxData: TextBoxData) {
    var textWidth: Dp = measureTextWidth(
        textBoxData.innerText, TextStyle(
            color = White, fontSize = 20.sp, fontFamily = RalewayRegular
        )
    ) + 20.dp

    //Box that takes up the entire space
    Box(
        Modifier
            .padding(bottom = 10.dp)
            .fillMaxWidth()
    ) {
        //Container for Text
        Box(
            Modifier
                .width(
                    if (textWidth >= 240.dp) 240.dp else textWidth
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
fun measureTextWidth(inputText: String, style: TextStyle): Dp {
    val measurer = rememberTextMeasurer()
    val widthInPixel = measurer.measure(inputText, style).size.width
    return with(LocalDensity.current) { widthInPixel.toDp() }
}

