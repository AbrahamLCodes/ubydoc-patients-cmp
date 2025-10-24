package com.personal.cmptests.composetests.features.maintabs.ProfileTabScreen.authmodalscreen.subviews

import androidx.compose.foundation.Image
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composetests.composeapp.generated.resources.Res
import composetests.composeapp.generated.resources.ubydoc_login
import org.jetbrains.compose.resources.painterResource

@Composable
fun RecoverPasswordView(
    email: String,
    onEmailChange: (String) -> Unit,
    onRecover: () -> Unit,
    onCancel: () -> Unit
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
            Image(
                painter = painterResource(Res.drawable.ubydoc_login),
                contentDescription = "App Logo",
                modifier = Modifier.size(120.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(Modifier.height(8.dp))

            Text("Recuperación de Contraseña", fontWeight = FontWeight.Bold, fontSize = 20.sp)

            Spacer(Modifier.height(8.dp))

            Text(
                "Si existe un usuario con el correo introducido, se enviará la nueva contraseña a esa dirección de correo electrónico.",
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = email,
                onValueChange = onEmailChange,
                label = { Text("Introduce el email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            Button(onClick = onRecover, colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) {
                Text("Verificar", color = Color.White)
            }

            Spacer(Modifier.height(8.dp))

            Button(onClick = onCancel, colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) {
                Text("Cancelar", color = Color.White)
            }
        }
    }
}