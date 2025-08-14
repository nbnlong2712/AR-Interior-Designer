package com.example.arinteriordesigner.presenter.component

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.arinteriordesigner.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LoginButton(
    onClick: () -> Unit,
    @ColorRes backgroundColor: Int,
    text: String,
    @DrawableRes icon: Int,
    @ColorRes textColor: Int = R.color.black,
) {
    var enabled by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()
    OutlinedButton(
        onClick = {
            if (!enabled) return@OutlinedButton
            enabled = false
            onClick()
            scope.launch {
                delay(3_000)
                enabled = true
            }
        },
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .widthIn(max = 400.dp)
            .heightIn(max = 60.dp)
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 8.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = colorResource(id = backgroundColor),
        ),
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = "",
            tint = Color.Unspecified,
            modifier = Modifier.padding(end = 6.dp)
        )
        Text(text = text, fontSize = 16.sp, color = colorResource(id = textColor))
    }
}