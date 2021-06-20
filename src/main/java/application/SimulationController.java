package application;

import application.mapper.LawnWithMowersMapper;
import domain.lawn.LawnWithMowers;
import domain.lawn.mower.MowerId;
import domain.lawn.mower.position.and.orientation.PositionWithOrientation;
import domain.lawn.mower.service.LawnmowerSimulatorService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SimulationController {

    public static void generateOutputFileAndReturnPath(Path inputFilePath, Path outputFilePath) throws IOException {

        try {
            LawnWithMowers lawnWithMowers = convertFileDataToDomain(inputFilePath);

            Map<MowerId, PositionWithOrientation> finalMowerPositionsAndOrientations = LawnmowerSimulatorService.getFinalMowerPositionsAndOrientations(lawnWithMowers);

            writeDataToOutputFile(outputFilePath, finalMowerPositionsAndOrientations);

        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static LawnWithMowers convertFileDataToDomain(Path inputFilePath) throws IOException {

        return LawnWithMowersMapper.toDomain(inputFilePath);
    }

    private static void writeDataToOutputFile(Path outputFilePath, Map<MowerId, PositionWithOrientation> finalMowerPositionsAndOrientations) throws IOException {

       LawnWithMowersMapper.fromDomainToFile(outputFilePath, finalMowerPositionsAndOrientations);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("START");
        Path inputFilePath = Path.of("C:\\IdeaProjects\\Mowers_Kata\\src\\main\\java\\application\\file\\input\\inputFile.txt");
        Path outputFilePath = Path.of("C:\\IdeaProjects\\Mowers_Kata\\src\\main\\java\\application\\file\\output\\outputFile.txt");
        generateOutputFileAndReturnPath(inputFilePath, outputFilePath);
    }
}
