package domain;

import domain.lawn.LawnWithMowers;
import domain.lawn.mower.*;
import domain.lawn.mower.position.and.orientation.factory.PositionWithOrientationFactory;
import domain.lawn.mower.service.LawnmowerSimulatorService;
import domain.lawn.mower.position.and.orientation.PositionWithOrientation;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class SingleMowerSimulationTest {

    @Test
    void single_mower_simulation_test_with_only_possible_movements() {
        //Given
        Mower mower = Mower.create(
                MowerId.create(1),
                PositionWithOrientationFactory.createPositionWithNorthOrientation(2, 2),
                NavigationPlan.create(MowerMovement.FORWARD, MowerMovement.RIGHT_TURN, MowerMovement.FORWARD, MowerMovement.LEFT_TURN, MowerMovement.LEFT_TURN));

        LawnWithMowers lawnWithMowers = LawnWithMowers.create(5, 5, Mowers.create(mower));

        //When
        Map<MowerId, PositionWithOrientation> actualMowersFinalPositionAndOrientation = LawnmowerSimulatorService.getFinalMowerPositionsAndOrientations(lawnWithMowers);

        //Then
        Map<MowerId, PositionWithOrientation> expectedMowersFinalPositionAdnOrientation = new HashMap<>();
        expectedMowersFinalPositionAdnOrientation.put(MowerId.create(1), PositionWithOrientationFactory.createPositionWithWestOrientation(3, 3));

        assertThat(actualMowersFinalPositionAndOrientation).isEqualTo(expectedMowersFinalPositionAdnOrientation);
    }

}
