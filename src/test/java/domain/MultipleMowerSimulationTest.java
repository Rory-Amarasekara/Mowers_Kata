package domain;

import domain.lawn.LawnWithMowers;
import domain.lawn.mower.*;
import domain.lawn.mower.position.and.orientation.PositionWithOrientation;
import domain.lawn.mower.position.and.orientation.factory.PositionWithOrientationFactory;
import domain.lawn.mower.service.LawnmowerSimulatorService;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class MultipleMowerSimulationTest {

    @Test
    void multiple_mower_simulation_with_only_possible_movements() {
        //Given
        Mower mower0 = Mower.create(
                MowerId.create(0),
                PositionWithOrientationFactory.createPositionWithNorthOrientation(2, 2),
                NavigationPlan.create(MowerMovement.FORWARD, MowerMovement.RIGHT_TURN, MowerMovement.FORWARD, MowerMovement.LEFT_TURN, MowerMovement.LEFT_TURN));

        Mower mower1 = Mower.create(
                MowerId.create(1),
                PositionWithOrientationFactory.createPositionWithWestOrientation(2, 1),
                NavigationPlan.create(MowerMovement.FORWARD, MowerMovement.LEFT_TURN, MowerMovement.FORWARD));

        LawnWithMowers lawnWithMowers = LawnWithMowers.create(5, 5, Mowers.create(mower0, mower1));

        //When
        Map<MowerId, PositionWithOrientation> actualMowersFinalPositionAndOrientation = LawnmowerSimulatorService.getFinalMowerPositionsAndOrientations(lawnWithMowers);

        //Then
        Map<MowerId, PositionWithOrientation> expectedMowersFinalPositionAdnOrientation = new HashMap<>();

        expectedMowersFinalPositionAdnOrientation.put(MowerId.create(0), PositionWithOrientationFactory.createPositionWithWestOrientation(3, 3));
        expectedMowersFinalPositionAdnOrientation.put(MowerId.create(1), PositionWithOrientationFactory.createPositionWithSouthOrientation(1, 0));

        assertThat(actualMowersFinalPositionAndOrientation).isEqualTo(expectedMowersFinalPositionAdnOrientation);
    }

    @Test
    void multiple_mower_simulation_with_avoiding_out_of_lawn_movements() {
        //Given
        Mower mower0 = Mower.create(
                MowerId.create(0),
                PositionWithOrientationFactory.createPositionWithNorthOrientation(2, 2),
                NavigationPlan.create(MowerMovement.FORWARD, MowerMovement.RIGHT_TURN, MowerMovement.FORWARD, MowerMovement.LEFT_TURN,
                        MowerMovement.FORWARD, MowerMovement.FORWARD, MowerMovement.FORWARD, MowerMovement.RIGHT_TURN));

        Mower mower1 = Mower.create(
                MowerId.create(1),
                PositionWithOrientationFactory.createPositionWithWestOrientation(2, 1),
                NavigationPlan.create(MowerMovement.FORWARD, MowerMovement.LEFT_TURN, MowerMovement.FORWARD, MowerMovement.FORWARD));

        LawnWithMowers lawnWithMowers = LawnWithMowers.create(5, 5, Mowers.create(mower0, mower1));

        //When
        Map<MowerId, PositionWithOrientation> actualMowersFinalPositionAndOrientation = LawnmowerSimulatorService.getFinalMowerPositionsAndOrientations(lawnWithMowers);

        //Then
        Map<MowerId, PositionWithOrientation> expectedMowersFinalPositionAdnOrientation = new HashMap<>();

        expectedMowersFinalPositionAdnOrientation.put(MowerId.create(0), PositionWithOrientationFactory.createPositionWithEastOrientation(3, 5));
        expectedMowersFinalPositionAdnOrientation.put(MowerId.create(1), PositionWithOrientationFactory.createPositionWithSouthOrientation(1, 0));

        assertThat(actualMowersFinalPositionAndOrientation).isEqualTo(expectedMowersFinalPositionAdnOrientation);
    }

    @Test
    void multiple_mower_simulation_with_one_position_requested_but_already_taken_by_a_mower() {
        //Given
        Mower mower0 = Mower.create(
                MowerId.create(0),
                PositionWithOrientationFactory.createPositionWithEastOrientation(1, 2),
                NavigationPlan.create(MowerMovement.FORWARD, MowerMovement.FORWARD, MowerMovement.FORWARD));

        Mower mower1 = Mower.create(
                MowerId.create(1),
                PositionWithOrientationFactory.createPositionWithNorthOrientation(2, 0),
                NavigationPlan.create(MowerMovement.FORWARD, MowerMovement.FORWARD, MowerMovement.FORWARD));

        LawnWithMowers lawnWithMowers = LawnWithMowers.create(5, 5, Mowers.create(mower0, mower1));

        //When
        Map<MowerId, PositionWithOrientation> actualMowersFinalPositionAndOrientation = LawnmowerSimulatorService.getFinalMowerPositionsAndOrientations(lawnWithMowers);

        //Then
        Map<MowerId, PositionWithOrientation> expectedMowersFinalPositionAdnOrientation = new HashMap<>();

        expectedMowersFinalPositionAdnOrientation.put(MowerId.create(0), PositionWithOrientationFactory.createPositionWithEastOrientation(4, 2));
        expectedMowersFinalPositionAdnOrientation.put(MowerId.create(1), PositionWithOrientationFactory.createPositionWithNorthOrientation(2, 2));

        assertThat(actualMowersFinalPositionAndOrientation).isEqualTo(expectedMowersFinalPositionAdnOrientation);
    }
}
