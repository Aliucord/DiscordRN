package com.discordrn.views

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.*
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.discordrn.databinding.WidgetChatBinding
import com.discordrn.databinding.WidgetChatMessageBinding
import com.discordrn.models.Message
import com.discordrn.models.MessageContent
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import java.util.ArrayList

class DCDChatList : SimpleViewManager<RecyclerView>() {

    override fun getName() = "DCDChatList"

    override fun createViewInstance(
        reactContext: ThemedReactContext
    ) = WidgetChatBinding.inflate(
        LayoutInflater.from(reactContext)
    ).root.apply {
        adapter = Adapter()
        layoutManager = LinearLayoutManager(reactContext, LinearLayoutManager.VERTICAL, true)
        setHasFixedSize(true)
    }

    inner class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {
        private val data = ArrayList<Message>()

        inner class ViewHolder(
            private val binding: WidgetChatMessageBinding
        ) : RecyclerView.ViewHolder(binding.root) {

            fun setMessageAuthor(author: String) {
                binding.authorName.text = author
            }

            fun setMessageContent(content: SpannableStringBuilder) {
                binding.authorName.text = content
            }
        }

        fun addMessage(message: Message) {
            data.add(message)
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ) = ViewHolder(
            WidgetChatMessageBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val message = data[position]

            holder.setMessageAuthor(message.authorId)
            holder.setMessageContent(parseMessageContentToSpan(message.content))
        }


        override fun getItemCount() = data.size
    }

    private fun parseMessageContentToSpan(
        contentList: List<MessageContent>
    ): SpannableStringBuilder = SpannableStringBuilder().apply {
        for (content in contentList) {
            when (content) {
                is MessageContent.Text -> append(content.content)
                is MessageContent.Link -> {
                    withStyles(ForegroundColorSpan(Color.BLUE)) {
                        append(parseMessageContentToSpan(content.content))
                    }
                }
                is MessageContent.CodeBlock -> {
                    withStyles(BackgroundColorSpan(Color.DKGRAY)) {
                        append(content.content)
                    }
                }
                is MessageContent.Mention -> {
                    withStyles(
                        ForegroundColorSpan(Color.parseColor("#5865F2")),
                        BackgroundColorSpan(Color.parseColor("#6f7ae8"))
                    ) {
                        append(parseMessageContentToSpan(content.content))
                    }
                }
                is MessageContent.Emoji -> append(content.content)
                is MessageContent.CustomEmoji -> {
                    append(':')
                    append(content.alt)
                    append(':')
                }
                is MessageContent.Channel -> {
                    withStyles(
                        ForegroundColorSpan(Color.parseColor("#5865F2")),
                        BackgroundColorSpan(Color.parseColor("#6f7ae8"))
                    ) {
                        append("#")
                        append(parseMessageContentToSpan(content.content))
                    }
                }
                is MessageContent.Strong -> {
                    withStyles(StyleSpan(Typeface.BOLD)) {
                        append(parseMessageContentToSpan(content.content))
                    }
                }
                is MessageContent.Emphasis ->  {
                    withStyles(StyleSpan(Typeface.ITALIC)) {
                        append(parseMessageContentToSpan(content.content))
                    }
                }
                is MessageContent.Stroke -> {
                    withStyles(StrikethroughSpan()) {
                        append(parseMessageContentToSpan(content.content))
                    }
                }
                is MessageContent.Underlined -> {
                    withStyles(UnderlineSpan()) {
                        append(parseMessageContentToSpan(content.content))
                    }
                }
                is MessageContent.InlineCode -> {
                    withStyles(BackgroundColorSpan(Color.DKGRAY)) {
                        append(content.content)
                    }
                }
            }
        }
    }

    private fun <T : SpannableStringBuilder> T.withStyles(
        vararg spans: Any,
        block: T.() -> Unit
    ) {
        val startIndex = if (length == 0) 0 else length - 1
        block()
        val endIndex = length
        spans.forEach { span ->
            setSpan(span, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }
}