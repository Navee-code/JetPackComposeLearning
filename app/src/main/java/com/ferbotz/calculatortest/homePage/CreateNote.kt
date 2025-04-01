package com.ferbotz.calculatortest.homePage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ferbotz.calculatortest.AppApplication
import com.ferbotz.calculatortest.CalculatorViewModel
import com.ferbotz.calculatortest.retrofit.Gallery
import coil3.compose.AsyncImage

@Composable
fun RemainderNoteList() {
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current.applicationContext as AppApplication
    val viewModel = viewModel<CalculatorViewModel>()

    viewModel.fetchPhotos()
    val photos by viewModel._photos.collectAsState() // Observe StateFlow safely

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(photos) { photo ->
            PhotoItem(photo)
        }
    }


}


@Composable
fun PhotoItem(photo: Gallery) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = "https://fakestoreapi.com/img/71YAIFU48IL._AC_UL640_QL65_ML3_.jpg",
                contentDescription = "Photo Thumbnail",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = photo.title, style = MaterialTheme.typography.bodyLarge)
        }
    }
}