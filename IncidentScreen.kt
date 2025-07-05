package com.example.myproject.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.myproject.model.IncidentRequest
import com.example.myproject.model.IncidentResponse
import com.example.myproject.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun IncidentScreen(userId: Long) {
    val context = LocalContext.current

    var selectedOffice by remember { mutableStateOf(1) }
    var description by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Report Incident", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(16.dp))

        Text("Select Office Number:")

        // Offices 1 to 11 radio buttons
        Column {
            (1..11).forEach { office ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (office == selectedOffice),
                            onClick = { selectedOffice = office }
                        )
                        .padding(vertical = 4.dp)
                ) {
                    RadioButton(
                        selected = (office == selectedOffice),
                        onClick = { selectedOffice = office }
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(text = "Office $office", modifier = Modifier.alignByBaseline())
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            maxLines = 6
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                if (description.isBlank()) {
                    Toast.makeText(context, "Description is required", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                val incidentRequest = IncidentRequest(
                    userId = userId,
                    officeNumber = selectedOffice,
                    description = description
                )

                RetrofitClient.instance.reportIncident(incidentRequest)
                    .enqueue(object : Callback<IncidentResponse> {
                        override fun onResponse(
                            call: Call<IncidentResponse>,
                            response: Response<IncidentResponse>
                        ) {
                            if (response.isSuccessful) {
                                Toast.makeText(context, "Incident reported successfully!", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "Failed: ${response.code()}", Toast.LENGTH_LONG).show()
                            }
                        }

                        override fun onFailure(call: Call<IncidentResponse>, t: Throwable) {
                            Toast.makeText(context, "Network error: ${t.message}", Toast.LENGTH_LONG).show()
                        }
                    })
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit Incident")
        }
    }
}
