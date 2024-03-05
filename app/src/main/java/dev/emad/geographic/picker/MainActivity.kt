package dev.emad.geographic.picker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.emad.geographic.picker.ui.theme.IranGeographicPickerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IranGeographicPickerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainContent()
                }
            }
        }
    }
}

@Composable
fun MainContent() {
    val (selectedGeographicEntity, setSelectedGeographicEntity) = remember {
        mutableStateOf<GeographicEntity?>(
            null
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {
        IranGeographicMapView(
            modifier = Modifier.weight(1f),
            onGeographicEntitySelected = setSelectedGeographicEntity
        )

        selectedGeographicEntity?.let { entity ->
            GeographicEntityDetails(geographicEntity = entity, modifier = Modifier.padding(16.dp))
        }
    }
}

@Composable
fun GeographicEntityDetails(geographicEntity: GeographicEntity, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = geographicEntity.name, style = MaterialTheme.typography.bodyLarge)
        Image(
            painter = painterResource(id = geographicEntity.drawableId),
            contentDescription = geographicEntity.name,
            modifier = Modifier.size(200.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainContent() {
    IranGeographicPickerTheme {
        MainContent()
    }
}
