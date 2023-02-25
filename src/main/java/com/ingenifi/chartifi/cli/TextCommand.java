package com.ingenifi.chartifi.cli;

import com.chrisrobertsfl.base.MorePaths;
import com.ingenifi.chartifi.yuml.YumlGenerator;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.Callable;

import static java.nio.file.Files.exists;

@CommandLine.Command(name = "text", description = "Output in plain text format", mixinStandardHelpOptions = true)
@Slf4j
class TextCommand implements Callable<Integer> {

    @CommandLine.Parameters(index = "0", description = "The file or directory path")
    Path fileOrDirectory;

    @Override
    public Integer call() throws Exception {
        if (!exists(fileOrDirectory)) {
            log.error("File or directory does not exist: {}", fileOrDirectory);
            return 1;
        }
        List<Path> files = MorePaths.getAllFiles(fileOrDirectory);
        new YumlGenerator().generate(files).stream()
                .forEach(System.out::println);

        return 0;
    }
}