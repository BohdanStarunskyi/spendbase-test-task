package com.example.spendbase.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed


@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.noRippleClickable(enabled: Boolean = true, onClick: () -> Unit): Modifier = composed {
    clickable(
        indication = null,
        enabled = enabled,
        interactionSource = remember { MutableInteractionSource() },
        onClick = onClick
    )
}

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.rippleClickable(enabled: Boolean = true, onClick: () -> Unit): Modifier = composed {
    clickable(
        enabled = enabled,
        interactionSource = remember { MutableInteractionSource() },
        indication = rememberRipple(),
        onClick = onClick
    )
}