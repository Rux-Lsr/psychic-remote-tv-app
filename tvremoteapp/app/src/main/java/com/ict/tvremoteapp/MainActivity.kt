package com.ict.tvremoteapp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ict.tvremoteapp.ir_module.IrController
import com.ict.tvremoteapp.ui.theme.TvRemoteAppTheme

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TvRemoteAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    val irController = IrController(LocalContext.current)
    val (report, setText) = remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {

        Column(
            verticalArrangement = Arrangement.spacedBy((-13).dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(report)
            Spacer(modifier = Modifier.height(30.dp))
            Button(onClick = {
                if(irController.isIrSupported()){
                    setText("Ir sur votre telephone")
                    Log.d("Ir",": Vrai");
                }else{
                    setText("Pas d'Ir sur votre telephone")
                    Log.d("Ir",": Faux");
                }
            }) {
                Text("V+")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if(irController.isIrSupported()){
                        setText("Ir sur votre telephone")
                        Log.d("Ir",": Vrai");
                    }else{
                        setText("Pas d'Ir sur votre telephone")
                        Log.d("Ir",": Faux");
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier.padding(12.dp))
            {
                Text("Power", color = Color.White,)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                if(irController.isIrSupported()){
                    setText("Ir sur votre telephone")
                    Log.d("Ir",": Vrai");
                }else{
                    setText("Pas d'Ir sur votre telephone")
                    Log.d("Ir",": Faux");
                }
            }) {
                Text("V-")
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(40.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Button(onClick = {
                if(irController.isIrSupported()){
                    setText("Ir sur votre telephone")
                    Log.d("Ir",": Vrai");
                }else{
                    setText("Pas d'Ir sur votre telephone")
                    Log.d("Ir",": Faux");
                }
            }) {
                Text("Ch-")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {
                if(irController.isIrSupported()){
                    setText("Ir sur votre telephone")
                    Log.d("Ir",": Vrai");
                }else{
                    setText("Pas d'Ir sur votre telephone")
                    Log.d("Ir",": Faux");
                }
            }) {
                Text("Ch+")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    MyApp()
}