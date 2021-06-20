package domain.lawn.mower;

import domain.lawn.mower.position.and.orientation.PositionAndOrientation;

import java.util.Arrays;
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

    public Map<MowerId, PositionAndOrientation> followMowerNavigationPlansAndGetFinalPositionAndOrientation() {
        return mowers
                .parallelStream()
                .map(Mower::followNavigationPlan)
                .collect(Collectors.toMap(Mower::getMowerId, Mower::getPositionAndOrientation));
    }
}
