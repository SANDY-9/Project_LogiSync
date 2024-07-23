package com.core.desinsystem.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.desinsystem.theme.LogiLightGray

@Composable
fun EmptyRecordView(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(
            start = 20.dp,
            end = 20.dp,
            bottom = 16.dp,
            top = 8.dp,
        ).background(
            color = LogiLightGray,
            shape = RoundedCornerShape(16.dp)
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            modifier = Modifier.size(20.dp),
            imageVector = Icons.Rounded.Info,
            contentDescription = null,
            tint = Color.Gray,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "수집된 심박수 데이터가 없습니다.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}
@Preview(name = "EmptyRecordView")
@Composable
private fun PreviewEmptyRecordView() {
    EmptyRecordView()
}
