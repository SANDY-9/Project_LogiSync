package com.feature.login.loginscreen

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import java.util.concurrent.Executor

private lateinit var executor: Executor
private lateinit var biometricPrompt: BiometricPrompt
private lateinit var promptInfo: BiometricPrompt.PromptInfo

fun requestBioLogin(
    activity: AppCompatActivity,
    onSuccess: () -> Unit,
) {
    executor = ContextCompat.getMainExecutor(activity)
    biometricPrompt = BiometricPrompt(activity, executor,
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(
                errorCode: Int,
                errString: CharSequence
            ) {
                super.onAuthenticationError(errorCode, errString)
                Toast.makeText(activity, "에러가 발생했습니다.", Toast.LENGTH_SHORT).show()
            }

            override fun onAuthenticationSucceeded(
                result: BiometricPrompt.AuthenticationResult
            ) {
                super.onAuthenticationSucceeded(result)
                Toast.makeText(activity, "지문인증에 성공했습니다.", Toast.LENGTH_SHORT).show()
                onSuccess()
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Toast.makeText(activity, "지문인식에 실패 했습니다", Toast.LENGTH_SHORT).show()
            }
        })

    promptInfo = BiometricPrompt.PromptInfo.Builder()
        .setTitle("생체 인증 로그인")
        .setSubtitle("현재 기기 지문에 등록된 계정으로 로그인 합니다.")
        .setDescription("지문을 입력해주세요.")
        .setNegativeButtonText("취소")
        .build()

    biometricPrompt.authenticate(promptInfo)
}
