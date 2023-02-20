package com.example.exerciseandroid

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
    val navList = listOf(
        NavMenuData(Icons.Default.Home, "Home", "home"),
        NavMenuData(Icons.Default.List, "List", "list")
    )

    Scaffold(
        scaffoldState = state,
        topBar = {
            TopBar {
                scope.launch { state.drawerState.open() }
            }
        },
        drawerContent = { DrawerContent(nav, navList, state) }
    ) {
        MainScreenContent(nav)
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
fun MainScreenContent(nav: NavHostController) {
    NavHost(navController = nav, startDestination = "home") {
        composable("home") { Home(nav) }
        composable("list") { List() }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ExerciseAndroidTheme {
        MainScreen()
    }
}

