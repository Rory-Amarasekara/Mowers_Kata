package domain;

import domain.lawn.Lawn;
import domain.lawn.mower.*;
import domain.lawn.mower.position.and.orientation.factory.PositionAndOrientationFactory;
import domain.lawn.mower.service.LawnmowerSimulatorService;
import domain.lawn.mower.position.and.orientation.PositionAndOrientation;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class SingleMowerSimulationTest {

    @Test
    void single_mower_simulation_test_with_only_possible_movements() {
        //Given
        Mower mower = Mower.create(
                MowerId.create(1),
                PositionAndOrientationFactory.createPositionWithNorthOrientation(2, 2),
                NavigationPlan.create(MowerMovement.FORWARD, MowerMovement.RIGHT_TURN, MowerMovement.FORWARD, MowerMovement.LEFT_TURN, MowerMovement.LEFT_TURN));

        Lawn lawn = Lawn.create(5, 5, Mowers.create(mower));

        //When
        Map<MowerId, PositionAndOrientation> actualMowersFinalPositionAndOrientation = LawnmowerSimulatorService.getFinalMowerPositionsAndOrientations(lawn);

        //Then
        Map<MowerId, PositionAndOrientation> expectedMowersFinalPositionAdnOrientation = new HashMap<>();
        expectedMowersFinalPositionAdnOrientation.put(MowerId.create(1), PositionAndOrientationFactory.createPositionWithWestOrientation(3, 3));

        assertThat(actualMowersFinalPositionAndOrientation).isEqualTo(expectedMowersFinalPositionAdnOrientation);
    }

}
