import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TelevisionTest {
    @Test
    void checkConstructor() {
        Television television = new Television(50,50);

        assertEquals(50, television.getCurrentChannel());
        assertEquals(50, television.getVolume());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 50, 49, 25,2})
    void setCurrentChannelValidInput(int channelValidInput) {
        Television television = new Television(50,50);
        television.setCurrentChannel(channelValidInput);

        assertEquals(channelValidInput, television.getCurrentChannel());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 51, 0, 100})
    void setCurrentChannelInvalidInput(int invalidChannel) {
        Television television = new Television(1, 10);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> television.setCurrentChannel(invalidChannel));

        assertEquals("Канал должен быть от 1 до 50", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 50, 99, 100,0})
    void setVolumeValidInput(int volumeValidInput) {
        Television television = new Television(50,50);
        television.setVolume(volumeValidInput);

        assertEquals(volumeValidInput, television.getVolume());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -200, -51, 101})
    void setVolumeInvalidInput(int invalidVolume) {
        Television television = new Television(1, 10);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> television.setVolume(invalidVolume));

        assertEquals("Громкость должна быть от 0 до 100", exception.getMessage());
    }
    @ParameterizedTest
    @CsvSource({
            // initialChannel, expectedChannel
            "1,  2",
            "25, 26",
            "49, 50",
            "50, 1"
    })
    void nextChannel(int initialChannel, int expectedChannel) {
        Television television = new Television(initialChannel, 10);
        television.nextChannel();

        assertEquals(expectedChannel, television.getCurrentChannel());
    }
}
