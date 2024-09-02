package com.example.labeledslider

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.labeledslider.ui.theme.LabeledSliderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabeledSliderTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    slider(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
enum class Location {
    TOP, BOTTOM,LEFT, RIGHT
}

@Composable
fun slider(modifier: Modifier=Modifier,
           location: Location = Location.TOP) {
    var sliderPosition by remember { mutableStateOf(0f) }
    Column(modifier = modifier) {
        if (location == Location.TOP) {
                Text(text = "Slider value: $sliderPosition")
                Slider(
                    value = sliderPosition,
                    onValueChange = { sliderPosition = it } ,
                    colors = SliderDefaults.colors(
                        thumbColor = MaterialTheme.colorScheme.secondary,
                        activeTrackColor = MaterialTheme.colorScheme.secondary,
                        inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                    ),
                    steps = 2,
                    valueRange = 0f..5f
                )
            }
        if (location == Location.BOTTOM) {

            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it }
            )
            Text(text = "Slider value: $sliderPosition")
        }
        if (location == Location.LEFT) {
            Row {
                Text(text = "Slider value: $sliderPosition")
                Slider(
                    value = sliderPosition,
                    onValueChange = { sliderPosition = it }
                )
            }
        }
        if (location == Location.RIGHT) {
            Row {
                Slider(
                    modifier = Modifier.weight(1f),
                    value = sliderPosition,
                    onValueChange = { sliderPosition = it }
                )

                Text(text = "Slider value: $sliderPosition")

            }
        }

        }
    }

@Composable
fun CheckboxSample(modifier: Modifier = Modifier) {
        Column(modifier = modifier) {
            var checked by remember { mutableStateOf(true) }
            Row(
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(text = "Check it")
                Checkbox(checked = checked, onCheckedChange = { checked = it })
            }
            Text(text = "Checkbox is $checked")
        }
    }

@Preview(showBackground = true)
@Composable
fun checkboxPreview() {
        LabeledSliderTheme {
            CheckboxSample()
        }
    }

@Preview(showBackground = true)
@Composable
fun sliderPreview() {
        LabeledSliderTheme {
            slider(location = Location.TOP)
        }
}
