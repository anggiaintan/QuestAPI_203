package com.example.api.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.api.ui.view.DestinasiDetail
import com.example.api.ui.view.DestinasiEntry
import com.example.api.ui.view.DestinasiHome
import com.example.api.ui.view.DestinasiUpdate
import com.example.api.ui.view.DetailMhsView
import com.example.api.ui.view.EntryMhsScreen
import com.example.api.ui.view.HomeScreen
import com.example.api.ui.view.UpdateView

@Composable
fun PengelolaHalaman(
    modifier: Modifier = Modifier,
            navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier,
    ) {
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                onDetailClick = { nim ->
                    println ("PengelolaHalaman: nim = $nim")
                    navController.navigate("${DestinasiDetail.route}/$nim") {
                        popUpTo(DestinasiHome.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(DestinasiEntry.route) {
            EntryMhsScreen(navigateBack = {
                navController.navigate(DestinasiHome.route) {
                    popUpTo(DestinasiHome.route) {
                        inclusive = true
                    }
                }
            })
        }
        composable(DestinasiDetail.routeWithArg) { navBackStackEntry ->
            val nim = navBackStackEntry.arguments?.getString(DestinasiDetail.NIM)
            nim?.let { DetailMhsView(
                nim = it,
                navigateBack = {
                    navController.navigate(DestinasiHome.route) {
                        popUpTo(DestinasiHome.route) {
                            inclusive = true
                        }
                    }
                }, onEditClick = { navController.navigate("${DestinasiUpdate.route}/$it")}
            ) }
        }
        composable(DestinasiUpdate.routesWithArg,
            arguments = listOf( navArgument(DestinasiUpdate.NIM) {
                type = NavType.StringType
            })
        ) { backStackEntry -> val nim = backStackEntry.arguments?.getString(DestinasiUpdate.NIM)
        nim?.let { UpdateView(navigateBack = { navController.popBackStack()},
            modifier = modifier) }}
}
}