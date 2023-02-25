package com.ingenifi.chartifi.cli;

import picocli.CommandLine;

@CommandLine.Command(name = "cfi", mixinStandardHelpOptions = true, subcommands = {YumlCommand.class})
public class CfiCommand {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new CfiCommand()).execute(args);
        System.exit(exitCode);
    }
}