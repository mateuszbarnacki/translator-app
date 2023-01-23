package com.project.translator.application.port.out;

import com.project.translator.domain.TagDomain;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class TagMapperTest {
    @Autowired
    private TagMapper mapper;

    @Test
    void shouldMapDomainToDto() {
        // given
        TagDomain tagDomain = TagDomain.builder()
                .id(19230L)
                .tag("Test")
                .build();

        // when
        TagDto dto = mapper.mapDomainToDto(tagDomain);

        // then
        assertThat(dto).hasFieldOrPropertyWithValue("id", 19230L)
                .hasFieldOrPropertyWithValue("tag", "Test");
    }
}
