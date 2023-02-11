import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.amirhusseinsoori.grpckotlin.component.banner.utils.utilFont
import com.amirhusseinsoori.grpckotlin.ui.theme.*
import com.google.accompanist.insets.LocalWindowInsets

fun Modifier.clearFocusOnKeyboardDismiss(): Modifier = composed {
    var isFocused by remember { mutableStateOf(false) }
    var keyboardAppearedSinceLastFocused by remember { mutableStateOf(false) }
    if (isFocused) {
        val imeIsVisible = LocalWindowInsets.current.ime.isVisible
        val focusManager = LocalFocusManager.current
        LaunchedEffect(imeIsVisible) {
            if (imeIsVisible) {
                keyboardAppearedSinceLastFocused = true
            } else if (keyboardAppearedSinceLastFocused) {
                focusManager.clearFocus()
            }
        }
    }
    onFocusEvent {
        if (isFocused != it.isFocused) {
            isFocused = it.isFocused
            if (isFocused) {
                keyboardAppearedSinceLastFocused = false
            }
        }
    }
}

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onClearQuery: () -> Unit,
    onSearchFocusChange: (Boolean) -> Unit,
    enableClose: Boolean,
    modifier: Modifier = Modifier
) {
    Surface(
        color = FunctionalDarkGrey,
        contentColor = Color.White,
        shape = MaterialTheme.shapes.small,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Box(Modifier.fillMaxSize()) {
            if (query.isEmpty()) {
                SearchHint()
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight()
            ) {

                AnimatedVisibility(visible = enableClose) {
                    IconButton(onClick = onClearQuery) {
                        Icon(
                            imageVector = mirroringCancelIcon(),
                            tint = Color.White,
                            contentDescription = ""
                        )
                    }
                }

                val keyboardController = LocalSoftwareKeyboardController.current
                BasicTextField(
                    value = query,
                    textStyle = TextStyle(
                        color = Color.White,
                        fontSize = 14.sp,
                    ),
                    onValueChange = onQueryChange,
                    modifier = Modifier
                        .clearFocusOnKeyboardDismiss()
                        .weight(1f)
                        .onFocusChanged {
                            onSearchFocusChange(it.isFocused)
                        },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done,
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                        },
                    ),
                )

            }
        }
    }
}

private val IconSize = 48.dp

@Composable
private fun SearchHint() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (icon, txt) = createRefs()
        Icon(
            modifier = Modifier.constrainAs(icon) {
                top.linkTo(parent.top, margin = 4.dp)
                end.linkTo(parent.end, margin = 20.dp)
                bottom.linkTo(parent.bottom, margin = 4.dp)
            },
            imageVector = Icons.Outlined.Search,
            tint = Color.White,
            contentDescription = "search"
        )

        Text(
            modifier = Modifier.constrainAs(txt) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
            },
            fontFamily = utilFont,
            fontWeight = FontWeight.Normal,
            text = "Search Movies",
            color = Color.White
        )
    }
}

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
private fun SearchBarPreview() {

    Surface() {
        SearchBar(
            query = "",
            onQueryChange = { },
            onSearchFocusChange = { },
            onClearQuery = { },
            enableClose = false
        )
    }

}

@Composable
fun mirroringIcon(ltrIcon: ImageVector, rtlIcon: ImageVector): ImageVector =
    if (LocalLayoutDirection.current == LayoutDirection.Ltr) ltrIcon else rtlIcon

@Composable
fun mirroringCancelIcon() = mirroringIcon(
    ltrIcon = Icons.Default.Close, rtlIcon = Icons.Outlined.ArrowForward
)