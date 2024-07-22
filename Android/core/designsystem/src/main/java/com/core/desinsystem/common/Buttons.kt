package com.core.desinsystem.common

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.core.desinsystem.theme.CallGreen
import com.core.desinsystem.theme.TransparentWhite
import com.core.desinsystem.theme.TransparentWhiteDeem

@Composable
fun BasicTextButton(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.clickable(onClick = onClick),
        text = title,
    )
}

@Composable
fun NextButton(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        onClick = onClick,
        enabled = enabled,
    ) {
        Text(
            text = title
        )

    }
}

@Composable
fun BasicOutlinedButton(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedButton(
        modifier = modifier.defaultMinSize(minHeight = 1.dp, minWidth = 1.dp),
        contentPadding = PaddingValues(vertical = 5.dp, horizontal = 16.dp),
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = TransparentWhiteDeem,
            contentColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.White,
        ),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Text(text = title)
    }
}

@Composable
fun CallButton(
    tel: String,
    context: Context,
    modifier: Modifier = Modifier,
) {
    IconButton(
        modifier = modifier.size(30.dp),
        onClick = {
            callUser(context, tel)
        },
        colors = IconButtonColors(
            containerColor = CallGreen,
            contentColor = Color.White,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.White,
        )
    ) {
        Icon(
            modifier = modifier.padding(5.dp),
            imageVector = Icons.Rounded.Call,
            contentDescription = null
        )
    }
}

private fun callUser(
    context: Context,
    tel: String,
) {
    val telUri = "tel:$tel"
    val intent = Intent(Intent.ACTION_DIAL).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
        data = Uri.parse(telUri)
    }
    context.startActivity(intent)
}
