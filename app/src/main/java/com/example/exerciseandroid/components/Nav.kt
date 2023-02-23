package com.example.exerciseandroid.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.exerciseandroid.R
import com.example.exerciseandroid.ui.theme.ExerciseAndroidTheme
import kotlinx.coroutines.launch

data class NavMenuData(val icon: ImageVector, val title: String, val router: String)

@Composable
fun DrawerContent(nav: NavController, navList: List<NavMenuData>, state: ScaffoldState) {
    val current by nav.currentBackStackEntryAsState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(56.dp)
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

            Text("123", color = Color.Green)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Column {
            navList.forEach { navItem ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                        .clickable {
                            nav.navigate(navItem.router)
                            scope.launch { state.drawerState.close() }
                        },
                    shape = RoundedCornerShape(10.dp),
                    backgroundColor = if (current?.destination?.route == navItem.router) Color.Blue else Color.White
                ) {
                    Row {
                        Icon(imageVector = navItem.icon, contentDescription = null)

                        Spacer(modifier = Modifier.width(20.dp))

                        Text(navItem.title)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DrawerContentPreview() {
    val nav = rememberNavController()
    val state = rememberScaffoldState()
    ExerciseAndroidTheme {
        DrawerContent(
            nav, listOf(
                NavMenuData(Icons.Default.List, "List", "list")
            ), state
        )
    }
}