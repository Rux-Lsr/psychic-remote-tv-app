package com.ict.tvremoteapp

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import java.io.File
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun HisenseRemoteScreen()
{
    val hisenseController = HisenseController("192.168.8.100")
    try{
        val file = File("commands.json")
        val jsonString = file.readText()
        val commands = Gson().fromJson(jsonString, Command::class.java)
        Log.d("commades", "debut")
        println(commands)
    }catch (e:Exception){
        e.message?.let { Log.d("une exception: ", it) }
    }
    var showDialog by remember {
        mutableStateOf(false);
    }
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {

        Column(
            verticalArrangement = Arrangement.spacedBy((-13).dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Une commande")
            Spacer(modifier = Modifier.height(30.dp))
            Button(onClick = {

            }) {
                Text("V+")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    showDialog = !showDialog

                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier.padding(12.dp))
            {
                Text("Connect", color = Color.White)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
            }) {
                Text("V-")
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(40.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Button(onClick = {

            }) {
                Text("Ch-")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {

            }) {
                Text("Ch+")
            }
        }
        if(showDialog) MyAlertDialog();
    }

}

@Composable
fun MyAlertDialog() {
    var text by remember { mutableStateOf("") } // Variable pour stocker le texte saisi
    var showDialog by remember { mutableStateOf(false) } // État pour contrôler l'affichage de la boîte de dialogue

    // Fonction pour afficher la boîte de dialogue
    fun showDialog() {
        showDialog = true
    }

    // Fonction pour fermer la boîte de dialogue
    fun dismissDialog() {
        showDialog = false
    }

    // Fonction pour traiter le texte saisi
    fun handleText() {
        // Traitez le texte saisi ici (par exemple, l'enregistrer, le valider)
        println("Texte saisi: $text")
        dismissDialog() // Fermez la boîte de dialogue
    }

    // Contenu de la boîte de dialogue
    AlertDialog(
        onDismissRequest = { dismissDialog() }, // Fonction à appeler lorsque l'utilisateur demande la fermeture
        title = { Text("Saisissez 4 caractères") }, // Titre de la boîte de dialogue
        text = {
            TextField(value = text, onValueChange = {newVal -> text = newVal}, label = { Text(text = "entrer les 4 digits")})
        },
        confirmButton = {
            Button(onClick = { handleText() }) {
                Text("Valider")
            }
        },
        dismissButton = {
            Button(onClick = { dismissDialog() }) {
                Text(text = "annuler")
            }
        }
    )

    if (showDialog) {
        // Afficher la boîte de dialogue
        MyAlertDialog()
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun prevThings(){
    HisenseRemoteScreen()
}