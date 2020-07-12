package com.example.skilltest.presenter

import android.content.Context

import androidx.core.hardware.fingerprint.FingerprintManagerCompat
import androidx.core.os.CancellationSignal


object FingerprintHelper {

    private lateinit var fingerprintManager: FingerprintManagerCompat
    private var mCancellationSignal: CancellationSignal? = null
    fun init(ctx: Context) {
        fingerprintManager = FingerprintManagerCompat.from(ctx)
    }

    fun hasEnrolledFingerprints(): Boolean {
        return fingerprintManager.hasEnrolledFingerprints()
    }


    fun isHardwareDetected(): Boolean {
        return fingerprintManager.isHardwareDetected
    }


    fun authenticate(callback: FingerprintManagerCompat.AuthenticationCallback) {
        mCancellationSignal = CancellationSignal()
        fingerprintManager.authenticate(null, 0, mCancellationSignal, callback, null)
    }



    fun cancel(){
        mCancellationSignal?.cancel()
    }
}