package com.example.exerciseandroid

import android.annotation.SuppressLint
import android.graphics.ColorSpace
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.exerciseandroid.ui.theme.ExerciseAndroidTheme
import java.util.*

data class Activity(val id: Int, val title: String, val image: Painter)

data class NewPostData(val id: Int, val title: String, val date: String)

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
        Spacer(modifier = Modifier.width(20.dp))
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
    Row(modifier = Modifier.padding(20.dp)) {
        // 1
        Column(modifier = Modifier.width(200.dp)) {
            Text("南港展覽館1館")
            Text("886-2-2725-5200")
            Text("服務台：#5111")
            Text("租借展覽場地：#5521")
            Text("租借會議室：#5527")
            Text("租借展覽及會議以外之其他各類型活動場地：#5523")
            Text("台北市 11568 南港區經貿二路 1 號")
            Text("niec@taitra.org.tw")
        }

        // 2
        Column(modifier = Modifier.width(200.dp)) {
            Text("南港展覽館2館")
            Text("886-2-2725-5200")
            Text("服務台：#6101；租借場地：展覽#5521 & #6614、活動#6616、會議#6614、#6617")
            Text("台北市 11568 南港區經貿二路 2 號")
            Text("tainex2@taitra.org.tw")

        }
    }
}

@Composable
fun NewList(nav: NavController) {
    val newList = listOf(
        NewPostData(1, "「青青婚宴文創集團」團隊進駐南港2館7樓星光會議中心", "2023/02/13"),
        NewPostData(2, "南港2館電費調整公告", "2023/02/07")
    )


    LazyColumn(
        modifier = Modifier
            .padding(20.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.Gray)
    ) {
        item {
            Row(
                Modifier
                    .background(Color(0f, 0f, 1f, 0.2f))
                    .fillMaxWidth()) {
                Text("時間", textAlign = TextAlign.Center, modifier = Modifier.width(90.dp))
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
    Row(modifier = Modifier
        .padding(5.dp)
        .clickable {
            nav.navigate("new-post/${data.id}")
        }) {
        Text(data.date, modifier = Modifier.width(90.dp))

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