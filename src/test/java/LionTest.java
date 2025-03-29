import com.example.Feline;
import com.example.Lion;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class LionTest {
    // параметры для тестов
    private static final String MALE = "Самец";
    private static final String FEMALE = "Самка";
    private static final String INVALID_SEX = "Оно";
    private static final String INVALID_SEX_MESSAGE = "Используйте допустимые значения пола животного - самец или самка";
    private static final String PREDATOR = "Хищник";
    private static final List<String> PREDATOR_FOOD = List.of("Животные", "Птицы", "Рыба");
    private static final int EXPECTED_KITTENS_COUNT = 1;

    @Mock
    private Feline feline;

    // параметры для тестов
    private final String sex;
    private final Boolean expectedHasMane;
    private final boolean shouldThrowException;

    // конструктор для инициализации параметризованных тестов
    public LionTest(String sex, Boolean expectedHasMane, boolean shouldThrowException) {
        this.sex = sex;
        this.expectedHasMane = expectedHasMane;
        this.shouldThrowException = shouldThrowException;
    }

    // параметры для теста doesHaveMane
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {MALE, true, false}, // Самец → грива есть, исключения нет
                {FEMALE, false, false}, // Самка → гривы нет, исключения нет
                {INVALID_SEX, null, true} // Невалидный пол → исключение
        });
    }

    @Before
    public void init() throws Exception {
        MockitoAnnotations.openMocks(this).close();
    }

    // Тест наличия гривы (проверка пола) и выброс исключения
    @Test
    public void testDoesHaveMane() throws Exception {
        if (shouldThrowException) {
            Exception exception = assertThrows(Exception.class, () -> new Lion(sex, feline));
            assertEquals(INVALID_SEX_MESSAGE, exception.getMessage());
        } else {
            Lion lion = new Lion(sex, feline);
            assertEquals(expectedHasMane, lion.doesHaveMane());
        }
    }

    // Тест получения количества котят
    @Test
    public void getKittensShouldReturnCorrectValue() throws Exception {
        when(feline.getKittens()).thenReturn(EXPECTED_KITTENS_COUNT); // настройка мок
        Lion lion = new Lion(FEMALE, feline); // создание объекта с корректным полом
        assertEquals("Должно вернуться значение по умолчанию", EXPECTED_KITTENS_COUNT, lion.getKittens()); // вызов lion.getKittens() и проверка
        verify(feline, times(1)).getKittens(); // проверка взаимодействия с мок
    }

    // Тест получения еды для Хищника и выброс исключения
    @Test
    public void getFoodShouldCallFelineWithPredatorParameter() throws Exception {
        when(feline.getFood(PREDATOR)).thenReturn(PREDATOR_FOOD); // настройка мок
        Lion lion = new Lion(MALE, feline); // создаем объект
        assertEquals("Список еды хищника должен совпадать", PREDATOR_FOOD, lion.getFood()); // проверка результата
        verify(feline, times(1)).getFood(PREDATOR); // проверка взаимодействия с мок
    }

    @Test
    public void getFoodShouldThrowExceptionWhenFelineThrowsException() throws Exception {
        when(feline.getFood(PREDATOR)).thenThrow(new Exception("Ошибка Feline")); // настройка мок
        Lion lion = new Lion(MALE, feline); // создаем объект и вызов метода, который должен бросить исключение
        Exception exception = assertThrows(Exception.class, lion::getFood);
        assertEquals("Ошибка Feline", exception.getMessage());
    }
}