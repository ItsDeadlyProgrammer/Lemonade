package com.example.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                  LemonadeAppTheme {
                      Lemonade(modifier = Modifier.padding(innerPadding))
                  }
                }
            }
        }
    }
}

@Composable
fun Lemonade(modifier: Modifier = Modifier.wrapContentSize(Alignment.Center).padding(top = 20.dp)){

    var result by remember { mutableIntStateOf(1) }
    var squeezeCount = (2..4).random()
    val imageResource = when(result) {
        1 -> R.drawable.lemon_tree
        in 2..4 -> R.drawable.lemon_squeeze
        5 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val cd = when(result){
        1 -> R.string.cd1
        in 2..4 -> R.string.cd2
        5 -> R.string.cd3
        else -> R.string.cd4
    }

    val step = when(result){
        1 -> R.string.step1
        in 2..4 -> R.string.step2
        5 -> R.string.step3
        else -> R.string.step4
    }

    Text(

        text = stringResource(R.string.app_name),
        textAlign = TextAlign.Center,
        fontWeight = Bold,
        color = Color.DarkGray,
        modifier = modifier.fillMaxWidth().background(color = Color.Yellow).height(30.dp)

    )
    Box(modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(onClick = {
                if( result >=5 || result<=1)  result = (result + 1)%6
                else {
                            squeezeCount--
                        if (squeezeCount==0) result = 5
                }
                } ,
                shape = RoundedCornerShape(35.dp))
            {
                Image(
                    painter = painterResource(imageResource),
                    contentDescription = stringResource(cd),

                    )
            }


            Spacer(modifier.padding(16.dp))

            Text(
                text = stringResource(step),
                fontSize = 18.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeAppTheme {
        Lemonade(modifier = Modifier)
    }
}