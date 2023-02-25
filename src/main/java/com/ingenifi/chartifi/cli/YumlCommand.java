package com.ingenifi.chartifi.cli;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "yuml", mixinStandardHelpOptions = true, subcommands = {UmlClassDiagramCommand.class})
class YumlCommand implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        // TODO: implement the logic for the yuml command
        return 0;
    }
}
