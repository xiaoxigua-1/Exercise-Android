package com.example.exerciseandroid

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.exerciseandroid.ui.theme.ExerciseAndroidTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExerciseAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val nav = rememberNavController()
    val state = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = state,
        topBar = {
            TopBar {
                scope.launch { state.drawerState.open() }
            }
        },
        drawerContent = { DrawerContent(nav) }
    ) {
        MainScreenContent()
    }
}

@Composable
fun TopBar(setOpenNav: () -> Unit) {
    TopAppBar(
        title = { Text(text = "南港展覽館導覽") },
        navigationIcon = {
            IconButton(onClick = setOpenNav) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
fun DrawerContent(nav: NavController) {
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
                Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = null)
                Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = null)
            }

            Text("123")
        }
    }
}

@Composable
fun MainScreenContent() {

}

@Preview
@Composable
fun DrawerContentPreview() {
    val nav = rememberNavController()
    ExerciseAndroidTheme {
        DrawerContent(nav)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ExerciseAndroidTheme {
        MainScreen()
    }
}

