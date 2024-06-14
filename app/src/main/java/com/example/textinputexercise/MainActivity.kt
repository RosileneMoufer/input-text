package com.example.textinputexercise

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionContext
import androidx.compose.runtime.CompositionLocalContext
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.textinputexercise.ui.theme.TextInputExerciseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TextInputExerciseTheme {
                PageWithScaffold()
            }
        }
    }
}

@Composable
fun PageWithScaffold(){
    var userName by remember { mutableStateOf("Usuário") }
    var newUserNameInput by remember { mutableStateOf("") }

    Scaffold(
        topBar = {TopBar(userName)},
        modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            val context = LocalContext.current

            OutlinedTextField(
                value = newUserNameInput,
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 20.dp),
                onValueChange = {
                    newUserNameInput = it
                },
                placeholder = { Text(text = "Digite seu nome") },
                suffix = {
                    IconButton(onClick = {
                        val stringTrimmed = newUserNameInput.trim()
                        if(stringTrimmed != ""){
                            if (!isThereANumber(stringTrimmed))
                                userName = newUserNameInput
                            else
                                Toast.makeText(context, "Somente são aceitos caracteres alfabéticos",
                                    Toast.LENGTH_LONG).show()
                        }
                        else{
                            Toast.makeText(context, "Não pode estar vazio",
                                Toast.LENGTH_LONG).show()
                        }
                    }) {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
                    }
                },
            )
        }
    }
}

fun isThereANumber(string:String):Boolean{
    val regex = "\\d".toRegex()
    return regex.containsMatchIn(string)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(name:String, modifier: Modifier = Modifier){
    TopAppBar(
        title = {
            Column(
            ) {
                Text(
                    text = "Hi, $name \uD83D\uDC4B",
                    fontFamily = FontFamily(
                        Font(R.font.montserrat_semibold)
                        ),
                    color = Color(0xFF2F2F2F),
                    fontSize = 30.sp
                    )

                Text(
                    text = "Explore the world",
                    fontFamily = FontFamily(
                        Font(R.font.inter_medium)
                    ),
                    color = Color(0xFF888888),
                    fontSize = 20.sp
                )
            }
        },
        actions = {
            IconButton(
                onClick = { /*TODO*/ },
                Modifier.size(50.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.my_profile_picture),
                    contentDescription = null

                )
            }
        },
        modifier = Modifier.padding(10.dp, 20.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun ViewPage(){
    PageWithScaffold()
}
