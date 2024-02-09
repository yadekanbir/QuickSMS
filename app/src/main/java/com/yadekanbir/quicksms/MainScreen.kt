package com.yadekanbir.quicksms


import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.yadekanbir.quicksms.model.SmsTemplate

var smsTemplates = mutableStateListOf<SmsTemplate>()

@Composable
fun MainScreen() {
    Column {
        Text(
            "SMS Şablonları",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(start = 24.dp, top = 24.dp)
        )

        if (smsTemplates.isNotEmpty())
            SmsTemplateList(smsTemplates)
        else {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Şablon listeniz boş. Eklemek için '+' butonunu kullanın.",
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )

        }
        Spacer(modifier = Modifier.weight(1f))
        AddSmsTemplateButton(modifier = Modifier.align(Alignment.End))
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    MainScreen()
}
