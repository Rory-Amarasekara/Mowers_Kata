package domain.lawn.mower.service;

import domain.lawn.Lawn;
import domain.lawn.mower.MowerId;
import domain.lawn.mower.position.and.orientation.PositionAndOrientation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LawnmowerSimulatorService {

    public static Map<MowerId, PositionAndOrientation> getFinalMowerPositionsAndOrientations(Lawn lawn) {
        return lawn.followMowerNavigationPlansAndGetFinalPositionAndOrientation();
    }
}
