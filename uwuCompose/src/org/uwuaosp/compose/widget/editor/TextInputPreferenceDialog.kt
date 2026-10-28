/*
 * Copyright (C) 2026 The uwuAOSP Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.uwuaosp.compose.widget.editor

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue

/** Keeps the edit local until confirmation and restores it across recreation. */
@Composable
fun TextInputPreferenceDialog(
    title: String,
    value: String,
    confirmText: String,
    dismissText: String,
    onConfirm: (String) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    validator: (String) -> Boolean = { true },
    errorText: String? = null,
) {
    val focusRequester = remember { FocusRequester() }
    var inputValue by
        rememberSaveable(value, stateSaver = TextFieldValue.Saver) {
            mutableStateOf(TextFieldValue(value, TextRange(value.length)))
        }
    val valid = validator(inputValue.text)
    fun confirm() {
        if (valid) onConfirm(inputValue.text)
    }
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        title = { Text(title) },
        text = {
            TextField(
                value = inputValue,
                onValueChange = { inputValue = it },
                modifier = Modifier.fillMaxWidth().focusRequester(focusRequester),
                singleLine = singleLine,
                isError = !valid,
                supportingText =
                    if (!valid && errorText != null) {
                        { Text(errorText) }
                    } else null,
                keyboardOptions =
                    KeyboardOptions(
                        imeAction = if (singleLine) ImeAction.Done else ImeAction.Default
                    ),
                keyboardActions = KeyboardActions(onDone = { confirm() }),
            )
        },
        confirmButton = { TextButton(onClick = ::confirm, enabled = valid) { Text(confirmText) } },
        dismissButton = { TextButton(onClick = onDismissRequest) { Text(dismissText) } },
    )
    LaunchedEffect(focusRequester) { focusRequester.requestFocus() }
}
