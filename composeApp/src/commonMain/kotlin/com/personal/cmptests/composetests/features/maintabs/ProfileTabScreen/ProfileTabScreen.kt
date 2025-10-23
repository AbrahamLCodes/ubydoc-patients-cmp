package com.personal.cmptests.composetests.features.maintabs.ProfileTabScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.personal.cmptests.composetests.features.maintabs.ProfileTabScreen.authmodalscreen.AuthModalScreen
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowRight
import compose.icons.fontawesomeicons.solid.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTabScreen() {
    val iconUser = FontAwesomeIcons.Solid.User

    val modalState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showAuthModal by remember { mutableStateOf(false) }
    var isSignedIn by remember { mutableStateOf(true) }

    val usuario = remember {
        mapOf(
            "fullName" to "Abraham Luna",
            "username" to "1234567890",
            "emailVerified" to false
        )
    }

    if (!isSignedIn) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Debes iniciar sesión para acceder a esta pantalla")
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Iniciar sesión",
                    color = Color.Red,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { showAuthModal = true }
                )
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Perfil",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Icon(
                    imageVector = iconUser,
                    contentDescription = "Settings",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { /* TODO: Open profile modal */ }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Información de la cuenta",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                ProfileDetailRow(label = "Nombre", value = usuario["fullName"].toString())
                ProfileDetailRow(
                    label = "Teléfono",
                    value = formatPhone(usuario["username"].toString()),
                    showWarning = !(usuario["emailVerified"] as Boolean)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(4.dp)) {
                Column {
                    ProfileOptionRow("Rastreo de solicitudes") { /* Navigate */ }
                    ProfileOptionRow("Direcciones") { /* Navigate */ }
                    if (!(usuario["emailVerified"] as Boolean)) {
                        ProfileOptionRow("Verificar cuenta") { /* Open modal */ }
                    }
                    ProfileOptionRow("Cerrar Sesión", isDanger = true) {
                        isSignedIn = false
                    }
                }
            }
        }
    }

    if (showAuthModal) {
        ModalBottomSheet(
            onDismissRequest = { showAuthModal = false },
            sheetState = modalState,
            modifier = Modifier
                .padding(top = 50.dp)
        ) {
            AuthModalScreen(
                onClose = { showAuthModal = false },
                onSignedIn = {
                    isSignedIn = true
                    showAuthModal = false
                }
            )
        }
    }
}


@Composable
fun ProfileDetailRow(label: String, value: String, showWarning: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("$label: ", color = Color.Gray, fontSize = 16.sp)
        Text(value, fontSize = 16.sp)
        if (showWarning) {
            Text(" (No verificado)", color = Color.Red, fontSize = 14.sp)
        }
    }
}

@Composable
fun ProfileOptionRow(label: String, isDanger: Boolean = false, onClick: () -> Unit) {
    val iconArrowForward = FontAwesomeIcons.Solid.ArrowRight

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, fontSize = 16.sp, color = if (isDanger) Color.Red else Color.Black)
        Icon(
            modifier = Modifier.size(16.dp),
            imageVector = iconArrowForward,
            contentDescription = "Go",
            tint = if (isDanger) Color.Red else Color.Black
        )
    }
}

fun formatPhone(value: String): String {
    val clean = value.filter { it.isDigit() }
    return if (clean.length == 10) "(${clean.substring(0, 3)}) ${clean.substring(3, 6)}-${clean.substring(6, 10)}"
    else value
}
