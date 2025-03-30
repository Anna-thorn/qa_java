import com.example.Cat;
import com.example.Feline;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CatTest {
    // параметры для тестов
    private static final String CAT_SOUND = "Мяу";
    private static final List<String> PREDATOR_FOOD = List.of("Животные", "Птицы", "Рыба");

    @Mock
    private Feline feline; // реализация Predator

    // Тест звука Мяу
    @Test
    public void getSoundReturnsCorrectSound() {
        Cat cat = new Cat(feline);
        String sound = cat.getSound();
        assertEquals(CAT_SOUND, sound);
    }

    // Тест получения мяса и выброс исключения
    @Test
    public void getFoodCallsPredatorEatMeat() throws Exception {
        when(feline.eatMeat()).thenReturn(PREDATOR_FOOD); // настройка поведения мок

        Cat cat = new Cat(feline); // создание объекта и вызов метода
        List<String> food = cat.getFood();

        assertEquals(PREDATOR_FOOD, food); // вызов food и проверка, что он вернул еду хищника
        verify(feline, times(1)).eatMeat(); // проверка взаимодействия с мок
    }

    @Test(expected = Exception.class)
    public void getFoodThrowsExceptionWhenPredatorThrows() throws Exception {
        when(feline.eatMeat()).thenThrow(new Exception("Ошибка")); // настройка поведения мок
        Cat cat = new Cat(feline); // создаем объект и вызов метода, который должен бросить исключение
        cat.getFood();
    }
}