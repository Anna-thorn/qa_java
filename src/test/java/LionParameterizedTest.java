import com.example.Feline;
import com.example.Lion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class LionParameterizedTest {
    // параметры для тестов
    private static final String MALE = "Самец";
    private static final String FEMALE = "Самка";
    private static final String INVALID_SEX = "Оно";
    private static final String INVALID_SEX_MESSAGE = "Используйте допустимые значения пола животного - самец или самка";

    // параметры для тестов
    @Parameterized.Parameter
    public String sex;

    @Parameterized.Parameter(1)
    public Boolean expectedHasMane;

    @Parameterized.Parameter(2)
    public boolean shouldThrowException;

    // параметры для теста doesHaveMane
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {MALE, true, false}, // Самец → грива есть, исключения нет
                {FEMALE, false, false}, // Самка → гривы нет, исключения нет
                {INVALID_SEX, null, true} // Невалидный пол → исключение
        });
    }

    // Тест наличия гривы (проверка пола) и выброс исключения
    @Test
    public void testDoesHaveMane() throws Exception {
        if (shouldThrowException) {
            Exception exception = assertThrows(Exception.class, () -> new Lion(sex, new Feline()));
            assertEquals(INVALID_SEX_MESSAGE, exception.getMessage());
        } else {
            Lion lion = new Lion(sex, new Feline());
            assertEquals(expectedHasMane, lion.doesHaveMane());
        }
    }
}