package grovox.learning;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class CarMockito {
    private Car car;

    @Mock  // Создаем мок объект
    private Car testCar;

    @BeforeEach //Инициализируем объекты с аннотацией  Mock
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCarTestGetNumber(){
        assertEquals(null,testCar.getNumber());
    }

    @Test
    void createCar() {
        car = new Car("Skoda", "ABC-1234", 2022, "Artemov Max");
        Car carMock = Mockito.mock(Car.class); // Создаем мок объект
        assertEquals(null, carMock.getManufacturer());
        assertEquals(0, carMock.getYear());
    }

    @Test
    void remoteServesReturnValue(){
        Car newCar = Mockito.mock(Car.class);
        when(newCar.testInt(4)).thenReturn(10); // Обычно бы вернулось 8, но мы указали, что когда указываем 4, должно выдавать 10
        assertEquals(10, newCar.testInt(4));
    }

    @Test
    void getOwner(){
        Car newCar = mock(Car.class);
        when(newCar.getOwner()).thenReturn("Dima"); // Указываем что GetOwner возвращяет Dima, вместо пустого
        assertEquals("Dima", newCar.getOwner());
    }

    @Test
    public void testVerificationTest(){
        Car newCar = Mockito.mock(Car.class);
        newCar.getOwner();
        verify(newCar).getOwner(); // Выдает ошибку если не был вызван getOwner() можно указать, был ли он вызван с конкретным параметром
    }

    @Test
    public void testVerificationTest2(){
        Car newCar = Mockito.mock(Car.class);
        newCar.getOwner();
        newCar.getOwner();
        newCar.getOwner();
        verify(newCar, times(3)).getOwner(); // Выдает ошибку если не был вызван getOwner() 3 раза
    }

    @Test
    public void testVerificationTest3(){
        Car newCar = Mockito.mock(Car.class);
        verify(newCar, never()).getOwner(); // Выдает ошибку если был вызван getOwner()
    }

    @Test
    public void testVerificationTest4(){
        Car newCar = Mockito.mock(Car.class);
        newCar.getOwner();
        verify(newCar, atLeast(1)).getOwner(); // Выдает ошибку если не был вызван getOwner() как минимум 1 раз
    }

    @Test
    public void testVerificationTest5(){
        Car newCar = Mockito.mock(Car.class);
        verify(newCar, atMost(3)).getOwner(); // Выдает ошибку если был вызван getOwner() более 3
    }

    @Test
    public void testVerificationTest6(){
        Car newCar = Mockito.mock(Car.class);
        newCar.getOwner();
        verify(newCar, only()).getOwner(); // Выдает ошибку если был вызван не только getOwner()
    }

}
