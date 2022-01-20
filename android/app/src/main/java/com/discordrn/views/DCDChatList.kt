package com.discordrn.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.discordrn.models.Message
import com.discordrn.models.MessageContent
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.google.accompanist.appcompattheme.AppCompatTheme

class DCDChatList : SimpleViewManager<ComposeView>() {

    companion object {
        private val messages = mutableStateListOf<Message>()

        @JvmStatic
        fun addMessage(message: Message) {
            messages.add(0, message)
        }
    }

    override fun getName() = "DCDChatList"

    override fun createViewInstance(reactContext: ThemedReactContext) =
        ComposeView(reactContext).apply {
            setContent {
                AppCompatTheme(context = reactContext) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        reverseLayout = true
                    ) {
                        items(messages) { message ->
                            WidgetChatMessage(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(6.dp),
                                message = message,
                            )
                        }
                    }
                }
            }
        }

    @Composable
    private fun WidgetChatMessage(
        message: Message,
        modifier: Modifier = Modifier
    ) {
        val messageContentString = parseMessageContentToSpan(message.content)

        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.White),
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .heightIn(min = 40.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                Text(
                    text = message.authorId,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colors.primary
                )
                Text(
                    text = messageContentString,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.primary
                )
            }
        }
    }

    @Composable
    private fun parseMessageContentToSpan(
        contentList: List<MessageContent>
    ): AnnotatedString = buildAnnotatedString {
        for (content in contentList) {
            when (content) {
                is MessageContent.Text -> append(content.content)
                is MessageContent.Link -> {
                    withStyle(SpanStyle(color = MaterialTheme.colors.blurple)) {
                        append(content.target)
                    }
                }
                is MessageContent.CodeBlock -> {
                    withStyle(SpanStyle(
                        color = Color.LightGray,
                        background = Color.DarkGray
                    )) {
                        append(content.content)
                    }
                }
                is MessageContent.Mention -> {
                    withStyle(SpanStyle(
                        color = MaterialTheme.colors.blurple,
                        background = MaterialTheme.colors.blurpleBackground
                    )) {
                        append('@')
                        append(content.userId)
                    }
                }
                is MessageContent.Emoji -> append(content.content)
                is MessageContent.CustomEmoji -> {
                    append(':')
                    append(content.alt)
                    append(':')
                }
                is MessageContent.Channel -> {
                    withStyle(SpanStyle(
                        color = MaterialTheme.colors.blurple,
                        background = MaterialTheme.colors.blurpleBackground
                    )) {
                        append('#')
                        append(content.channelId)
                    }
                }
                is MessageContent.Strong -> {
                    withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                        append(parseMessageContentToSpan(content.content))
                    }
                }
                is MessageContent.Emphasis ->  {
                    withStyle(SpanStyle(fontStyle = FontStyle.Italic)) {
                        append(parseMessageContentToSpan(content.content))
                    }
                }
                is MessageContent.Stroke -> {
                    withStyle(SpanStyle(textDecoration = TextDecoration.LineThrough)) {
                        append(parseMessageContentToSpan(content.content))
                    }
                }
                is MessageContent.Underlined -> {
                    withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append(parseMessageContentToSpan(content.content))
                    }
                }
                is MessageContent.InlineCode -> {
                    withStyle(SpanStyle(
                        color = Color.LightGray,
                        background = Color.DarkGray
                    )) {
                        append(content.content)
                    }
                }
                else -> {
                    //TODO
                }
            }
        }
    }

    private val Colors.blurple get() =
        Color(88, 101, 242)

    private val Colors.blurpleBackground get() =
        blurple.copy(alpha = 0.3f)

}