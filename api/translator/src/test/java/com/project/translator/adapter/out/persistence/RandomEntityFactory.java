package com.project.translator.adapter.out.persistence;

import org.apache.commons.lang3.RandomStringUtils;

class RandomEntityFactory {

    public static LanguageEntity generateLanguage(String language) {
        LanguageEntity languageEntity = new LanguageEntity();
        languageEntity.setLanguage(language);
        return languageEntity;
    }

    public static TagEntity generateTag(String tag) {
        TagEntity tagEntity = new TagEntity();
        tagEntity.setTag(tag);
        return tagEntity;
    }

    public static MessageEntity generateMessage() {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setContent(RandomStringUtils.randomAlphabetic(10));
        return messageEntity;
    }
}
