package edu.tamu.csce315_908_t4.imdbParser;

import edu.tamu.csce315_908_t4.imdbParser.inputDataType.InputDataBlock;
import edu.tamu.csce315_908_t4.imdbParser.inputDataType.InputTable;
import edu.tamu.csce315_908_t4.imdbParser.outputDataType.OutputDataBlock;
import edu.tamu.csce315_908_t4.imdbParser.outputDataType.OutputPaths;
import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.Scanner;

public class IMDBParser{
    public static void main(String[] args){
        Options options = new Options();
        options.addOption("h", "help", false, "Print this message");
        options.addOption("o", "output", true, "output directory, required");
        for(InputTable table : InputTable.values()){
            options.addOption(Character.toString(table.commandArgument), table.tableName, true, table.readableName + " table path");
        }

        DefaultParser parser = new DefaultParser();
        CommandLine commandLine;
        try{
            commandLine = parser.parse(options, args);
        } catch(ParseException e){
            e.printStackTrace();
            return;
        }

        if(commandLine.hasOption('h') || !commandLine.hasOption('o')){
            new HelpFormatter().printHelp("IMDBParser", options);
            return;
        }

        ArrayList<String> inputPaths = new ArrayList<>(7);
        Scanner keyboard = new Scanner(System.in);
        for(InputTable table : InputTable.values()){
            if(commandLine.hasOption(table.commandArgument)){
                inputPaths.add(commandLine.getOptionValue(table.commandArgument));
            } else{
                System.out.println("Input " + table.readableName + " path: ");
                inputPaths.add(keyboard.nextLine());
//                while(keyboard.hasNext()){  //Clear buffer
//                    keyboard.next();
//                }
            }
        }

        String outputPath = commandLine.getOptionValue('o');
        OutputPaths outputPaths = new OutputPaths(
                null,
                outputPath + "characterTable.json",
                outputPath + "crewTable.json",
                outputPath + "episodeTable.json",
                outputPath + "knownForTable.json",
                outputPath + "personTable.json",
                outputPath + "principalTable.json",
                outputPath + "professionTable.json",
                outputPath + "ratingTable.json",
                outputPath + "titleTable.json",
                outputPath + "genreTable.json"
        );

        System.out.println("Reading data...");
        long time = System.nanoTime();
        InputDataBlock inputDataBlock = InputDataBlock.readFromFiles(inputPaths);
        long diff = System.nanoTime() - time;
        System.out.println("Read data in " + diff + " nanos");

        System.out.println("Beginning conversion to output...");
        time = System.nanoTime();
        OutputDataBlock outputDataBlock = new OutputDataBlock(inputDataBlock);
        outputPaths.setData(outputDataBlock);
        diff = System.nanoTime() - time;
        System.out.println("Converted in " + diff + " nanos");

        System.out.println("Sending to files...");
        time = System.nanoTime();
        outputPaths.sendToFiles();
        diff = System.nanoTime() - time;
        System.out.println("Sent to files in " + diff + " nanos");
    }
}
