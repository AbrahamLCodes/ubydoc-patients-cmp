package com.personal.cmptests.composetests.features.maintabs.ProfileTabScreen.authmodalscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.personal.cmptests.composetests.features.maintabs.ProfileTabScreen.authmodalscreen.subviews.RecoverPasswordView
import com.personal.cmptests.composetests.features.maintabs.ProfileTabScreen.authmodalscreen.subviews.SignInView
import com.personal.cmptests.composetests.features.maintabs.ProfileTabScreen.authmodalscreen.subviews.VerifyEmailView
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft

@Composable
fun AuthModalScreen(
    onClose: () -> Unit,
    onSignedIn: () -> Unit,
) {
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var seePass by remember { mutableStateOf(false) }

    var showVerifyEmail by remember { mutableStateOf(false) }
    var showRecoverPassword by remember { mutableStateOf(false) }

    var verifyCode by remember { mutableStateOf("") }
    var recoveryEmail by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF639AEE))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = FontAwesomeIcons.Solid.ArrowLeft,
                    contentDescription = "Regresar",
                    tint = Color.White,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onClose() }
                )
            }

            when {
                showVerifyEmail -> VerifyEmailView(
                    email = "usuario@correo.com",
                    code = verifyCode,
                    onCodeChange = { verifyCode = it },
                    onVerify = { /* TODO */ },
                    onResend = { /* TODO */ },
                    onBack = { showVerifyEmail = false }
                )

                showRecoverPassword -> RecoverPasswordView(
                    email = recoveryEmail,
                    onEmailChange = { recoveryEmail = it },
                    onRecover = { /* TODO */ },
                    onCancel = { showRecoverPassword = false }
                )

                else -> SignInView(
                    phone = phone,
                    password = password,
                    seePass = seePass,
                    onPhoneChange = { phone = it },
                    onPasswordChange = { password = it },
                    onToggleSeePass = { seePass = !seePass },
                    onSignIn = { onSignedIn() },
                    onGoToSignUp = { /* TODO: open sign up modal */ },
                    onForgotPassword = { showRecoverPassword = true }
                )
            }
        }
    }
}