package com.personal.cmptests.composetests.features.SplashScreen

import androidx.compose.foundation.background
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.personal.cmptests.composetests.features.maintabs.HomeTabScreen.HomeTabScreen
import com.personal.cmptests.composetests.features.maintabs.ProfileTabScreen.ProfileTabScreen
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.User
import compose.icons.fontawesomeicons.solid.ShoppingCart
import compose.icons.fontawesomeicons.solid.*


@Composable
fun MainTabsScreen() {
    var selectedIndex by remember { mutableStateOf(1) }

    val tabs = listOf(
        TabItem("Profile", FontAwesomeIcons.Solid.User),
        TabItem("Home", FontAwesomeIcons.Solid.HouseUser),
        TabItem("Cart", FontAwesomeIcons.Solid.ShoppingCart)
    )

    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 12.dp)
                    .offset(y = (-26).dp)
                    .shadow(8.dp, RoundedCornerShape(24.dp))
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(24.dp))
                    .padding(vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    tabs.forEachIndexed { index, tab ->
                        IconButton(onClick = { selectedIndex = index }) {
                            Icon(
                                tab.icon,
                                contentDescription = tab.label,
                                modifier = Modifier.size(24.dp),
                                tint = if (selectedIndex == index) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                            )
                        }
                    }
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            when (selectedIndex) {
                0 -> ProfileTabScreen()
                1 -> HomeTabScreen()
                2 -> Text("🛒 Cart Screen")
            }
        }
    }
}

data class TabItem(val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)