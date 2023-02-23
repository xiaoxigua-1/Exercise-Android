package com.example.exerciseandroid.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.Room
import com.example.exerciseandroid.util.TicketDatabase

data class TicketData(val name: String, val price: Int, val des: String)

data class UserInfo(val name: String, val email: String, val phone: String)

@Composable
fun Buy(db: TicketDatabase) {
    var userInfo by remember {
        mutableStateOf(UserInfo("", "", ""))
    }
    val ticketList = listOf(
        TicketData(
            "Test1",
            200,
            "許四維專賣店 abc test aaa pasd sssd asd asdq asd aasd asd xaxaasd aadw aas ddaasd asd asd asdasdad"
        ),
        TicketData("Test1", 200, "許四維專賣店"),
        TicketData("Test1", 200, "許四維專賣店"),
        TicketData("Test1", 200, "許四維專賣店"),
        TicketData("Test1", 200, "許四維專賣店"),
    )
    var buy: TicketData? by remember {
        mutableStateOf(null)
    }

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(ticketList) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(10.dp)
                    .clickable {
                        buy = it
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    modifier = Modifier
                        .width(80.dp)
                        .height(80.dp)
                ) {
                    // image
                }

                Spacer(modifier = Modifier.width(20.dp))
                Column(modifier = Modifier.weight(4f)) {
                    Text(text = it.name)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        it.des, color = Color.Gray,

                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    "${it.price}$",
                    textAlign = TextAlign.End,
                )
            }
            Divider()
        }
    }

    if (buy != null) {
        AlertDialog(
            onDismissRequest = {
                buy = null
            },
            title = { Text("購買${buy?.name ?: ""}") },
            text = {
                Column {
                    Text(buy?.des ?: "")
                    TextField(
                        value = userInfo.name, onValueChange = {
                            userInfo = userInfo.copy(name = it)
                        },
                        label = { Text("Name") }
                    )
                    TextField(
                        value = userInfo.email, onValueChange = {
                            userInfo = userInfo.copy(email = it)
                        },
                        label = { Text("Email") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                    )
                    TextField(
                        value = userInfo.phone, onValueChange = {
                            userInfo = userInfo.copy(phone = it)
                        },
                        label = { Text("Phone number") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        buy = null
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)
                ) {
                    Text("取消")
                }
            },
            confirmButton = {
                Button(onClick = {
                    // buy
                }) {
                    Text("購買")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBuyPage() {
    Buy(
        db = Room.databaseBuilder(
            LocalContext.current,
            TicketDatabase::class.java,
            "db"
        ).build()
    )
}