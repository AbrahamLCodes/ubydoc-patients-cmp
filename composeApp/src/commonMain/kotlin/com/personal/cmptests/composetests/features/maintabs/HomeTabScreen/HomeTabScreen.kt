package com.personal.cmptests.composetests.features.maintabs.HomeTabScreen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Ambulance
import compose.icons.fontawesomeicons.solid.BookMedical
import compose.icons.fontawesomeicons.solid.ClinicMedical
import compose.icons.fontawesomeicons.solid.Donate
import compose.icons.fontawesomeicons.solid.Hospital
import compose.icons.fontawesomeicons.solid.LaptopMedical
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun HomeTabScreen() {
    val emergencyItems = listOf(
        ProfessionalItem("Enfermero", "", true, "NURSE", true, FontAwesomeIcons.Solid.BookMedical),
        ProfessionalItem("Médico", "", true, "GENERAL", true, FontAwesomeIcons.Solid.ClinicMedical)
    )

    val optionItems = listOf(
        ProfessionalItem("Enfermero a domicilio", "", true, "NURSE", false, FontAwesomeIcons.Solid.BookMedical),
        ProfessionalItem("Médico a Domicilio", "", true, "GENERAL", false, FontAwesomeIcons.Solid.ClinicMedical),
        ProfessionalItem("Especialista Presencial", "", true, "SPECIALIST", false, FontAwesomeIcons.Solid.Hospital),
        ProfessionalItem("Ambulancia", "", false, "AMBULANCE", false, FontAwesomeIcons.Solid.Ambulance),
        ProfessionalItem("Donaciones", "", false, "DONATION", false, FontAwesomeIcons.Solid.Donate),
        ProfessionalItem("Atención Virtual", "", false, "VIRTUAL", false, FontAwesomeIcons.Solid.LaptopMedical)
    )

    val emergencyRows = emergencyItems.chunked(2)
    val rows = optionItems.chunked(3)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF3880FF))
        ) {
            Text(
                "Avisos especiales de la plataforma:\n\nSin avisos especiales",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "\uD83D\uDCBC Servicios de emergencia",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Text("El tiempo es oro.", fontSize = 14.sp, color = Color.DarkGray)

        Spacer(modifier = Modifier.height(8.dp))

        emergencyRows.forEachIndexed { rowIndex, row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                row.forEachIndexed { colIndex, item ->
                    val itemIndex = rowIndex * 2 + colIndex
                    ProfessionalCard(item, index = itemIndex)
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "\uD83D\uDCBC Servicios por agenda",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Text("Selecciona la opción acorde a tus necesidades.", fontSize = 14.sp, color = Color.DarkGray)

        Spacer(modifier = Modifier.height(8.dp))

        rows.forEachIndexed { rowIndex, row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                row.forEachIndexed { colIndex, item ->
                    val itemIndex = (rowIndex * 3 + colIndex) + emergencyItems.size
                    ProfessionalCard(item, index = itemIndex)
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun ProfessionalCard(
    item: ProfessionalItem,
    index: Int
) {
    val translationY = remember { Animatable(100f) }
    val alphaAnim = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        delay(index * 100L)

        launch {
            translationY.animateTo(
                targetValue = 0f,
                animationSpec = tween(durationMillis = 300)
            )
        }
        launch {
            alphaAnim.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 300)
            )
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(100.dp)
            .graphicsLayer {
                this.translationY = translationY.value
                alpha = alphaAnim.value
            }
    ) {
        Card(
            modifier = Modifier
                .size(80.dp)
                .clickable { /* TODO: Handle item click */ }
                .graphicsLayer { alpha = if (item.available) 1f else 0.5f },
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.title,
                    tint = if (item.available) if (item.isEmergency) Color(0xFFFFC409) else Color(0xFF3880FF) else Color.Gray,
                    modifier = Modifier.size(36.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = item.title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )

        Text(
            text = if (item.available) item.subtitle else "Muy pronto",
            fontSize = 12.sp,
            color = if (item.available) Color.DarkGray else Color.Red,
            textAlign = TextAlign.Center
        )
    }
}


data class ProfessionalItem(
    val title: String,
    val subtitle: String,
    val available: Boolean,
    val professionalType: String,
    val isEmergency: Boolean,
    val icon: ImageVector
)