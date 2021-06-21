package domain.lawn.mower;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class NavigationPlan {

    private List<MowerMovement> mowerMovements;

    private NavigationPlan(MowerMovement... mowerMovements) {
        this.mowerMovements = Arrays.asList(mowerMovements);
    }

    public static NavigationPlan create(MowerMovement... mowerMovements) {
        return new NavigationPlan(mowerMovements);
    }

    List<MowerMovement> getMowerMovements() {
        return mowerMovements;
    }

    Optional<MowerMovement> getNextMovement() {
        return mowerMovements.isEmpty() ? Optional.empty() : Optional.ofNullable(mowerMovements.get(0));
    }

    void removeOldestMovement() {
        if (!mowerMovements.isEmpty()){
            mowerMovements = mowerMovements.subList(1, mowerMovements.size());
        }
    }
}
