package application.mapper;

import domain.lawn.mower.Mower;
import domain.lawn.mower.MowerId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MowerMapper {

    public static Mower toDomain(int mowerId, int xAxisPosition, int yAxisPosition, String orientation, String navigationPlan) {
        return Mower.create(
                MowerId.create(mowerId),
                PositionWithOrientationMapper.toDomain(xAxisPosition, yAxisPosition, orientation),
                NavigationPlanMapper.toDomain(navigationPlan));
    }
}
