package com.yadekanbir.quicksms

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yadekanbir.quicksms.model.SmsTemplate

@Composable
fun SmsTemplateItem(template: SmsTemplate, onSendSms: (String) -> Unit) {
    var showDialog by remember { mutableStateOf(false) }
    var phoneNumber by remember { mutableStateOf("") }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
                phoneNumber = ""
            },
            title = { Text("Numara Giriniz") },
            text = {
                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = {
                        phoneNumber = it
                    },
                    label = { Text("Numara") },
                    placeholder = { Text("90XXXXXXXXXX") },
                    maxLines = 1
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        onSendSms(phoneNumber)
                        showDialog = false
                        phoneNumber = ""
                    },
                    enabled = phoneNumber.isNotBlank()
                ) {
                    Text("Gönder")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog = false
                        phoneNumber = ""
                    }
                ) {
                    Text("İptal")
                }
            }
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = template.content, modifier = Modifier
                .weight(1f)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Button(onClick = { showDialog = true }) {
            Text("Gönder")
        }
        Spacer(modifier = Modifier.padding(4.dp))
        Icon(
            Icons.Filled.Close,
            contentDescription = "Remove",
            tint = Color.White,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.Red)
                .padding(8.dp)
                .clickable { smsTemplates.remove(template) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SmpTemplateItemPreview() {
    SmsTemplateItem(template = SmsTemplate(0, "Test"), onSendSms = {})
}

@Preview(showBackground = true)
@Composable
fun LongSmpTemplateItemPreview() {
    SmsTemplateItem(
        template = SmsTemplate(
            0,
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut euismod congue tortor vel porttitor. Quisque vitae nisl vitae ligula tristique tempor ut vel justo. Sed."
        ),
        onSendSms = {}
    )
}
