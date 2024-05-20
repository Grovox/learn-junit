package grovox.learning;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {
    private Car car;
    @BeforeEach
    void createCar(){
        car = new Car("Skoda", "ABC-1234", 2022, "Artemov Max");
    }



    @Test
    @EnabledOnOs(OS.WINDOWS) // Этот тест запускается только на Windows
    @EnabledOnJre(JRE.JAVA_20) // Этот тест запускается только на 20 java
    @DisabledOnJre(JRE.JAVA_8) //Этот тест не работает только на 8 java
    void getManufacturer() {
        assertEquals("Skoda", car.getManufacturer());
    }

    @Test
    @Execution(ExecutionMode.CONCURRENT) // Позволяет выполня тесты паралельно
    void getNumber() {
        assertEquals("ABC-1234", car.getNumber());
    }


    @Test
    @Execution(ExecutionMode.CONCURRENT) // Позволяет выполня тесты паралельно
    void setNumber() {
        car.setNumber("ABC-4321");
        assertEquals("ABC-4321", car.getNumber());
    }

    @ParameterizedTest // Позволяет тестить с несколькими параметрами
    @ValueSource(strings = {"'ABC-456', 'RTF-444', 'RRR-234'"}) // Позволяет указать один параметр
    @NullSource // Позволяет указать null
    @EmptySource // Позволяет указать пустое поле
    void setNumberMultipleValue(String number){
        car.setNumber(number);
        assertEquals(number, car.getNumber());
    }

    @ParameterizedTest
    @CsvSource({"AWR-111, 11", "FFF-333, 11"}) // позволяет указать несколько параметров
    void testSetNumberAndSetYear(String number, int year){
        car.setNumber(number);
        assertEquals(number, car.getNumber());
        
        Car carTest = new Car("", "", year, "");
        assertEquals(year, carTest.getYear());
    }


    @Test
    void getYear() {
        assertEquals(2022, car.getYear());
    }

    @Test
    void getOwner() {
        assertEquals("Artemov Max", car.getOwner());
    }

    @Test
    void setOwner() {
        car.setOwner("Artemov Alex");
        assertEquals("Artemov Alex", car.getOwner());
    }

    @Test
    void getOwners() {
        assertArrayEquals(new String[]{"Artemov Max"}, car.getOwners().toArray());
    }

    @Test
    void getAnyOwners() {
        car.setOwner("Artemov Alex");
        assertArrayEquals(new String[]{"Artemov Max", "Artemov Alex"}, car.getOwners().toArray());
    }

    @Test
    void testMethod(){
        try {
            Method method = Car.class.getDeclaredMethod("testMethod");
            method.setAccessible(true);
            assertEquals("abc", method.invoke(car).toString());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testMethodVisArgs(){ // тестируем приверный метод
        try {
            Method method = Car.class.getDeclaredMethod("testMethod", String.class);
            method.setAccessible(true);
            assertEquals("test" + "abc", method.invoke(car,"test").toString());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest
    @DisplayName("DisplayNameTest") //Меняет название при запуске
    @CsvFileSource(resources = "/grovox/learning/test-data.csv", delimiter = '|', numLinesToSkip = 1)
    public void setNumberVisParamFromFile(String number){
        car.setNumber(number);
        assertEquals(number, car.getNumber());
    }

    @Test
    void getTestData(){
        assertThrows(Exception.class, () -> {
            car.getDataFromRemoteServer();
        });
    }
    
}