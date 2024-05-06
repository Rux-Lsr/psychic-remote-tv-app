package com.ict.tvremoteapp.ir_module

import android.content.Context
import android.hardware.ConsumerIrManager


class IrController(context: Context) {
     val irManager = context.getSystemService(Context.CONSUMER_IR_SERVICE) as ConsumerIrManager



    fun isIrSupported() : Boolean{

        return if(irManager.hasIrEmitter())
            true
        else
            false
    }


}
