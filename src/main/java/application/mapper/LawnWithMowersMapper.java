package application.mapper;

import application.Utils;
import domain.lawn.LawnWithMowers;
import domain.lawn.mower.Mower;
import domain.lawn.mower.MowerId;
import domain.lawn.mower.Mowers;
import domain.lawn.mower.position.and.orientation.PositionWithOrientation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

import static application.Utils.SPACE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LawnWithMowersMapper {

    public static LawnWithMowers toDomain(Path filePath) throws IOException {

        String inputFileContent = Files.readString(filePath);

        return parseData(inputFileContent);
    }

    public static void fromDomainToFile(Path outputFilePath, Map<MowerId, PositionWithOrientation> finalMowerPositionsAndOrientations) throws IOException {

        createFile(outputFilePath);

        FileWriter fileWriter = new FileWriter(outputFilePath.toString());

        IntStream.range(0, finalMowerPositionsAndOrientations.size())
                .mapToObj(MowerId::create)
                .map(finalMowerPositionsAndOrientations::get)
                .forEach(positionWithOrientation -> {
                    try {
                        fileWriter.append(PositionWithOrientationMapper.fromDomain(positionWithOrientation));
                        fileWriter.append("\r\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        fileWriter.close();
    }

    private static LawnWithMowers parseData(String inputFileContent) {

        String[] inputFileLines = inputFileContent.split("\\r\\n");

        String[] lawnData = inputFileLines[0].split(SPACE);

        int lawnXAxisSize = Integer.parseInt(lawnData[0]);
        int lawnYAxisSize = Integer.parseInt(lawnData[1]);

        Mower[] mowerStream = IntStream
                .range(0, numberOfMowers(inputFileLines.length))
                .parallel()
                .mapToObj(createMower(inputFileLines))
                .toArray(Mower[]::new);

        return LawnWithMowers.create(lawnXAxisSize, lawnYAxisSize, Mowers.create(mowerStream));
    }

    private static int numberOfMowers(int numberOfLinesInInputFile) {
        return numberOfLinesInInputFile / 2;
    }

    private static IntFunction<Mower> createMower(String[] inputFileLines) {
        return mowerId -> {
            String[] mowerPositionAndOrientation = inputFileLines[mowerId * 2 + 1].split(SPACE);
            String xAxisPosition = mowerPositionAndOrientation[0];
            String yAxisPosition = mowerPositionAndOrientation[1];
            String orientation = mowerPositionAndOrientation[2];
            String mowerNavigationPlan = inputFileLines[mowerId * 2 + 2];
            return Mower.create(
                    MowerId.create(mowerId),
                    PositionWithOrientationMapper.toDomain(
                            Integer.parseInt(xAxisPosition),
                            Integer.parseInt(yAxisPosition),
                            orientation),
                    NavigationPlanMapper.toDomain(mowerNavigationPlan));
        };
    }

    private static void createFile(Path outputFilePath) throws IOException {

        File outputFile = new File(outputFilePath.toString());

        outputFile.createNewFile();
    }
}
