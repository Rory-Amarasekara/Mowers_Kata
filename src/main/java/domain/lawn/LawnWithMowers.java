package domain.lawn;

import domain.lawn.mower.Mower;
import domain.lawn.mower.MowerId;
import domain.lawn.mower.Mowers;
import domain.lawn.mower.position.and.orientation.Position;
import domain.lawn.mower.position.and.orientation.PositionWithOrientation;
import lombok.ToString;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@ToString
public class LawnWithMowers {

    private int xAxisSize;
    private int yAxisSize;
    private Mowers mowers;

    private LawnWithMowers(int xAxisSize, int yAxisSize, Mowers mowers) {
        this.xAxisSize = xAxisSize;
        this.yAxisSize = yAxisSize;
        this.mowers = mowers;
    }

    public static LawnWithMowers create(int xAxisSize, int yAxisSize, Mowers mowers) {
        return new LawnWithMowers(xAxisSize, yAxisSize, mowers);
    }

    public Map<MowerId, PositionWithOrientation> followMowerNavigationPlansAndGetFinalPositionAndOrientation() {

        Map<MowerId, Mower> mowerIdMowerMap = mowers.toMowerIdMowerMap();

        int biggestNumberOfMovementsForASingleMower = mowers.getBiggestNumberOfMovementsForASingleMower();

        for (int i = 0; i < biggestNumberOfMovementsForASingleMower; i++) {

            Set<Position> currentMowerPositions = mowers.getCurrentMowerPositions();
            Map<MowerId, Position> mowerIdsWithNewPositionsRequired = mowers.getMowerIdsWithNewPositionsRequired();


            Set<MowerId> mowerIdsThatRequireANewPosition = mowerIdsWithNewPositionsRequired.keySet();

            Set<MowerId> mowerIdsThatCantMove = mowerIdsThatRequireANewPosition
                    .parallelStream()
                    .filter(mowerId -> mowerCantMove(mowerId, currentMowerPositions, mowerIdsWithNewPositionsRequired, xAxisSize, yAxisSize))
                    .collect(Collectors.toSet());

            Set<MowerId> mowersThatCanMove = getMowersThatCanMove(mowerIdMowerMap.keySet(), mowerIdsThatCantMove);

            mowersThatCanMove.parallelStream().map(mowerIdMowerMap::get).forEach(Mower::applyNextMowerMovement);
            mowerIdsThatCantMove.parallelStream().map(mowerIdMowerMap::get).forEach(Mower::cancelNextMovement);
        }

        return mowers.getMowerIdPositionWithOrientationMap();
    }

    private Set<MowerId> getMowersThatCanMove(Set<MowerId> mowerIds, Set<MowerId> mowerIdsThatCantMove) {

        Set<MowerId> mowerIdsThatCanMove = new HashSet<>(mowerIds);
        mowerIdsThatCanMove.removeAll(mowerIdsThatCantMove);
        return mowerIdsThatCanMove;
    }

    private boolean mowerCantMove(MowerId mowerId,
                                  Set<Position> currentMowerPositions,
                                  Map<MowerId, Position> mowerIdsWithNewPositionsRequired,
                                  int lawnXAxisSize, int lawnYAxisSize) {

        return nextPositionOutsideLawn(mowerIdsWithNewPositionsRequired.get(mowerId), lawnXAxisSize, lawnYAxisSize)
                || nextMowersPositionIsInConflictWithAnotherMowersCurrentOrNextPosition(mowerId, currentMowerPositions, mowerIdsWithNewPositionsRequired);

    }

    private boolean nextPositionOutsideLawn(Position position, int xAxisSize, int yAxisSize) {

        int xAxisPosition = position.getXAxisPosition();
        int yAxisPosition = position.getYAxisPosition();

        return 0 > xAxisPosition || xAxisPosition > xAxisSize
                || 0 > yAxisPosition || yAxisPosition > yAxisSize;
    }

    private boolean nextMowersPositionIsInConflictWithAnotherMowersCurrentOrNextPosition(MowerId mowerId,
                                                                                         Set<Position> currentMowerPositions,
                                                                                         Map<MowerId, Position> mowerIdsWithNewPositionsRequired) {

        Position positionRequested = mowerIdsWithNewPositionsRequired.get(mowerId);

        return currentMowerPositions.contains(positionRequested);
    }
}
