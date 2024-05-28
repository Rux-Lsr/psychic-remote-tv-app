package com.ict.tvremoteapp

import org.eclipse.paho.mqttv5.client.IMqttDeliveryToken
import org.eclipse.paho.mqttv5.client.IMqttToken
import org.eclipse.paho.mqttv5.client.MqttCallback
import org.eclipse.paho.mqttv5.client.MqttClient
import org.eclipse.paho.mqttv5.client.MqttConnectionOptions
import org.eclipse.paho.mqttv5.client.MqttDisconnectResponse
import org.eclipse.paho.mqttv5.common.MqttException
import org.eclipse.paho.mqttv5.common.MqttMessage
import org.eclipse.paho.mqttv5.common.packet.MqttProperties

class HisenseController(private val tvIpAddress: String) {
    private val serverURI = "tcp://$tvIpAddress:36669"
    private val client = MqttClient(serverURI, null)

    init {
        val options = MqttConnectionOptions()
        options.userName = "hisenseservice"
        options.password = "multimqttservice".encodeToByteArray()

        client.setCallback(object : MqttCallback {
            override fun connectComplete(reconnect: Boolean, serverURI: String) {
                println("Connected to MQTT broker")
            }

            override fun authPacketArrived(reasonCode: Int, properties: MqttProperties?) {

            }

            fun connectionLost(cause: Throwable) {
                println("Connection to MQTT broker lost")
            }

            override fun disconnected(disconnectResponse: MqttDisconnectResponse?) {

            }

            override fun mqttErrorOccurred(exception: MqttException?) {
            }

            override fun messageArrived(topic: String, message: MqttMessage) {
                println("Received message from topic $topic: ${message.toString()}")
            }

            override fun deliveryComplete(token: IMqttToken?) {
                println("Delivery complete")
            }

        })
        client.connect(options)
    }
    fun sendCommand(topic: String, message: String) {
        val mqttMessage = MqttMessage(message.toByteArray())
        client.publish(topic, mqttMessage)
    }

    fun authenticate(authCode: String) {
        val topic = "/remoteapp/tv/ui_service/HomeAssistant/actions/authenticationcode"
        val message = "{ \"authNum\": $authCode }"
        sendCommand(topic, message)
    }

}