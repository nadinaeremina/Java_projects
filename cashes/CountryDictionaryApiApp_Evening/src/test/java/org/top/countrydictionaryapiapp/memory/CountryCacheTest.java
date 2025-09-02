package org.top.countrydictionaryapiapp.memory;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.top.countrydictionaryapiapp.model.Country;
import org.top.countrydictionaryapiapp.model.CountryStorage;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CountryCacheTest {
    // объект тестируемого кэша
    private CountryCache cache;

    // mock-объект провайдера данных для тестируемого кэша
    private CountryStorage mock;

    @BeforeEach
    public void setUp() {
        mock = Mockito.mock(CountryStorage.class, invocation -> {
            String methodName = invocation.getMethod().getName();
            String argsString = Arrays.toString(invocation.getArguments());
            String message = String.format("I don`t know method '%s' which called with args '%s'", methodName, argsString);
            throw new UnsupportedOperationException(message);
        });
        cache = new CountryCache(mock);
    }

    public void assertCountriesEquals(Country expected, Country actual) {
        assertEquals(expected.getAlpha2Code(), actual.getAlpha2Code());
        assertEquals(expected.getShortName(), actual.getShortName());
        assertEquals(expected.getOfficialName(), actual.getOfficialName());
    }

    @Nested
    public class SelectAllTest {

        @Test
        public void selectAll() {
            String oldCode = "old";
            Country old = new Country(oldCode, "old", "old");

            String expectedCode = "new";
            Country expected = new Country(expectedCode, "new", "new");

            Mockito.doReturn(List.of(expected)).when(mock).selectAll();
            Mockito.doReturn(Optional.of(old)).when(mock).selectByCode(oldCode);

            // закэшируем old
            Optional<Country> actualOld = cache.selectByCode(oldCode);
            assertTrue(actualOld.isPresent());
            assertCountriesEquals(old, actualOld.get());

            // получим все (произошла инвалидация - очистка + перезаполнение)
            List<Country> actualList = cache.selectAll();
            assertEquals(1, actualList.size());
            assertCountriesEquals(expected, actualList.getFirst());

            // сделаем обращение за старым объектом, которого не должно быть в кэше
            // проверим что отработала очистка старых данных и кэширование новых
            actualOld = cache.selectByCode(oldCode);
            assertTrue(actualOld.isPresent());
            assertCountriesEquals(old, actualOld.get());
            Mockito.verify(mock, Mockito.times(2)).selectByCode(oldCode);

            // проверим что были закэшированы новые данные
            Optional<Country> actual = cache.selectByCode(expectedCode);
            assertTrue(actual.isPresent());
            assertCountriesEquals(expected, actual.get());
        }
    }

    @Nested
    public class SelectByCodeTest {

        @Test
        public void hit() {
            String originCode = "ru";                                                       // код исходной страны
            Country origin = new Country(originCode, "РФ", "Россия");  // объект исходной страны
            Mockito.doReturn(Optional.of(origin)).when(mock).selectByCode(originCode);      // при обращении к хранилищу вернуть исходную страну

            Optional<Country> expected = cache.selectByCode(originCode);    // пробуем получить страну (она должна закэшироваться)
            assertTrue(expected.isPresent());                               // она должна быть
            Optional<Country> actual = cache.selectByCode(originCode);      // пробуем получить страну так же второй раз (она должна быть уже закэширована и получена из памяти без обращения к хранилищу)
            assertTrue(actual.isPresent());                                 // она тоже должна быть
            assertCountriesEquals(expected.get(), actual.get());            // сравнить объекты стран

            Mockito.verify(mock, Mockito.times(1)).selectByCode(originCode); // проверить, что было ровно одно обращение к хранилищу
        }

        @Test
        public void miss() {
            String originCode = "ru";
            Country expected = new Country(originCode, "РФ", "Россия");
            Mockito.doReturn(Optional.of(expected)).when(mock).selectByCode(originCode);

            Optional<Country> actual = cache.selectByCode(originCode);
            assertTrue(actual.isPresent());
            assertCountriesEquals(expected, actual.get());
            Mockito.verify(mock, Mockito.times(1)).selectByCode(originCode);
        }

        @Test
        public void empty() {
            Mockito.doReturn(Optional.empty()).when(mock).selectByCode(Mockito.anyString());
            Optional<Country> actual = cache.selectByCode(Mockito.anyString());
            assertTrue(actual.isEmpty());
        }
    }
}
