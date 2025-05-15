import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void checkConstructor() {
        Game game = new Game();
        int secretNumber = game.getSecretNumber();

        assertTrue(secretNumber >= 1 && secretNumber <= 100);
    }

    @Test
    void checkCorrectGuess() {
        Game game = new Game();
        int secretNumber = game.getSecretNumber();

        assertEquals("Угадал", game.checkGuess(secretNumber));
    }

    @Test
    void checkGuessHigher() {
        Game game = new Game();
        int secretNumber = game.getSecretNumber();

        assertEquals("Меньше", game.checkGuess(Math.min(secretNumber + 1, 100)));
    }

    @Test
    void checkGuessLower() {
        Game game = new Game();
        int secretNumber = game.getSecretNumber();

        assertEquals("Больше", game.checkGuess(Math.max(secretNumber - 1, 1)));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, 101, 1000})
    void checkInvalidInput(int invalidGuess) {
        Game game = new Game();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> game.checkGuess(invalidGuess));

        assertEquals("Число должно быть от 1 до 100", exception.getMessage());
    }
}