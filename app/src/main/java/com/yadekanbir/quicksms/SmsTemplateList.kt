package com.yadekanbir.quicksms

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.yadekanbir.quicksms.model.SmsTemplate

@Composable
fun SmsTemplateList(templates: List<SmsTemplate>) {
    val context = LocalContext.current
    LazyColumn {
        items(templates) { template ->
            SmsTemplateItem(template) { phoneNumber ->
                sendSms(template, context, phoneNumber)
            }
        }
    }
}

private fun sendSms(template: SmsTemplate, context: Context, phoneNumber: String) {
    val smsUri = Uri.parse("smsto:+$phoneNumber")
    val smsIntent = Intent(Intent.ACTION_SENDTO, smsUri)
    smsIntent.putExtra("sms_body", template.content)
    ContextCompat.startActivity(context, smsIntent, null)
}