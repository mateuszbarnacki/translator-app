package com.project.translator.adapter.in.web;

import com.project.translator.application.port.in.TagDetails;
import com.project.translator.application.port.in.TagUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.project.translator.application.port.out.TagDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Tag(name = "Tags")
@RequiredArgsConstructor
@RestController
@RequestMapping("/tags")
public class TagController {
    private final TagUseCase tagUseCase;

    @Operation(summary = "Returns all tags")
    @GetMapping
    Collection<TagDto> getTags() {
        return tagUseCase.getTags();
    }

    @Operation(summary = "Returns tag by id")
    @GetMapping(value = "/{id}")
    TagDto getById(@PathVariable("id") Long id) {
        return tagUseCase.getTagById(id);
    }

    @Operation(summary = "Create tag")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createTag(@RequestBody @Valid TagDetails tagDetails) {
        tagUseCase.createTag(tagDetails);
    }

    @Operation(summary = "Update tag with given id")
    @PutMapping("/{id}")
    void updateTag(@PathVariable("id") Long id, @RequestBody @Valid TagDetails tagDetails) {
        tagUseCase.updateTag(id, tagDetails);
    }

    @Operation(summary = "Delete tag with given id")
    @DeleteMapping("/{id}")
    void deleteTag(@PathVariable("id") Long id) {
        tagUseCase.deleteTag(id);
    }
}
