package com.ucne.proj_1erparcial_ap2.ui.prestamo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.DocumentScanner
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucne.proj_1erparcial_ap2.data.local.entity.PrestamoEntity

@Composable
fun PrestamoScreen(viewModel: PrestamoViewModel = hiltViewModel()) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.padding(10.dp))
        TituloBody()
        Spacer(modifier = Modifier.padding(10.dp))
        PrestamoBody(viewModel)
        Spacer(modifier = Modifier.padding(10.dp))
        TituloLista()
        Spacer(modifier = Modifier.padding(10.dp))
        val uiState by viewModel.uiState.collectAsState()
        PrestamoListScreen(uiState.prestamoList)
    }
}

@Composable
fun TituloBody() {
    Text(
        text = "Registro de Prestamos",
        fontSize = 35.sp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrestamoBody(viewModel: PrestamoViewModel) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        OutlinedTextField(modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
            value = viewModel.deudor,
            onValueChange = { viewModel.deudor = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = null,
                    modifier = Modifier
                        .size(33.dp)
                        .padding(4.dp)
                )
            },
            label = {
                Text(
                    text = "Deudor", style = MaterialTheme.typography.titleLarge
                )
            })

        OutlinedTextField(modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
            value = viewModel.concepto,
            onValueChange = { viewModel.concepto = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.DocumentScanner,
                    contentDescription = null,
                    modifier = Modifier
                        .size(33.dp)
                        .padding(4.dp)
                )
            },
            label = {
                Text(
                    text = "Concepto", style = MaterialTheme.typography.titleLarge
                )
            })
        OutlinedTextField(modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
            value = viewModel.monto,
            onValueChange = { viewModel.monto = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.AttachMoney,
                    contentDescription = null,
                    modifier = Modifier
                        .size(33.dp)
                        .padding(4.dp)
                )
            },
            label = {
                Text(
                    text = "Monto", style = MaterialTheme.typography.titleLarge
                )
            })
        ExtendedFloatingActionButton(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
            text = {
                Text(
                    text = "Guardar", style = MaterialTheme.typography.titleLarge
                )
            },
            icon = { Icon(imageVector = Icons.Filled.Save, contentDescription = "Save") },
            onClick = { viewModel.insert() })
    }
}

@Composable
private fun PrestamoListScreen(prestamoList: List<PrestamoEntity>) {
    LazyColumn {
        items(prestamoList) { prestamo ->
            PrestamoRow(prestamo)
        }
    }
}

@Composable
fun TituloLista() {
    Text(
        text = "Lista de Prestamos",
        fontSize = 35.sp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
private fun PrestamoRow(prestamo: PrestamoEntity) {
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = prestamo.deudor,
                fontSize = 35.sp,
                style = MaterialTheme.typography.titleLarge
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        ) {
            Text(
                text = prestamo.concepto, style = MaterialTheme.typography.titleLarge
            )
            Text(
                String.format("%.2f", prestamo.monto),
                fontSize = 35.sp,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .weight(3f)
                    .fillMaxWidth()
            )
        }
        Divider(Modifier.fillMaxWidth())
    }
}
