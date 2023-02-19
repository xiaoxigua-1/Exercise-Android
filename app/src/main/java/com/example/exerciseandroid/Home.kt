package com.example.exerciseandroid

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.exerciseandroid.ui.theme.ExerciseAndroidTheme

data class Activity(val id: Int, val title: String, val image: Painter)

@Composable
fun Home() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(20.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = null
                )
            }

            Text("123")
        }

        Spacer(modifier = Modifier.height(20.dp))
        ActivityInfo()
        NewList()
        Info()
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ActivityInfo() {
    val activityList = listOf(
        Activity(1, "Test", painterResource(id = R.drawable.ic_launcher_background)),
        Activity(1, "Test", painterResource(id = R.drawable.ic_launcher_background)),
        Activity(1, "Test", painterResource(id = R.drawable.ic_launcher_background)),
        Activity(1, "Test", painterResource(id = R.drawable.ic_launcher_background)),
        Activity(1, "Test", painterResource(id = R.drawable.ic_launcher_background)),
        Activity(1, "Test", painterResource(id = R.drawable.ic_launcher_background)),
        Activity(1, "Test", painterResource(id = R.drawable.ic_launcher_background)),
        Activity(1, "Test", painterResource(id = R.drawable.ic_launcher_background)),
        Activity(1, "Test", painterResource(id = R.drawable.ic_launcher_background)),
        Activity(1, "Test", painterResource(id = R.drawable.ic_launcher_background)),
        Activity(1, "Test", painterResource(id = R.drawable.ic_launcher_background)),
        Activity(1, "Test", painterResource(id = R.drawable.ic_launcher_background)),
    )
    val lazyListState = rememberLazyListState()
    Column {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),

            state = lazyListState,
            userScrollEnabled = false
        ) {
            items(activityList) {
                Card(
                    modifier = Modifier
                        .widthIn(0.dp, 200.dp)
                        .padding(horizontal = 10.dp)
                ) {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Image(
                            painter = it.image,
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(it.title, modifier = Modifier.align(Alignment.BottomEnd))
                    }
                }
            }
        }
    }
    
}

@Composable
fun Info() {

}

@Composable
fun NewList() {

}

@Preview
@Composable
fun PreviewHome() {
    ExerciseAndroidTheme {
        Home()
    }
}