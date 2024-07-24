package com.feature.arrest.admin.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.core.desinsystem.common.DottedLine
import com.core.desinsystem.common.LogiCardClickable
import com.core.desinsystem.icons.PulseAlert
import com.core.desinsystem.theme.DarkGreen
import com.core.desinsystem.theme.DarkRed
import com.core.desinsystem.theme.LogiDarkBlue
import com.core.model.Arrest
import com.core.utils.MaskingUtil
import java.time.LocalDateTime

@Composable
internal fun AdminArrestItem(
    arrest: Arrest,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        when(arrest.arrestType) {
            Arrest.ArrestType.NORMAL -> AdminReportItemNormal(
                id = MaskingUtil.maskId(arrest.id),
                name = MaskingUtil.maskName(arrest.name),
                tel = MaskingUtil.maskTel(arrest.tel),
                desc = arrest.arrestType.desc,
                date = arrest.date(),
                onItemClick = onItemClick,
            )
            Arrest.ArrestType.HEART_RATE_LOW ->  AdminReportItemHeartRate(
                id = MaskingUtil.maskId(arrest.id),
                name = MaskingUtil.maskName(arrest.name),
                tel = arrest.tel,
                bpm = arrest.bpm,
                desc = arrest.arrestType.desc,
                date = arrest.date(),
                onItemClick = onItemClick,
            )
            Arrest.ArrestType.HEART_RATE_HIGH ->  AdminReportItemHeartRate(
                id = MaskingUtil.maskId(arrest.id),
                name = MaskingUtil.maskName(arrest.name),
                tel = MaskingUtil.maskTel(arrest.tel),
                bpm = arrest.bpm,
                desc = arrest.arrestType.desc,
                date = arrest.date(),
                onItemClick = onItemClick,
            )
        }
        Spacer(modifier = modifier.height(8.dp))
    }
}

@Composable
private fun AdminReportItemNormal(
    id: String,
    name: String,
    tel: String,
    desc: String,
    date: String,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LogiCardClickable(
        onClick = onItemClick,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = modifier.size(20.dp),
                imageVector = Icons.Rounded.Warning,
                contentDescription = null,
                tint = DarkRed,
            )
            Spacer(modifier = modifier.width(6.dp))
            Text(
                text = desc,
                style = MaterialTheme.typography.labelLarge,
                color = DarkRed,
            )
            Spacer(modifier = modifier.weight(1f))
            Text(
                text = date,
                style = MaterialTheme.typography.bodySmall.copy(
                    letterSpacing = (-0.5).sp
                )
            )
        }
        DottedLine(modifier = modifier.padding(vertical = 8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = modifier.size(20.dp),
                imageVector = Icons.Rounded.AccountBox,
                tint = LogiDarkBlue,
                contentDescription = null,
            )
            Spacer(modifier = modifier.width(6.dp))
            Text(
                text = "$id ($name)",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = modifier.width(16.dp))
            Icon(
                modifier = modifier.size(20.dp),
                imageVector = Icons.Rounded.Call,
                tint = DarkGreen,
                contentDescription = null,
            )
            Spacer(modifier = modifier.width(6.dp))
            Text(
                text = "$tel",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
private fun AdminReportItemHeartRate(
    id: String,
    name: String,
    tel: String,
    bpm: Int?,
    desc: String,
    date: String,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LogiCardClickable(
        onClick = onItemClick,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = modifier.size(20.dp),
                imageVector = Icons.PulseAlert,
                contentDescription = null,
                tint = DarkRed,
            )
            Spacer(modifier = modifier.width(6.dp))
            Row {
                Text(
                    text = "$desc ",
                    style = MaterialTheme.typography.labelLarge.copy(
                        letterSpacing = (-0.2).sp
                    ),
                    color = DarkRed,
                )
                Text(
                    modifier = modifier.padding(top = 1.dp),
                    text = "($bpm)",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 13.sp
                    ),
                    color = DarkRed
                )
            }
            Spacer(modifier = modifier.weight(1f))
            androidx.compose.material3.Text(
                text = date,
                style = MaterialTheme.typography.bodySmall.copy(
                    letterSpacing = (-0.5).sp
                ),
            )
        }
        DottedLine(modifier = modifier.padding(vertical = 8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = modifier.size(20.dp),
                imageVector = Icons.Rounded.AccountBox,
                tint = LogiDarkBlue,
                contentDescription = null,
            )
            Spacer(modifier = modifier.width(6.dp))
            Text(
                text = "$id ($name)",
                style = MaterialTheme.typography.bodySmall,
            )
            Spacer(modifier = modifier.width(16.dp))
            Icon(
                modifier = modifier.size(20.dp),
                imageVector = Icons.Rounded.Call,
                tint = DarkGreen,
                contentDescription = null,
            )
            Spacer(modifier = modifier.width(6.dp))
            Text(
                text = "$tel",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(name = "AdminArrestItem")
@Composable
private fun PreviewAdminArrestItem() {
    AdminReportItemNormal(
        id = "a123455",
        name = "홍길동",
        tel = "000-0000-0000",
        desc = Arrest.ArrestType.NORMAL.desc,
        date = LocalDateTime.now().toString(),
        onItemClick = { /*TODO*/ },
        modifier = Modifier,
    )
}
