package com.ict.tvremoteapp

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TvRemoteAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "G2-Remote App")
                        },
                        navigationIcon = {
                            IconButton(onClick = { /* Handle navigation */ }) {
                                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
                            }
                        }
                    )
                }) {
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
                    setText("up sound")
                    volumeUP(irController)
                }else{
                    setText("Oups!!! Pas d'Ir sur votre telephone")
                    Log.d("Ir-updound",": Faux")
                }
            }) {
                Text("V+")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if(irController.isIrSupported()){
                        setText("Power tv")
                        Log.d("Ir-power",": Vrai")
                        powerOff(irController)
                    }else{
                        setText("Oups!!! Pas d'Ir sur votre telephone")
                        Log.d("Ir-power",": Faux")
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier.padding(12.dp))
            {
                Text("Power", color = Color.White)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {

                if(irController.isIrSupported()){
                    setText("down sound")
                    Log.d("Ir-downsound",": Vrai")
                    volumedown(irController)
                }else{
                    setText("Oups!!! Pas d'Ir sur votre telephone")
                    Log.d("Ir",": Faux")
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
                    setText("Up chanel")
                    Log.d("Ir",": Vrai")
                    chainedown(irController)
                }else{
                    setText("Oups!!! Pas d'Ir sur votre telephone")
                    Log.d("Ir-downchanel",": Faux")
                }
            }) {
                Text("Ch-")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {
                if(irController.isIrSupported()){
                    setText("down chanel")
                    Log.d("Ir-upchanel",": Vrai")
                    chaineup(irController)
                }else{
                    setText("Oups!!! Pas d'Ir sur votre telephone")
                    Log.d("Ir-upchanel",": Faux")
                }
            }) {
                Text("Ch+")
            }
        }
    }
}
fun powerOff(irManager: IrController) {
    val samsungPowerOffPattern = intArrayOf(0, 109, 34, 3, 4444, 4418, 552, 1657, 552, 1657, 552, 1657, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 1657, 552, 1657, 552, 1657, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 1657, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 1683, 552, 552, 552, 1657, 552, 1657, 552, 1657, 552, 1657, 552, 1657, 552, 1657, 552, 47175, 4444, 4418, 552, 552, 552, 97137)
    try {
        irManager.irManager.transmit(38029,samsungPowerOffPattern)
        Log.d("powerOff", "signal sent")
    }   catch ( e:Exception){
        e.message?.let { Log.d("powerOff", it) }
    }
}
fun volumeUP(irManager: IrController) {
    val samsungVolumeUpPattern = intArrayOf(0, 109, 34, 3, 4444, 4418, 552, 1657, 552, 1657, 552, 1657, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 1657, 552, 1657, 552, 1657, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 1657, 552, 1657, 552, 1657, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 1657, 552, 1657, 552, 1657, 552, 1657, 552, 1657, 552, 47175, 4444, 4418, 552, 552, 552, 97137)
    try {
        irManager.irManager.transmit(38029,samsungVolumeUpPattern)
        Log.d("powerOff", "signal sent")
    }   catch ( e:Exception){
        e.message?.let { Log.d("powerOff", it) }
    }
}

fun volumedown(irManager: IrController) {
    val samsungVolumeDowPattern = intArrayOf(0, 109, 34, 3, 4444, 4418, 552, 1657, 552, 1657, 552, 1657, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 1657, 552, 1657, 552, 1657, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 1657, 552, 1657, 552, 552, 552, 1657, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 1657, 552, 552, 552, 1657, 552, 1657, 552, 1657, 552, 1657, 552, 47175, 4444, 4418, 552, 552, 552, 97137)
    try {
        irManager.irManager.transmit(38029,samsungVolumeDowPattern)
        Log.d("powerOff", "signal sent")
    }   catch ( e:Exception){
        e.message?.let { Log.d("powerOff", it) }
    }
}
fun chaineup(irManager: IrController) {
    val samsungVolumeDowPattern = intArrayOf(0, 109, 34, 3, 4444, 4418, 552, 1657, 552, 1657, 552, 1657, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 1657, 552, 1657, 552, 1657, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 1657, 552, 552, 552, 552, 552, 1657, 552, 552, 552, 552, 552, 552, 552, 1657, 552, 552, 552, 1657, 552, 1657, 552, 552, 552, 1683, 552, 1657, 552, 1657, 552, 47175, 4444, 4418, 552, 552, 552, 97137)
    try {
        irManager.irManager.transmit(38029,samsungVolumeDowPattern)
        Log.d("powerOff", "signal sent")
    }   catch ( e:Exception){
        e.message?.let { Log.d("powerOff", it) }
    }
}
fun chainedown(irManager: IrController) {
    val samsungVolumeDowPattern = intArrayOf(0, 109, 34, 3, 4444, 4418, 552, 1657, 552, 1657, 552, 1657, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 1657, 552, 1657, 552, 1657, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 552, 1657, 552, 552, 552, 552, 552, 552, 552, 1657, 552, 1657, 552, 1657, 552, 1657, 552, 552, 552, 1657, 552, 1657, 552, 1657, 552, 47175, 4444, 4418, 552, 552, 552, 97137)
    try {
        irManager.irManager.transmit(38029,samsungVolumeDowPattern)
        Log.d("powerOff", "signal sent")
    }   catch ( e:Exception){
        e.message?.let { Log.d("powerOff", it) }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    MyApp()
}