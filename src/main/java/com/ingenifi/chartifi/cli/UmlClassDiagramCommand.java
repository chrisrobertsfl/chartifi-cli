package com.ingenifi.chartifi.cli;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "uml-class-diagram", mixinStandardHelpOptions = true, description = "UML Class diagram", subcommands = {TextCommand.class})
class UmlClassDiagramCommand implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        // TODO: implement the logic for the uml-class-diagram command
        return 0;
    }
}