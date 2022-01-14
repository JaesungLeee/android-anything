package com.jslee.simplecustomview

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jslee.simplecustomview.databinding.ActivityMainBinding
import com.jslee.simplecustomview.enums.ImageButtonState

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private var state = ImageButtonState.BEFORE_RECORDING

    private val requiredPermissions = arrayOf(Manifest.permission.RECORD_AUDIO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        requestAudioPermission()
        initViews()

    }

    private fun requestAudioPermission() {
        requestPermissions(requiredPermissions, REQUEST_RECORD_AUDIO_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        val audioRecordPermissionGranted =
            requestCode == REQUEST_RECORD_AUDIO_CODE &&
                    grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED

        if (!audioRecordPermissionGranted) {
            finish()
            Log.e("PERMISSION", "NOT ALLOWED")
        }
    }

    private fun initViews() {
        binding.recordButton.updateIconWithState(state)
    }

    companion object {
        private const val REQUEST_RECORD_AUDIO_CODE = 201
    }
}