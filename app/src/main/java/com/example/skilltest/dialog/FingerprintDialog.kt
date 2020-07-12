package com.example.skilltest.dialog

import android.app.Service
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.hardware.fingerprint.FingerprintManagerCompat
import com.example.skilltest.R
import com.example.skilltest.presenter.FingerprintHelper
import com.example.skilltest.views.MainScreen
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_fingerprint.*


class FingerprintDialog : BottomSheetDialogFragment() {
    private lateinit var mVibrator: Vibrator
    private lateinit var fingerImageView: ImageView
    private lateinit var hintTv: TextView
    private lateinit var btnCancel: TextView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mVibrator = context!!.getSystemService(Service.VIBRATOR_SERVICE) as Vibrator
        return inflater.inflate(R.layout.dialog_fingerprint, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fingerImageView = view.findViewById(R.id.fingerprint_image)
        hintTv = view.findViewById(R.id.hint_tv)
        btnCancel = view.findViewById(R.id.btn_cancel)
        btnCancel.setOnClickListener { dismiss() }
        FingerprintHelper.authenticate(object : FingerprintManagerCompat.AuthenticationCallback() {

            //在识别指纹成功时调用。
            override fun onAuthenticationSucceeded(result: FingerprintManagerCompat.AuthenticationResult?) {
                startVibrate()
                hintTv.text = "Fingerprint recognition success"
                fingerImageView.setImageResource(R.drawable.ic_interfaceselect)
                hintTv.setTextColor(context!!.resources.getColor(R.color.colorPrimary))
//                hintTv.postDelayed({ dismiss() }, 1000)
                fingerprint_image.setImageResource(R.drawable.ic_interfacegreen)
              val intent=Intent(activity,MainScreen::class.java)
                startActivity(intent)
                activity!!.finish()

            }

            //当指纹有效但未被识别时调用。
            override fun onAuthenticationFailed() {
                hintTv.text = "Recognition failed, please try again"
                fingerprint_image.setImageResource(R.drawable.ic_interfaceselect)
                Toast.makeText(
                        activity,
                       "Praroop",
                        Toast.LENGTH_LONG
                ).show()
                startVibrate()
            }

            //当遇到不可恢复的错误并且操作完成时调用。
            override fun onAuthenticationError(errMsgId: Int, errString: CharSequence?) {
                hintTv.text = errString
                Toast.makeText(
                        activity,
                        "Error",
                        Toast.LENGTH_LONG
                ).show()
                if (errMsgId != 5) {   //取消不震动
                    startVibrate()
                }
            }

            //在认证期间遇到可恢复的错误时调用。
            override fun onAuthenticationHelp(helpMsgId: Int, helpString: CharSequence?) {
                hintTv.text = helpString
                startVibrate()
            }
        })
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog!!)
        FingerprintHelper.cancel()
    }

    fun startVibrate() {
        mVibrator.vibrate(500)
    }
}