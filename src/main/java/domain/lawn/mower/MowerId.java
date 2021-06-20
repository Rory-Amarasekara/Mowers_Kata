package domain.lawn.mower;

import lombok.EqualsAndHashCode;
import lombok.ToString;


@ToString
@EqualsAndHashCode
public class MowerId {

    private final int value;

    private MowerId(int value) {
        this.value = value;
    }

    public static MowerId create(int value) {
        return new MowerId(value);
    }
}
