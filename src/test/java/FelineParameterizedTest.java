import com.example.Feline;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FelineParameterizedTest {
    // параметры для теста getKittens
    @Parameterized.Parameter
    public int kittensCount;

    @Parameterized.Parameter(1)
    public int expectedKittensCount;

    @Parameterized.Parameters(name = "Количество котят: {0} → Ожидаем: {1}")
    public static Collection<Object[]> kittensDataProvider() {
        return Arrays.asList(new Object[][]{
                {-1, -1},
                {0, 0},
                {1, 1},
                {5, 5}
        });
    }

    // Параметризованный тест получения котят
    @Test
    public void getKittensWithArgShouldReturnSameNumber() { // getKittens с разными значениями
        Feline feline = new Feline();
        int actualKittens = feline.getKittens(kittensCount);
        assertEquals(String.format("Метод getKittens(%d) должен возвращать %d",
                        kittensCount, expectedKittensCount),
                expectedKittensCount, actualKittens);
    }
}