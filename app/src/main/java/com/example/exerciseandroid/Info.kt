package com.example.exerciseandroid

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.exerciseandroid.ui.theme.ExerciseAndroidTheme

data class TabInfo(val title: String, val content: @Composable () -> Unit)

data class FloorData(val title: String, val imageId: Int, val selectColor: Color)

@Composable
fun InfoPage() {
    var current by remember {
        mutableStateOf(0)
    }
    val tabs = listOf(TabInfo("關於展館") {
        About()
    }, TabInfo("樓層立體圖") {
        Floor()
    }, TabInfo("公共藝術") {
        Arts()
    }, TabInfo("聯絡我們") {
        Contact()
    })

    Column {
        ScrollableTabRow(selectedTabIndex = current, edgePadding = 0.dp) {
            tabs.forEachIndexed { index, tabInfo ->
                Tab(selected = index == current, onClick = {
                    current = index
                }, text = {
                    Text(text = tabInfo.title)
                })
            }
        }

        tabs[current].content()
    }
}

@Composable
fun About() {

}

@Preview
@Composable
fun Floor() {
    var tabIndex by remember {
        mutableStateOf(0)
    }
    val floors = listOf(
        FloorData("1館", R.drawable.one_hall_3ds, Color(0xFF006182)),
        FloorData("2館", R.drawable.two_hall_3d, Color(0xFF00AA9D)),
    )

    Column {
        val id = floors[tabIndex].imageId

        Image(painter = painterResource(id), contentDescription = null)

        TabRow(selectedTabIndex = tabIndex, backgroundColor = Color.White) {
            floors.forEachIndexed { index, data ->
                Tab(selected = index == tabIndex, onClick = {
                    tabIndex = index
                },
                    selectedContentColor = data.selectColor,
                    text = { Text(text = data.title) })
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun Arts() {
    val arts = listOf(
        TabInfo("1館") {
            Art("四季之歌", "春，喚醒了露珠中沉睡的精靈，如彩蝶翩翩穿梭嫩芽新綠間，搖起片片飛絮漫舞風中。 夏雷如雄獅吼出牠的熱情，金色光芒飛奔蔚藍天際，隆隆高歌生命無限。 秋風翦翦吹來清涼，翻動詩篇蕭索如落葉沙沙，在水晶般透明的秋氣中。 冬之聖堂管風琴合唱起讚美歌，詠嘆生命美好，祈禱聲中似聞嬰兒伊呀，見證了另一個生命的起始。 長廊上的彩色玻璃以脈搏般的節奏，伴隨光效的拍子，引導人們用自然的步伐，走過春夏秋冬四季更替的生命之旅。", 1)
            Art("四季之歌", "春，喚醒了露珠中沉睡的精靈，如彩蝶翩翩穿梭嫩芽新綠間，搖起片片飛絮漫舞風中。 夏雷如雄獅吼出牠的熱情，金色光芒飛奔蔚藍天際，隆隆高歌生命無限。 秋風翦翦吹來清涼，翻動詩篇蕭索如落葉沙沙，在水晶般透明的秋氣中。 冬之聖堂管風琴合唱起讚美歌，詠嘆生命美好，祈禱聲中似聞嬰兒伊呀，見證了另一個生命的起始。 長廊上的彩色玻璃以脈搏般的節奏，伴隨光效的拍子，引導人們用自然的步伐，走過春夏秋冬四季更替的生命之旅。", 1)
            Art("四季之歌", "春，喚醒了露珠中沉睡的精靈，如彩蝶翩翩穿梭嫩芽新綠間，搖起片片飛絮漫舞風中。 夏雷如雄獅吼出牠的熱情，金色光芒飛奔蔚藍天際，隆隆高歌生命無限。 秋風翦翦吹來清涼，翻動詩篇蕭索如落葉沙沙，在水晶般透明的秋氣中。 冬之聖堂管風琴合唱起讚美歌，詠嘆生命美好，祈禱聲中似聞嬰兒伊呀，見證了另一個生命的起始。 長廊上的彩色玻璃以脈搏般的節奏，伴隨光效的拍子，引導人們用自然的步伐，走過春夏秋冬四季更替的生命之旅。", 1)
            Art("四季之歌", "春，喚醒了露珠中沉睡的精靈，如彩蝶翩翩穿梭嫩芽新綠間，搖起片片飛絮漫舞風中。 夏雷如雄獅吼出牠的熱情，金色光芒飛奔蔚藍天際，隆隆高歌生命無限。 秋風翦翦吹來清涼，翻動詩篇蕭索如落葉沙沙，在水晶般透明的秋氣中。 冬之聖堂管風琴合唱起讚美歌，詠嘆生命美好，祈禱聲中似聞嬰兒伊呀，見證了另一個生命的起始。 長廊上的彩色玻璃以脈搏般的節奏，伴隨光效的拍子，引導人們用自然的步伐，走過春夏秋冬四季更替的生命之旅。", 1)
            Art("四季之歌", "春，喚醒了露珠中沉睡的精靈，如彩蝶翩翩穿梭嫩芽新綠間，搖起片片飛絮漫舞風中。 夏雷如雄獅吼出牠的熱情，金色光芒飛奔蔚藍天際，隆隆高歌生命無限。 秋風翦翦吹來清涼，翻動詩篇蕭索如落葉沙沙，在水晶般透明的秋氣中。 冬之聖堂管風琴合唱起讚美歌，詠嘆生命美好，祈禱聲中似聞嬰兒伊呀，見證了另一個生命的起始。 長廊上的彩色玻璃以脈搏般的節奏，伴隨光效的拍子，引導人們用自然的步伐，走過春夏秋冬四季更替的生命之旅。", 1)
            Art("四季之歌", "春，喚醒了露珠中沉睡的精靈，如彩蝶翩翩穿梭嫩芽新綠間，搖起片片飛絮漫舞風中。 夏雷如雄獅吼出牠的熱情，金色光芒飛奔蔚藍天際，隆隆高歌生命無限。 秋風翦翦吹來清涼，翻動詩篇蕭索如落葉沙沙，在水晶般透明的秋氣中。 冬之聖堂管風琴合唱起讚美歌，詠嘆生命美好，祈禱聲中似聞嬰兒伊呀，見證了另一個生命的起始。 長廊上的彩色玻璃以脈搏般的節奏，伴隨光效的拍子，引導人們用自然的步伐，走過春夏秋冬四季更替的生命之旅。", 1)
            Art("四季之歌", "春，喚醒了露珠中沉睡的精靈，如彩蝶翩翩穿梭嫩芽新綠間，搖起片片飛絮漫舞風中。 夏雷如雄獅吼出牠的熱情，金色光芒飛奔蔚藍天際，隆隆高歌生命無限。 秋風翦翦吹來清涼，翻動詩篇蕭索如落葉沙沙，在水晶般透明的秋氣中。 冬之聖堂管風琴合唱起讚美歌，詠嘆生命美好，祈禱聲中似聞嬰兒伊呀，見證了另一個生命的起始。 長廊上的彩色玻璃以脈搏般的節奏，伴隨光效的拍子，引導人們用自然的步伐，走過春夏秋冬四季更替的生命之旅。", 1)
            Art("四季之歌", "春，喚醒了露珠中沉睡的精靈，如彩蝶翩翩穿梭嫩芽新綠間，搖起片片飛絮漫舞風中。 夏雷如雄獅吼出牠的熱情，金色光芒飛奔蔚藍天際，隆隆高歌生命無限。 秋風翦翦吹來清涼，翻動詩篇蕭索如落葉沙沙，在水晶般透明的秋氣中。 冬之聖堂管風琴合唱起讚美歌，詠嘆生命美好，祈禱聲中似聞嬰兒伊呀，見證了另一個生命的起始。 長廊上的彩色玻璃以脈搏般的節奏，伴隨光效的拍子，引導人們用自然的步伐，走過春夏秋冬四季更替的生命之旅。", 1)
        },
        TabInfo("2館") {

        }
    )
    var tabIndex by remember {
        mutableStateOf(0)
    }

    Scaffold(scaffoldState = rememberScaffoldState(), bottomBar = {
        TabRow(selectedTabIndex = tabIndex, backgroundColor = Color.White, contentColor = Color(0xFF006182)) {
            arts.forEachIndexed { index, tabInfo ->
                Tab(selected = index == tabIndex, onClick = { tabIndex = index }) {
                    Text(tabInfo.title)
                }
            }
        }
    }) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState(), true)) {
            arts[tabIndex].content()
        }
    }
}

@Composable
fun Art(name: String, content: String, imageId: Int) {
    Column(modifier = Modifier.padding(20.dp)) {
        Text("作品名: $name", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(5.dp))
        Divider()
        Spacer(modifier = Modifier.height(5.dp))
        Text(content)
    }
}

@Composable
fun Contact() {

}

@Preview(showBackground = true)
@Composable
fun PreviewInfoPage() {
    ExerciseAndroidTheme {
        InfoPage()
    }
}