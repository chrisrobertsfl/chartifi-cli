package com.ingenifi.chartifi.yuml;

import com.ingenifi.chartifi.yuml.parser.RoasterParser;
import com.ingenifi.chartifi.yuml.render.YumlRenderer;

import java.nio.file.Path;
import java.util.List;


public record YumlGenerator() {
    public List<String> generate(List<Path> files) {
        RoasterParser roasterParser = new RoasterParser();
        YumlRenderer yumlRenderer = new YumlRenderer();
        return roasterParser.parse(files).stream()
                .map(yumlRenderer::render)
                .toList();
    }
}
