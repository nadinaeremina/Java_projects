package org.top.countrydictionaryapiapp.memory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.top.countrydictionaryapiapp.model.Country;
import org.top.countrydictionaryapiapp.model.CountryStorage;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CountryCacheTest {
    private CountryCache cache;
    private CountryStorage storage;

    @BeforeEach
    public void setUp() {
        storage = Mockito.mock(CountryStorage.class, invocation -> {
            throw new UnsupportedOperationException(invocation.getMethod().getName());
        });
        cache = new CountryCache(storage);
    }

    @Nested
    public class SelectAllTest {

        @Test
        public void success() {
            Country expected = new Country("TST", "test", "test");
            Mockito.doReturn(List.of(expected))
                    .when(storage).selectAll();

            List<Country> actualList = cache.selectAll();

            assertEquals(1, actualList.size());
            assertEquals(expected, actualList.getFirst());
        }

        @Test
        public void successInvalidation() {
            Country notActual = new Country("TST", "test", "test");
            Country actual = new Country("ABC", "test", "test");
            Mockito.doReturn(Optional.empty()).when(storage).selectByCode(Mockito.anyString());
            Mockito
                .doReturn(List.of(notActual))
                .doReturn(List.of(actual))
                .when(storage).selectAll();

            List<Country> notActualList = cache.selectAll();
            assertEquals(1, notActualList.size());
            assertEquals(notActual, notActualList.getFirst());

            List<Country> actualList = cache.selectAll();
            assertEquals(1, actualList.size());
            assertEquals(actual, actualList.getFirst());

            // проверить что прошла инвалидация после второго selectAll
            Optional<Country> emptyActual = cache.selectByCode(notActual.getAlpha2Code());
            assertTrue(emptyActual.isEmpty());
            Optional<Country> presentActual = cache.selectByCode(actual.getAlpha2Code());
            assertTrue(presentActual.isPresent());
        }
    }

    @Nested
    public class SelectByCodeTest {
        @Test
        public void hitMiss() {
            String originCode = "TST";
            Country origin = new Country(originCode, "test", "test");
            Mockito.doReturn(Optional.of(origin)).when(storage).selectByCode(originCode);

            // miss
            Optional<Country> actual = cache.selectByCode(originCode);
            assertTrue(actual.isPresent());
            assertEquals(origin, actual.get());
            Mockito.verify(storage, Mockito.times(1)).selectByCode(originCode);

            // hit
            actual = cache.selectByCode(originCode);
            assertTrue(actual.isPresent());
            assertEquals(origin.getAlpha2Code(), actual.get().getAlpha2Code());
            assertEquals(origin.getShortName(), actual.get().getShortName());
            assertEquals(origin.getOfficialName(), actual.get().getOfficialName());
            Mockito.verify(storage, Mockito.times(1)).selectByCode(originCode);
        }
    }

    @Nested
    public class UpdateTest {
        @Test
        public void update() {
            String originCode = "TST";
            Country origin = new Country(originCode, "test", "test");
            Country expected = new Country("TST", "test2", "test2");
            Mockito
                    .doReturn(Optional.of(origin))
                    .doReturn(Optional.of(expected))
                    .when(storage).selectByCode(originCode);
            Mockito.doNothing().when(storage).update(expected);

            // сделаем вызов selectByCode чтобы закэшировать объект
            cache.selectByCode(originCode);

            cache.update(expected);
            Optional<Country> actual = cache.selectByCode(originCode);
            assertTrue(actual.isPresent());
            assertEquals(expected, actual.get());
            Mockito.verify(storage, Mockito.times(2)).selectByCode(originCode);
        }
    }
}
