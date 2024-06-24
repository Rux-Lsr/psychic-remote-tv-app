package com.ict.tvremoteapp

import MQTTClient
import android.util.JsonReader
import mqtt.MQTTVersion
import mqtt.Subscription
import mqtt.packets.Qos
import mqtt.packets.mqttv5.SubscriptionOptions

class HisensenseManager(private val ipAdress:String) {
    @OptIn(ExperimentalUnsignedTypes::class)
    private val client =  MQTTClient(
        MQTTVersion.MQTT5,
        "tcp://$ipAdress",
        36669,
        null,
        userName = "hisenseservice",
        password = "multimqttservice".encodeToByteArray().toUByteArray()
    ) {
        println("Affichage du pupplish"+it.payload?.toByteArray()?.decodeToString())
    }
    init {
        client.run()
    }


    fun sendCommand(topic:String, msg:String){
        client.publish(false, Qos.EXACTLY_ONCE, topic, msg.encodeToByteArray().toUByteArray())
    }

    fun disconnect(){
    }
}
