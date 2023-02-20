package com.example.exerciseandroid

import android.annotation.SuppressLint
import android.text.format.DateFormat
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.exerciseandroid.ui.theme.ExerciseAndroidTheme
import java.util.*

data class Activity(val id: Int, val title: String, val image: Painter)

data class NewPostData(val id: Int, val title: String, val route: String, val date: Date)

@Composable
fun Home(nav: NavController) {
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
        Spacer(modifier = Modifier.height(20.dp))
        NewList(nav)
        Info()
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
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
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            state = lazyListState
        ) {
            items(activityList) {
                Card(
                    modifier = Modifier
                        .width(200.dp)
                        .height(100.dp)
                        .padding(horizontal = 10.dp)
                ) {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Image(
                            painter = it.image,
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            it.title, modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(end = 20.dp)
                        )
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
fun NewList(nav: NavController) {
    val newList = listOf(
        NewPostData(1, "Hi", "1", Date()),
        NewPostData(1, "Hi", "1", Date())
    )


    LazyColumn {
        item {
            Row {
                Text("時間", textAlign = TextAlign.Center, modifier = Modifier.width(60.dp))
                Spacer(modifier = Modifier.width(20.dp))
                Text("標題", textAlign = TextAlign.Center)
            }
        }
        items(newList) {
            NewPost(it, nav)
        }
    }
}

@Composable
fun NewPost(data: NewPostData, nav: NavController) {
    val formatTime = DateFormat.format("yyyy/MM/dd", data.date).toString()

    Row(modifier = Modifier
        .padding(5.dp)
        .clickable {
            nav.navigate("new-post/${data.id}")
        }) {
        Text(formatTime, modifier = Modifier.width(60.dp))

        Spacer(modifier = Modifier.width(20.dp))

        Text(data.title)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    ExerciseAndroidTheme {
        Home(rememberNavController())
    }
}