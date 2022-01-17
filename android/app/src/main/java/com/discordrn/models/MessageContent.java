package com.discordrn.models;

import java.util.List;

public abstract class MessageContent {
    public static class Text extends MessageContent {
        public String content;
    }

    public static class Link extends MessageContent {
        public List<MessageContent> content;
        public String target;
    }

    public static class CodeBlock extends MessageContent {
        public String lang;
        public String content;
        public boolean inQuote;
    }

    public static class Mention extends MessageContent {
        public String userId;
        public String channelId;
        public List<MessageContent> content;
    }

    public static class Channel extends MessageContent {
        public String channelId;
        public String guildId;
        public List<MessageContent> content;
    }

    public static class Emoji extends MessageContent {
        public String content;
        public String surrogate;
    }

    public static class CustomEmoji extends MessageContent {
        public String id;
        public String alt;
        public String src;
        public String frozenSrc;
        public boolean jumboable;
    }

    public static class Strong extends MessageContent {
        public List<MessageContent> content;
    }

    public static class Emphasis extends MessageContent {
        public List<MessageContent> content;
    }

    public static class Stroke extends MessageContent {
        public List<MessageContent> content;
    }

    public static class Underlined extends MessageContent {
        public List<MessageContent> content;
    }

    public static class InlineCode extends MessageContent {
        public String content;
    }
}
