package com.octal.todosalud.presentation.composeFragment

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.octal.todosalud.presentation.composeFragment.stateAndEvents.ComposeScreenEvents
import com.octal.todosalud.presentation.composeFragment.stateAndEvents.ComposeScreenState

@Composable
fun ComposeScreen(
    state: State<ComposeScreenState>,
    onComposeScreenEvents: (ComposeScreenEvents) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth(), Arrangement.Center) {
        Text(text = "Welcome to compose", modifier = Modifier.align(CenterHorizontally))
        Spacer(modifier = Modifier.height(32.dp))
        Text(text = state.value.textInput, modifier = Modifier.align(CenterHorizontally))
        Spacer(modifier = Modifier.height(32.dp))
        TextField(
            value = state.value.textInput,
            modifier = Modifier.align(CenterHorizontally),
            onValueChange = {
            onComposeScreenEvents.invoke(ComposeScreenEvents.OnTextChanged(it))
        })
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = {
            onComposeScreenEvents.invoke(ComposeScreenEvents.ShowToast)
        }, modifier = Modifier.align(CenterHorizontally)) {
            Text(text = "Click Here")
        }
    }
}