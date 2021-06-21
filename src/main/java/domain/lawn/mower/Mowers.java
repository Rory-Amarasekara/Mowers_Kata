package domain.lawn.mower;

import domain.lawn.mower.position.and.orientation.Position;
import domain.lawn.mower.position.and.orientation.PositionWithOrientation;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Mowers {

    private Set<Mower> mowers;

    private Mowers(Mower... mowers) {
        this.mowers = new HashSet<>(Arrays.asList(mowers));
    }

    public static Mowers create(Mower... mowers) {
        return new Mowers(mowers);
    }

    public int getBiggestNumberOfMovementsForASingleMower() {
        return mowers
                .stream()
                .map(Mower::getRemainingNumberOfMovements).max(Comparator.comparingInt(i -> i))
                .orElse(0);
    }

    public Map<MowerId, Position> getMowerIdsWithNewPositionsRequired() {
        return mowers
                .parallelStream()
                .filter(Mower::requiresNewPosition)
                .collect(Collectors.toMap(Mower::getMowerId, mower -> mower
                        .getNextRequestedPosition()
                        .orElseThrow(() -> new RuntimeException("Next position should be present but is not"))));
    }

    public Set<Position> getCurrentMowerPositions() {
        return mowers.parallelStream().map(Mower::getPosition).collect(Collectors.toSet());
    }

    public Map<MowerId, Mower> getMowerIdMowerMap() {
        return mowers.parallelStream().collect(Collectors.toMap(Mower::getMowerId, mower -> mower));
    }

    public Map<MowerId, PositionWithOrientation> getMowerIdPositionWithOrientationMap() {
        return mowers.parallelStream().collect(Collectors.toMap(Mower::getMowerId, Mower::getPositionWithOrientation));
    }
}
