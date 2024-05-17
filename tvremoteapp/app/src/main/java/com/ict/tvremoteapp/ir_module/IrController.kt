package com.ict.tvremoteapp.ir_module

import android.content.Context
import android.hardware.ConsumerIrManager



class IrController(context: Context) {

    private val irManager = context.getSystemService(Context.CONSUMER_IR_SERVICE) as? ConsumerIrManager

    fun isIrSupported(): Boolean {
        return irManager?.hasIrEmitter() ?: false
    }

    fun signal(frequency: Int, pattern: IntArray) {
        irManager?.transmit(frequency, pattern)
    }
}