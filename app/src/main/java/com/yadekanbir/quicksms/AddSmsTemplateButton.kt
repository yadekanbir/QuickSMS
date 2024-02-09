package com.yadekanbir.quicksms

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.yadekanbir.quicksms.model.SmsTemplate

@Composable
fun AddSmsTemplateButton(modifier: Modifier) {
    var smsText by remember { mutableStateOf("") }
    var showAddDialog by remember { mutableStateOf(false) }

    if (showAddDialog) {
        AlertDialog(
            onDismissRequest = {
                showAddDialog = false
                smsText = ""
            },
            title = { Text("SMS Şablonu Giriniz") },
            text = {
                OutlinedTextField(
                    value = smsText,
                    onValueChange = { smsText = it },
                    label = { Text("Buraya yazınız") },
                    minLines = 3
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showAddDialog = false
                        smsTemplates.add(SmsTemplate(smsTemplates.size + 1, smsText))
                        smsText = ""
                    },
                    enabled = smsText.isNotBlank()
                )
                {
                    Text("Ekle")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showAddDialog = false
                        smsText = ""
                    }
                ) {
                    Text("İptal")
                }
            }
        )
    }

    FloatingActionButton(
        onClick = { showAddDialog = !showAddDialog },
        contentColor = Color.Black,
        modifier = modifier.padding(bottom = 16.dp, end = 16.dp),
    ) {
        Icon(
            modifier = modifier.padding(24.dp),
            imageVector = Icons.Filled.Add,
            contentDescription = "Ekle"
        )
    }
}