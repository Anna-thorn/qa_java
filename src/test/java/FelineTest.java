import com.example.Feline;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class FelineTest {
    // параметры для тестов (общие)
    private static final List<String> PREDATOR_FOOD = List.of("Животные", "Птицы", "Рыба");
    private static final String FELINE_FAMILY = "Кошачьи";
    private static final int DEFAULT_KITTENS_COUNT = 1;

    // Тест получения еды для Хищника и выброс исключения
    @Test
    public void eatMeatShouldReturnPredatorFood() throws Exception { // eatMeat вызывает getFood с "Хищник" и возвращает результат
        Feline feline = new Feline();
        List<String> actualFood = feline.eatMeat();
        assertEquals("Метод eatMeat должен возвращать еду для хищников", PREDATOR_FOOD, actualFood);
    }

    @Test(expected = Exception.class)
    public void eatMeatShouldThrowExceptionWhenGetFoodFails() throws Exception {
        Feline feline = new Feline() {
            @Override
            public List<String> getFood(String animalKind) throws Exception {
                throw new Exception("Ошибка в getFood");
            }
        };
        feline.eatMeat();
    }

    // Тест получения семейства Кошачьи
    @Test
    public void getFamilyShouldReturnFelidae() { // getFamily возвращает "Кошачьи"
        Feline feline = new Feline();
        String actualFamily = feline.getFamily();
        assertEquals("Метод getFamily должен возвращать 'Кошачьи'", FELINE_FAMILY, actualFamily);
    }

    // Тест получения котят по умолчанию (без параметра)
    @Test
    public void getKittensNoArgShouldReturnOne() { // getKittens без параметров возвращает 1
        Feline feline = new Feline();
        int actualKittens = feline.getKittens();
        assertEquals("Метод getKittens() без параметров должен возвращать 1", DEFAULT_KITTENS_COUNT, actualKittens);
    }
}