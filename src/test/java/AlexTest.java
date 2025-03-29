import com.example.Alex;
import com.example.Feline;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AlexTest {
    private Alex alex;

    @Mock
    private Feline feline;

    // параметры для тестов
    private static final List<String> EXPECTED_FRIENDS = List.of("Марти", "Глория", "Мелман");
    private static final String EXPECTED_HOME = "Нью-Йоркский зоопарк";
    private static final int EXPECTED_KITTENS_COUNT = 0;
    private static final String PREDATOR_TYPE = "Хищник";
    private static final List<String> PREDATOR_FOOD = List.of("Животные", "Птицы", "Рыба");

    @Before
    public void setUp() throws Exception {
        alex = new Alex(feline);
    }

    // Тест для получения списка друзей и выброс исключения
    @Test
    public void testGetFriendsReturnsCorrectList() {
        assertEquals("Список друзей не совпадает", EXPECTED_FRIENDS, alex.getFriends());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testFriendsListIsImmutable() {
        List<String> friends = alex.getFriends();
        friends.add("Новый друг"); // Вызовет исключение
    }

    // Тест для получения места обитания
    @Test
    public void testGetPlaceOfLivingReturnsNewYorkZoo() {
        assertEquals("Место обитания некорректно", EXPECTED_HOME, alex.getPlaceOfLiving());
    }

    // Тест наличия гривы (проверка пола)
    @Test
    public void testAlexIsMaleAndHasMane() {
        assertTrue("Алекс должен быть самцом с гривой", alex.doesHaveMane());
    }

    // Тест получения количества котят
    @Test
    public void testGetKittensAlwaysReturnsZero() {
        assertEquals("Количество котят должно быть 0", EXPECTED_KITTENS_COUNT, alex.getKittens());
        verifyNoInteractions(feline);
    }

    // Тест получения еды для Хищника
    @Test
    public void testGetFoodUsesPredatorType() throws Exception {
        when(feline.getFood(PREDATOR_TYPE)).thenReturn(PREDATOR_FOOD);
        List<String> food = alex.getFood();
        assertEquals("Еда хищника не совпадает", PREDATOR_FOOD, food);
        verify(feline, times(1)).getFood(PREDATOR_TYPE);
    }
}