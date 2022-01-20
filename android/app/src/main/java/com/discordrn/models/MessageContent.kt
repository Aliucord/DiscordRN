package com.discordrn.models

sealed class MessageContent {
    data class Text(val content: String) : MessageContent()
    data class Link(val target: String, val content: List<MessageContent>) : MessageContent()
    data class CodeBlock(val lang: String, val inQuote: Boolean, val content: String) : MessageContent()
    data class Mention(val userId: String, val channelId: String, val roleId: String, val color: Int, val colorString: String, val content: List<MessageContent>) : MessageContent()
    data class Channel(val channelId: String, val guildId: String, val content: List<MessageContent>) : MessageContent()
    data class Emoji(val content: String, val surrogate: String) : MessageContent()
    data class CustomEmoji(val id: String, val alt: String, val src: String, val frozenSrc: String, val jumboable: Boolean) : MessageContent()
    data class Strong(val content: List<MessageContent>) : MessageContent()
    data class Emphasis(val content: List<MessageContent>) : MessageContent()
    data class Stroke(val content: List<MessageContent>) : MessageContent()
    data class Underlined(val content: List<MessageContent>) : MessageContent()
    data class InlineCode(val content: String) : MessageContent()
    data class Spoiler(val channelId: String) : MessageContent()
    data class Timestamp(val timestamp: String, val full: String, val formatted: String) : MessageContent()
    data class BlockQuote(val content: List<MessageContent>) : MessageContent()
    data class Paragraph(val content: List<MessageContent>) : MessageContent()
}