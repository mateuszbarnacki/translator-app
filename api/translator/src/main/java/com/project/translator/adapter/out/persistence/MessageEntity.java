package com.project.translator.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "message")
class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "language_id")
        private LanguageEntity language;

        @ManyToMany(cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
        })
        @JoinTable(name = "message_tag",
            joinColumns = @JoinColumn(name = "message_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
        )
        private Set<TagEntity> tags = new HashSet<>();

    @ManyToOne(
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "original_message_id")
    private MessageEntity originalMessage;


    public void addTag(TagEntity tag){
        tags.add(tag);
        tag.getMessages().add(this);
    }

    public void removeTag(TagEntity tag){
        tags.remove(tag);
        tag.getMessages().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MessageEntity that = (MessageEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
