package com.example.skilltest.views

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle

import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.skilltest.R
import com.example.skilltest.dialog.FingerprintDialog
import com.example.skilltest.presenter.FingerprintHelper

class FingurePrintActivity : AppCompatActivity() {


    private lateinit var mSDKVersionTv: TextView
    private lateinit var mHasPermissionTv: TextView
    private lateinit var mCheckBtn: Button
    private lateinit var mStartBtn: Button
    private var hasPermission = true
    private lateinit var mDialog: FingerprintDialog

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finfure)
        mSDKVersionTv = findViewById(R.id.tv_sdk_version)
        mHasPermissionTv = findViewById(R.id.tv_fingerprint_permission)
        mCheckBtn = findViewById(R.id.btn_requires_permission)
        mStartBtn = findViewById(R.id.btn_fingerprint)

        mSDKVersionTv.text = "System version:：${Build.VERSION.SDK_INT}"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hasPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) == PackageManager.PERMISSION_GRANTED
            mHasPermissionTv.text = "Is there fingerprint permission：$hasPermission"
        } else {
            hasPermission = true
            mHasPermissionTv.text = "Whether there is fingerprint permission: M(23-6.0) below does not need runtime permission"
        }

        mCheckBtn.setOnClickListener {
            if (!hasPermission && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.USE_FINGERPRINT), 0x99)
            } else {
                Toast.makeText(applicationContext, "Fingerprint permission has been obtained", Toast.LENGTH_SHORT).show()
            }
        }
        mDialog = FingerprintDialog()
        FingerprintHelper.init(this)
        mStartBtn.setOnClickListener {
            if (FingerprintHelper.isHardwareDetected()) {
                if (FingerprintHelper.hasEnrolledFingerprints()) {
                    mDialog.show(supportFragmentManager, "dialog")
                } else {
                    Toast.makeText(applicationContext, "You have not recorded any fingerprints", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(applicationContext, "Your phone does not support fingerprint recognition", Toast.LENGTH_SHORT).show()
            }
        }
        mDialog.show(supportFragmentManager, "dialog")
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0x99) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(applicationContext, "Fingerprint permission has been obtained", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Fingerprint access denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
