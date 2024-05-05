package com.ict.tvremoteapp.ir_module

import android.content.Context
import android.hardware.ConsumerIrManager


class IrController(context: Context) {
    private val irManager = context.getSystemService(Context.CONSUMER_IR_SERVICE) as ConsumerIrManager

    fun isIrSupported() : Boolean{

        if(irManager.hasIrEmitter())
            return true
        else
            return false
    }

    fun sendIrSignal(frequency: Int, pattern: IntArray?) {
        if (isIrSupported()) {
            irManager.transmit(frequency, pattern)
        }
    }
}
