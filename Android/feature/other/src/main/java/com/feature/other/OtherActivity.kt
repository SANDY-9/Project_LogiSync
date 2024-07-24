package com.feature.other

import android.graphics.Color
import androidx.biometric.BiometricPrompt
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.core.desinsystem.theme.LogiSyncTheme
import com.core.domain.usecases.auth.RegisterFingerPrintUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.concurrent.Executor
import javax.inject.Inject

@AndroidEntryPoint
class OtherActivity : AppCompatActivity() {

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    @Inject
    lateinit var registerFingerPrintUseCase: RegisterFingerPrintUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window?.statusBarColor = Color.WHITE
        setContent {
            LogiSyncTheme {
                OtherScreen(
                    onRegisterFingerPrint = {
                        biometricPrompt.authenticate(promptInfo)
                    },
                    onNavigateUp = {
                        finish()
                    },
                    onInitConnect = {

                    },
                )
            }
        }

        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int,
                    errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(applicationContext, "에러가 발생했습니다.", Toast.LENGTH_SHORT).show()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    lifecycleScope.launch {
                        registerFingerPrintUseCase()
                    }
                    Toast.makeText(applicationContext, "성공적으로 지문을 등록했습니다.", Toast.LENGTH_SHORT).show()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(applicationContext, "지문인식에 실패 했습니다", Toast.LENGTH_SHORT).show()
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("생체 인증 로그인 등록")
            .setSubtitle("현재 계정에 생체 인증 정보를 등록합니다.")
            .setDescription("지문을 등록해주세요.")
            .setNegativeButtonText("취소")
            .build()
    }
}
