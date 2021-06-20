package domain.lawn.mower.service;

import domain.lawn.LawnWithMowers;
import domain.lawn.mower.MowerId;
import domain.lawn.mower.position.and.orientation.PositionWithOrientation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LawnmowerSimulatorService {

    public static Map<MowerId, PositionWithOrientation> getFinalMowerPositionsAndOrientations(LawnWithMowers lawnWithMowers) {
        return lawnWithMowers.followMowerNavigationPlansAndGetFinalPositionAndOrientation();
    }
}
