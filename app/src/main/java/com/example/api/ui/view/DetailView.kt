package com.example.api.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.api.model.Mahasiswa
import com.example.api.navigation.DestinasiNavigasi
import com.example.api.ui.customwidget.CostumeTopAppBar
import com.example.api.ui.viewmodel.DetailUiState
import com.example.api.ui.viewmodel.DetailViewModel
import com.example.api.ui.viewmodel.PenyediaViewModel

object DestinasiDetail : DestinasiNavigasi {
    override val route = "detail"
    const val NIM = "nim"
    val routeWithArg = "$route/{$NIM}"
    override val titleRes = "Detail Mhs"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailMhsView (
    nim: String,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onEditClick: (String) -> Unit = {},
    navigateBack: () -> Unit,
) {
    Scaffold (
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetail.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack,
                onRefresh = {viewModel.getDetailMahasiswa()}
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onEditClick(nim) },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Icon (
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Mhs"
                )
            }
        }
    ){  innerPadding -> val detailUiState by viewModel.detailUiState.collectAsState()
    BodyDetailMhs(
        modifier = Modifier.padding(innerPadding),
        detailUiState = detailUiState,
        retryAction = {viewModel.getDetailMahasiswa()}
    )}
}

@Composable
fun BodyDetailMhs (
    modifier: Modifier= Modifier,
    detailUiState: DetailUiState,
    retryAction: () -> Unit = {}
) {
    when (detailUiState) {
        is DetailUiState.Loading -> {
            OnLoading(modifier = modifier.fillMaxSize())
        }
        is DetailUiState.Success->{
            Column (
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ){  ItemDetailMhs (mahasiswa = detailUiState.mahasiswa)}
        }
        is DetailUiState.Error->{
            OnError(retryAction = retryAction, modifier = modifier.fillMaxSize())
        }
        else -> {
            Text("Unknown Error")
        }
    }
}

@Composable
fun ItemDetailMhs (mahasiswa: Mahasiswa)
{
    Card (
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ){  Column (modifier = Modifier.padding(16.dp)) {
        ComponentDetailMahasiswa(judul = "NIM", isinya = mahasiswa.nim)
        Spacer(modifier = Modifier.padding(4.dp))
        ComponentDetailMahasiswa(judul = "Nama", isinya = mahasiswa.nama)
        Spacer(modifier = Modifier.padding(4.dp))
        ComponentDetailMahasiswa(judul = "Alamat", isinya = mahasiswa.alamat)
        Spacer(modifier = Modifier.padding(4.dp))
        ComponentDetailMahasiswa(judul = "Jenis Kelamin", isinya = mahasiswa.jenisKelamin)
        Spacer(modifier = Modifier.padding(4.dp))
        ComponentDetailMahasiswa(judul = "Kelas", isinya = mahasiswa.kelas)
        Spacer(modifier = Modifier.padding(4.dp))
        ComponentDetailMahasiswa(judul = "Angkatan", isinya = mahasiswa.angkatan)
    }}
}

@Composable
fun ComponentDetailMahasiswa (
    judul: String,
    isinya: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul: ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = isinya,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}