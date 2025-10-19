package com.personal.cmptests.composetests.features.MainTabsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import kotlinx.coroutines.delay
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composetests.composeapp.generated.resources.Res
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.animateLottieCompositionAsState
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import io.github.alexzhirkevich.compottie.rememberLottiePainter
import kotlin.random.Random

@Composable
fun SplashScreen(onFinished: () -> Unit) {
    val quotes = listOf(
        "La salud es el mayor regalo de la vida. Cuídala con amor y respeto",
        "Un cuerpo sano es una mente sana. Prioriza tu bienestar físico y mental",
        "La prevención es mejor que la cura. Cuida tu salud antes de que surjan problemas",
        "La medicina cura, pero el amor sana. Brinda compasión y apoyo a quienes necesitan ayuda",
        "Cada cuerpo es único. Escucha las señales de tu cuerpo y busca atención médica adecuada",
        "La salud no es solo la ausencia de enfermedad, es un estado de bienestar completo. Busca equilibrio en tu vida",
        "La salud es un viaje, no un destino. Continúa aprendiendo y creciendo en tu camino hacia el bienestar",
        "No esperes a sentirte mal para cuidar tu salud. Haz del bienestar una prioridad diaria",
        "La salud es un tesoro invaluable. Invierte en tu bienestar físico y mental",
        "Comparte tu conocimiento sobre salud con los demás. Juntos, podemos crear una comunidad más saludable"
    )

    val randomQuote = remember { quotes[Random.nextInt(quotes.size)] }

    val composition by rememberLottieComposition {
        LottieCompositionSpec.JsonString(
            Res.readBytes("files/lottie_loading_white.json").decodeToString()
        )
    }


    val progress by animateLottieCompositionAsState(composition)
    LaunchedEffect(Unit) {
        delay(4000)
        onFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.horizontalGradient(
                    colors = listOf(Color(0xFF4087F3), Color(0xFF33E4F2))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = rememberLottiePainter(
                    composition = composition,
                    progress = { progress },
                ),
                contentDescription = "Lottie animation"
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = randomQuote,
                color = Color.White,
                fontSize = 18.sp,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}