package com.personal.cmptests.composetests.features.maintabs.ProfileTabScreen.authmodalscreen.subviews

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun VerifyEmailView(
    email: String,
    code: String,
    onCodeChange: (String) -> Unit,
    onVerify: () -> Unit,
    onResend: () -> Unit,
    onBack: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            KamelImage(
                resource = asyncPainterResource("drawable/logo-tipo.png"),
                contentDescription = "Logo",
                modifier = Modifier.size(150.dp),
                contentScale = ContentScale.Fit
            )

//            Image(
//                painter = painterResource("logo_tipo.png"), // Path relative to resources folder
//                contentDescription = "Logo",
//                modifier = Modifier.size(150.dp)
//            )

            Text("Verificación de Email", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(Modifier.height(8.dp))
            Text(
                "Hemos enviado un código de verificación a $email.\nVerifica tu bandeja de spam si no lo encuentras.",
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )

            Spacer(Modifier.height(12.dp))
            OutlinedTextField(
                value = code,
                onValueChange = onCodeChange,
                label = { Text("Introduce el código") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))
            Button(onClick = onVerify, colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) {
                Text("Verificar", color = Color.White)
            }
            Spacer(Modifier.height(8.dp))
            Button(onClick = onResend, colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) {
                Text("Reenviar código", color = Color.White)
            }

            Spacer(Modifier.height(8.dp))
            TextButton(onClick = onBack) { Text("Volver atrás") }
        }
    }
}